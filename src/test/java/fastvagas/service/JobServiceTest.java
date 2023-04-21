package fastvagas.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fastvagas.entity.Job;
import fastvagas.entity.UserEntity;
import fastvagas.repository.JobRepository;
import fastvagas.repository.UserJobRepository;
import fastvagas.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

  @Mock JobRepository jobRepository;

  @Mock UserJobRepository userJobRepository;

  @Mock UserRepository userRepository;

  @Mock MailService mailService;

  private JobService jobService;

  @BeforeEach
  void setup() {
    jobService = new JobService(jobRepository, userJobRepository, userRepository, mailService);
  }

  private UserEntity createUser() {
    UserEntity user = new UserEntity();
    user.setEmail("test@user.com");
    user.setId(1L);
    user.setTerms("");
    return user;
  }

  @Test
  @DisplayName("processAllUsersTest_disabled")
  void processAllUsersTest_disabled() {
    when(userRepository.findAllByDisabledAt(any())).thenReturn(List.of());

    jobService.processAllUsers();

    verify(jobRepository, times(0)).findAllByCreatedStartingAt(any());
  }

  @Test
  @DisplayName("processAllUsersTest_emptyTerm")
  void processAllUsersTest_emptyTerm() {
    UserEntity user = createUser();

    when(userRepository.findAllByDisabledAt(any())).thenReturn(List.of(user));

    jobService.processAllUsers();

    verify(jobRepository, times(0)).findAllByCreatedStartingAt(any());
  }

  @Test
  @DisplayName("processAllUsersTest_noUserJobs")
  void processAllUsersTest_noUserJobs() {
    UserEntity user = createUser();
    user.setTerms("lala");

    when(userRepository.findAllByDisabledAt(any())).thenReturn(List.of(user));

    Job jobOne = new Job();
    jobOne.setId(1L);
    jobOne.setJobTitle("Java Developer");
    jobOne.setCompanyName("Encora");
    jobOne.setJobType("Full-time");
    jobOne.setJobDescription("Code for fun, but all the time");
    jobOne.setPublishedAt("Monday, 7th April, 2023");
    jobOne.setJobUrl("http://test-job.com/one");
    jobOne.setPortalId(1L);
    jobOne.setCreatedAt(LocalDateTime.now());

    when(jobRepository.findAllByCreatedStartingAt(any())).thenReturn(List.of(jobOne));

    jobService.processAllUsers();

    verify(jobRepository, times(1)).findAllByCreatedStartingAt(any());
    verify(userJobRepository, times(0)).saveAll(any());
    verify(mailService, times(0)).jobNotification(any(), any());
  }

  @Test
  @DisplayName("processAllUsersTest_success")
  void processAllUsersTest_success() {
    UserEntity user = createUser();
    user.setTerms("java");

    when(userRepository.findAllByDisabledAt(any())).thenReturn(List.of(user));

    Job jobOne = new Job();
    jobOne.setId(1L);
    jobOne.setJobTitle("Java Developer");
    jobOne.setCompanyName("Encora");
    jobOne.setJobType("Full-time");
    jobOne.setJobDescription("Code for fun, but all the time");
    jobOne.setPublishedAt("Monday, 7th April, 2023");
    jobOne.setJobUrl("http://test-job.com/one");
    jobOne.setPortalId(1L);
    jobOne.setCreatedAt(LocalDateTime.now());

    when(jobRepository.findAllByCreatedStartingAt(any())).thenReturn(List.of(jobOne));

    jobService.processAllUsers();

    verify(jobRepository, times(1)).findAllByCreatedStartingAt(any());
    verify(userJobRepository, times(1)).saveAll(any());
    verify(mailService, times(1)).jobNotification(any(), any());
  }
}
