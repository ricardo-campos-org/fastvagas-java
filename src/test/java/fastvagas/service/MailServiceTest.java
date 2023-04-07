package fastvagas.service;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import fastvagas.config.MailPropertiesConfig;
import fastvagas.entity.Job;
import fastvagas.entity.User;
import java.time.LocalDateTime;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.Transport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MailServiceTest {

  @Mock MailPropertiesConfig mailPropertiesConfig;

  @Mock Transport transport;

  private MailService mailService;

  @BeforeEach
  void setupTest() {
    mailService = new MailService(mailPropertiesConfig);
  }

  private User createUser() {
    User user = new User();
    user.setId(1L);
    user.setFirstName("Test");
    user.setLastName("User");
    user.setEmail("test@user.com");
    user.setCity("Joinville");
    user.setState("SC");
    user.setCreatedAt(LocalDateTime.now());
    user.setTerms("dev");
    return user;
  }

  private Job createJob() {
    Job job = new Job();
    job.setId(1L);
    job.setJobTitle("Developer");
    job.setCompanyName("Encora");
    job.setJobType("Full-time");
    job.setJobDescription("Be the best developer");
    job.setPublishedAt("Some day");
    job.setJobUrl("https://career.encora.com/brazil/job");
    job.setPortalId(1L);
    job.setCreatedAt(LocalDateTime.now());
    return job;
  }

  @Test
  @DisplayName("jobNotificationTestDisabled")
  void jobNotificationTestDisabled() {
    when(mailPropertiesConfig.getEnabled()).thenReturn("false");

    boolean emailSent = mailService.jobNotification(createUser(), Set.of(createJob()));
    Assertions.assertFalse(emailSent);
  }

  @Test
  @DisplayName("jobNotificationTestSuccess")
  void jobNotificationTestSuccess() throws Exception {
    when(mailPropertiesConfig.getEnabled()).thenReturn("true");
    when(mailPropertiesConfig.getSmtpHost()).thenReturn("mail.com");
    when(mailPropertiesConfig.getSmtpPort()).thenReturn("123");
    when(mailPropertiesConfig.getSmtpSocketFactoryClass()).thenReturn("java.class");
    when(mailPropertiesConfig.getFromAddress()).thenReturn("mail@test.com");

    try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
      boolean emailSent = mailService.jobNotification(createUser(), Set.of(createJob()));
      Assertions.assertTrue(emailSent);

      mockedTransport.verify(
          () ->
              Transport.send(
                  argThat(
                      message -> {
                        try {
                          return message.getSubject().equals("1 new jobs found!");
                        } catch (MessagingException e) {
                          throw new RuntimeException(e);
                        }
                      })));
    }
  }

  @Test
  @DisplayName("jobNotificationTestNullCases")
  void jobNotificationTestNullCases() throws Exception {
    when(mailPropertiesConfig.getEnabled()).thenReturn("true");
    when(mailPropertiesConfig.getSmtpHost()).thenReturn("mail.com");
    when(mailPropertiesConfig.getSmtpPort()).thenReturn("123");
    when(mailPropertiesConfig.getSmtpSocketFactoryClass()).thenReturn("java.class");
    when(mailPropertiesConfig.getFromAddress()).thenReturn("mail@test.com");
    when(mailPropertiesConfig.getDebug()).thenReturn("true");
    when(mailPropertiesConfig.getSmtpAuth()).thenReturn("true");
    when(mailPropertiesConfig.getSmtpStarttlsEnabled()).thenReturn("true");

    try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
      boolean emailSent = mailService.jobNotification(createUser(), Set.of(createJob()));
      Assertions.assertTrue(emailSent);

      mockedTransport.verify(
          () ->
              Transport.send(
                  argThat(
                      message -> {
                        try {
                          return message.getSubject().equals("1 new jobs found!");
                        } catch (MessagingException e) {
                          throw new RuntimeException(e);
                        }
                      })));
    }
  }

  @Test
  @DisplayName("sendLogsToAdminTest")
  void sendLogsToAdminTest() {
    mailService.sendLogsToAdmin("Test content");
  }
}
