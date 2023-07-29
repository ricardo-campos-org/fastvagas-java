package fastvagas.service;

import fastvagas.entity.Job;
import fastvagas.entity.UserEntity;
import fastvagas.entity.UserJob;
import fastvagas.entity.UserJobPk;
import fastvagas.repository.JobRepository;
import fastvagas.repository.UserJobRepository;
import fastvagas.repository.UserRepository;
import fastvagas.util.DateUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** This class contains useful method to retrieve jobs information. */
@Slf4j
@Service
public class JobService {

  private JobRepository jobRepository;
  private UserJobRepository userJobRepository;
  private UserRepository userRepository;
  private MailService mailService;

  /**
   * Creates an instance of JobService.
   *
   * @param jobRepository {@link JobRepository} instance
   * @param userJobRepository {@link UserJobRepository} instance
   * @param userRepository {@link UserRepository} instance
   * @param mailService {@link MailService} instance
   */
  public JobService(
      JobRepository jobRepository,
      UserJobRepository userJobRepository,
      UserRepository userRepository,
      MailService mailService) {
    this.jobRepository = jobRepository;
    this.userJobRepository = userJobRepository;
    this.userRepository = userRepository;
    this.mailService = mailService;
  }

  /** Process a list of jobs and a list of users' terms. */
  public void processAllUsers() {
    List<UserEntity> enabledUsers = userRepository.findAllByDisabledAt(null);
    if (enabledUsers.isEmpty()) {
      log.info("No active users!");
      return;
    }

    for (UserEntity user : enabledUsers) {
      if (!user.getTerms().isBlank()) {
        processUserJobs(user);
      } else {
        log.info("User {} doesn't have search terms!", user.getEmail());
      }
    }
  }

  private void processUserJobs(UserEntity user) {
    LocalDateTime startingAt = user.getLastSearch();
    log.info("Processing new jobs to user {}", user.getEmail());
    log.info("Processing new jobs starting at {}", DateUtil.formatLocalDateTime(startingAt));

    List<Job> jobList = jobRepository.findAllByCreatedStartingAt(startingAt);
    log.info("{} new job(s) to check to user {}!", jobList.size(), user.getEmail());

    Set<UserJob> userJobsToSave = new HashSet<>();
    Set<Job> jobsToNotify = new HashSet<>();

    // Note: terms should be separated by semicolon
    List<String> terms = Arrays.asList(user.getTerms().split(";"));

    for (Job job : jobList) {
      String match = check(job.getJobTitle(), terms);
      if (!Objects.isNull(match)) {
        UserJobPk pk = new UserJobPk();
        pk.setUserId(user.getId());
        pk.setJobId(job.getId());

        UserJob userJob = new UserJob();
        userJob.setId(pk);
        userJob.setTerm(match);
        userJobsToSave.add(userJob);
        jobsToNotify.add(job);
      }
    }

    if (!userJobsToSave.isEmpty()) {
      log.info("{} user jobs to save(s)!", userJobsToSave.size());
      userJobRepository.saveAll(new ArrayList<>(userJobsToSave));
      mailService.jobNotification(user, jobsToNotify);
    } else {
      log.info("No user jobs to save(s)!");
    }
  }

  private String check(String jobName, List<String> terms) {
    for (String term : terms) {
      if (jobName.toLowerCase().contains(term.toLowerCase())) {
        return term;
      }
    }

    return null;
  }
}
