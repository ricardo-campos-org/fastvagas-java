package fastvagas.service;

import fastvagas.data.entity.PortalJob;
import fastvagas.data.entity.PersonJob;
import fastvagas.data.entity.PersonTerm;
import fastvagas.data.repository.PersonJobRepository;
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

    @Autowired
    public JobService(PortalJobRepository portalJobRepository, PersonJobRepository personJobRepository,
                      PersonTermRepository personTermRepository) {
        this.portalJobRepository = portalJobRepository;
        this.personJobRepository = personJobRepository;
        this.personTermRepository = personTermRepository;
    }

    public List<PortalJob> findUserJobsByTermsNotSeen(Integer personId) {
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

        Set<Integer> portalJobIds = jobServices
                .stream()
                .map(PersonJob::getPortal_job_id)
                .collect(Collectors.toSet());

        List<PortalJob> portalJobList = new ArrayList<>();
        for (Integer portalJobId : portalJobIds) {
            portalJobList.addAll(portalJobRepository.findAllByPortalId(portalJobId));
        }

        return portalJobList;
    }

    public void processUserJobs(LocalDateTime startingAt) {
        Set<Long> userIds = userTermRepository.findAllEnabledUsersTerms()
                .stream()
                .map(PersonTerm::getUser_id)
                .collect(Collectors.toSet());

        for (Long user_id : userIds) {
            processUserJobs(user_id, startingAt);
        }
    }

    public void processUserJobs(Integer userId, LocalDateTime startingAt) {
        log.info("ProcessUserJobs starting at {} for user {}", DateUtil.formatLocalDateTime(startingAt), userId);

        List<PortalJob> portalJobList = portalJobService.findAllByCreatedAt(startingAt);
        List<PersonTerm> userTerms = userTermRepository.findAllByUserId(userId);

        log.info("{} jobs to check!", portalJobList.size());
        log.info("{} user term(s)!", userTerms.size());

        Set<PersonJob> userJobsToSave = new HashSet<>();

        for (PersonTerm userTerm : userTerms) {
            List<String> terms = Arrays.asList(userTerm.getTerms().split(";"));

            for (PortalJob portalJob : portalJobList) {

                Boolean match = check(portalJob.getName(), terms);
                if (match) {
                    if (!userJobRepository.exists(userTerm.getUser_id(), portalJob.getPortal_job_id())) {
                        PersonJob userJob = PersonJob.builder()
                                .user_id(userTerm.getUser_id())
                                .portal_job_id(portalJob.getPortal_job_id())
                                .seen(null)
                                .build();

                        userJobsToSave.add(userJob);
                    }
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
