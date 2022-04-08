package fastvagas.service;

import fastvagas.data.entity.PortalJob;
import fastvagas.data.entity.UserJob;
import fastvagas.data.entity.UserTerm;
import fastvagas.data.repository.PortalJobService;
import fastvagas.data.repository.UserJobRepository;
import fastvagas.data.repository.UserTermRepository;
import fastvagas.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobService {

    private final PortalJobService portalJobService;
    private final UserJobRepository userJobRepository;
    private final UserTermRepository userTermRepository;

    @Autowired
    public JobService(PortalJobService portalJobService, UserJobRepository userJobRepository,
                      UserTermRepository userTermRepository) {
        this.portalJobService = portalJobService;
        this.userJobRepository = userJobRepository;
        this.userTermRepository = userTermRepository;
    }

    public List<PortalJob> findUserJobsByTermsNotSeen(Long userId) {
        if (Optional.ofNullable(userId).isEmpty()) {
            throw new RuntimeException("User id not provided!!");
        }

        List<UserJob> jobServices = userJobRepository.findAllNotSeen(userId);
        if (jobServices.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Long> portalJobIds = jobServices
                .stream()
                .map(UserJob::getPortal_job_id)
                .collect(Collectors.toSet());

        return portalJobService.findAllByPortalJobidList(portalJobIds);
    }

    public void processUserJobs(LocalDateTime startingAt) {
        Set<Long> userIds = userTermRepository.findAllEnabledUsersTerms()
                .stream()
                .map(UserTerm::getUser_id)
                .collect(Collectors.toSet());

        for (Long user_id : userIds) {
            processUserJobs(user_id, startingAt);
        }
    }

    public void processUserJobs(Long userId, LocalDateTime startingAt) {
        log.info("ProcessUserJobs starting at {} for user {}", DateUtil.formatLocalDateTime(startingAt), userId);

        List<PortalJob> portalJobList = portalJobService.findAllByCreatedAt(startingAt);
        List<UserTerm> userTerms = userTermRepository.findAllByUserId(userId);

        log.info("{} jobs to check!", portalJobList.size());
        log.info("{} user term(s)!", userTerms.size());

        Set<UserJob> userJobsToSave = new HashSet<>();

        for (UserTerm userTerm : userTerms) {
            List<String> terms = Arrays.asList(userTerm.getTerms().split(";"));

            for (PortalJob portalJob : portalJobList) {

                Boolean match = check(portalJob.getName(), terms);
                if (match) {
                    if (!userJobRepository.exists(userTerm.getUser_id(), portalJob.getPortal_job_id())) {
                        UserJob userJob = UserJob.builder()
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
            userJobRepository.createBatch(new ArrayList<>(userJobsToSave));
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
