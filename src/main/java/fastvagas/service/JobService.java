package fastvagas.service;

import fastvagas.entity.Person;
import fastvagas.entity.PortalJob;
import fastvagas.entity.PersonJob;
import fastvagas.repository.PersonJobRepository;
import fastvagas.repository.PersonRepository;
import fastvagas.repository.PortalJobRepository;
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
    private final PersonRepository personRepository;

    @Autowired
    public JobService(PortalJobRepository portalJobRepository, PersonJobRepository personJobRepository,
                      PersonRepository personRepository) {
        this.portalJobRepository = portalJobRepository;
        this.personJobRepository = personJobRepository;
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

        Set<Person> personTerms = new HashSet<>();

        for (Person p : enabledUsers) {
            if (!p.getTerms().isEmpty()) {
                personTerms.add(p);
            }
        }

        for (Person personTerm : personTerms) {
            processUserJobs(personTerm, startingAt);
        }
    }

    public void processUserJobs(Person person, LocalDateTime startingAt) {
        final Long personId = person.getId();
        log.info("ProcessUserJobs starting at {} for user {}", DateUtil.formatLocalDateTime(startingAt), personId);

        List<PortalJob> portalJobList = portalJobRepository.findAllByCreatedStartingAt(startingAt);

        log.info("{} jobs to check!", portalJobList.size());

        Set<PersonJob> userJobsToSave = new HashSet<>();

        List<String> terms = Arrays.asList(person.getTerms().split(";"));

        for (PortalJob portalJob : portalJobList) {

            Boolean match = check(portalJob.getJobTitle(), terms);
            if (match) {
                long count = personJobRepository
                    .findAllByPersonId(person.getId())
                    .stream().filter(pj -> pj.getPortalJobId().equals(portalJob.getId()))
                    .count();

                if (count == 0L) {
                    PersonJob userJob = PersonJob.builder()
                            .personId(person.getId())
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
