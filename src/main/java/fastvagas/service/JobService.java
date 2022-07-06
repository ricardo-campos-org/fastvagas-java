package fastvagas.service;

import fastvagas.data.entity.Person;
import fastvagas.data.entity.PortalJob;
import fastvagas.data.entity.PersonJob;
import fastvagas.data.entity.PersonTerm;
import fastvagas.data.repository.PersonJobRepository;
import fastvagas.data.repository.PersonRepository;
import fastvagas.data.repository.PersonTermRepository;
import fastvagas.data.repository.PortalJobRepository;
import fastvagas.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobService {

    private final PortalJobRepository portalJobRepository;
    private final PersonJobRepository personJobRepository;
    private final PersonTermRepository personTermRepository;
    private final PersonRepository personRepository;

    @Autowired
    public JobService(PortalJobRepository portalJobRepository, PersonJobRepository personJobRepository,
                      PersonTermRepository personTermRepository, PersonRepository personRepository) {
        this.portalJobRepository = portalJobRepository;
        this.personJobRepository = personJobRepository;
        this.personTermRepository = personTermRepository;
        this.personRepository = personRepository;
    }

    public List<PortalJob> findUserJobsByTermsNotSeen(Long personId) {
        if (Optional.ofNullable(personId).isEmpty()) {
            throw new RuntimeException("User id not provided!!");
        }

        List<PersonJob> jobServices = personJobRepository.findAllByPersonId(personId)
                .stream()
                .filter((x -> Objects.isNull(x.getSeen())))
                .collect(Collectors.toList());
        if (jobServices.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Long> portalJobIds = jobServices
                .stream()
                .map(PersonJob::getPortalJobId)
                .collect(Collectors.toSet());

        List<PortalJob> portalJobList = new ArrayList<>();
        for (Long portalJobId : portalJobIds) {
            portalJobList.addAll(portalJobRepository.findAllByPortalId(portalJobId));
        }

        return portalJobList;
    }

    public void processUserJobs(LocalDateTime startingAt) {
        List<Person> enabledUsers = personRepository.findAllByEnabled(Boolean.TRUE);
        if (enabledUsers.isEmpty()) {
            return;
        }

        Set<PersonTerm> personTerms = new HashSet<>();

        for (Person p : enabledUsers) {
            List<PersonTerm> userTerms = personTermRepository.findAllByPersonId(p.getId());
            if (!userTerms.isEmpty()) {
                personTerms.addAll(userTerms);
            }
        }

        for (PersonTerm personTerm : personTerms) {
            processUserJobs(personTerm, startingAt);
        }
    }

    public void processUserJobs(PersonTerm personTerm, LocalDateTime startingAt) {
        final Long personId = personTerm.getPersonId();
        log.info("ProcessUserJobs starting at {} for user {}", DateUtil.formatLocalDateTime(startingAt), personId);

        List<PortalJob> portalJobList = portalJobRepository.findAllByCreatedStartingAt(startingAt);

        log.info("{} jobs to check!", portalJobList.size());

        Set<PersonJob> userJobsToSave = new HashSet<>();

        List<String> terms = Arrays.asList(personTerm.getTerms().split(";"));

        for (PortalJob portalJob : portalJobList) {

            Boolean match = check(portalJob.getJobTitle(), terms);
            if (match) {
                long count = personJobRepository
                    .findAllByPersonId(personTerm.getPersonId())
                    .stream().filter(pj -> pj.getPortalJobId().equals(portalJob.getId()))
                    .count();

                if (count == 0L) {
                    PersonJob userJob = PersonJob.builder()
                            .personId(personTerm.getPersonId())
                            .portalJobId(portalJob.getId())
                            .seen(null)
                            .build();

                    userJobsToSave.add(userJob);
                }
            }
        }

        if (!userJobsToSave.isEmpty()) {
            log.info("{} user jobs to save(s)!", userJobsToSave.size());
            personJobRepository.saveAll(new ArrayList<>(userJobsToSave));
        } else {
            log.info("No user jobs to save(s)!");
        }
    }

    private Boolean check(String jobName, List<String> terms) {
        for (String term : terms) {
            if (jobName.toLowerCase().contains(term.toLowerCase())) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }
}
