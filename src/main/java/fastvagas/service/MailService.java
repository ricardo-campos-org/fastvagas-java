package fastvagas.service;

import fastvagas.config.MailPropertiesConfig;
import fastvagas.entity.Job;
import fastvagas.entity.UserEntity;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class contains methods to send mail notifications. */
@Slf4j
@Service
public class MailService {

  private MailPropertiesConfig mailPropertiesConfig;

  /** Creates an instance of MailService. */
  @Autowired
  public MailService(MailPropertiesConfig mailPropertiesConfig) {
    this.mailPropertiesConfig = mailPropertiesConfig;
  }

  private MailService() {}

  /**
   * Send a notification to a user with his job list.
   *
   * @param user the {@link UserEntity} destiny
   * @param jobs list of {@link Job} with all found jobs
   * @return True if the email was sent, false otherwise
   */
  public boolean jobNotification(UserEntity user, Set<Job> jobs) {
    if (!mailPropertiesConfig.getEnabled().equals("true")) {
      log.info("Mail system not enabled for job notifications!! Leaving!");
      return false;
    }

    Properties propvls = System.getProperties();
    propvls.setProperty("mail.smtp.host", mailPropertiesConfig.getSmtpHost());
    if (!Objects.isNull(mailPropertiesConfig.getDebug())
        && !mailPropertiesConfig.getDebug().isBlank()) {
      propvls.put("mail.debug", mailPropertiesConfig.getDebug());
    }
    propvls.put("mail.smtp.port", mailPropertiesConfig.getSmtpPort());
    if (!Objects.isNull(mailPropertiesConfig.getSmtpAuth())
        && !mailPropertiesConfig.getSmtpAuth().isBlank()) {
      propvls.put("mail.smtp.auth", mailPropertiesConfig.getSmtpAuth());
    }
    if (!Objects.isNull(mailPropertiesConfig.getSmtpStarttlsEnabled())
        && !mailPropertiesConfig.getSmtpStarttlsEnabled().isBlank()) {
      propvls.put("mail.smtp.starttls.enable", mailPropertiesConfig.getSmtpStarttlsEnabled());
    }
    propvls.put("mail.smtp.socketFactory.class", mailPropertiesConfig.getSmtpSocketFactoryClass());

    Authenticator authenticator =
        new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(
                mailPropertiesConfig.getFromAddress(), mailPropertiesConfig.getFromPassword());
          }
        };

    Session session = Session.getDefaultInstance(propvls);

    // Send one email containing all found jobs.
    try {
      URL url = getClass().getClassLoader().getResource("email_template.html");
      if (url == null) {
        throw new RuntimeException("Unable to retrieve email_template.html file!");
      }
      File file = new File(url.getFile());
      String mailTemplate = new String(Files.readAllBytes(file.toPath()));

      // user's name
      mailTemplate = mailTemplate.replace("__PRIMEIRO_NOME__", user.getFirstName());

      final String jobTemplate =
          """
          <div class="content">
            <table>
              <tr>
                <td
                  class="small"
                  width="20%"
                  style="vertical-align: top; padding-right:10px;"
                >
                  <img
                    width="32"
                    src="http://www.fastvagas.com.br/user/img/fast_logo2a.png"
                    alt="Nova vaga detectada"
                  />
                </td>
                <td>
                  <h4>__JOB_NAME__</h4>
                  <p>__JOB_DETAILS__</p>
                  <a
                    href="__JOB_URL__"
                    target="_blank"
                    title="Abrir link"
                    class="btn"
                  >
                    Acessar o site da vaga
                  </a>
                </td>
              </tr>
            </table>
          </div>
          """;

      for (Job portalJob : jobs) {
        String jobDetails = portalJob.getJobDescription();
        if (portalJob.getCompanyName() != null && !portalJob.getCompanyName().isEmpty()) {
          boolean addDot =
              !portalJob.getJobDescription().endsWith(".")
                  && !portalJob.getJobDescription().endsWith("!");

          if (addDot) {
            jobDetails += ".";
          }

          jobDetails += " Empresa: " + portalJob.getCompanyName();
        }

        if (portalJob.getPublishedAt() != null) {
          boolean addDot = !jobDetails.endsWith(".") && !jobDetails.endsWith("!");

          if (addDot) {
            jobDetails += ".";
          }

          if (!portalJob.getPublishedAt().isBlank()) {
            jobDetails += " Publicado em: " + portalJob.getPublishedAt() + ".";
          }
        }

        String job = jobTemplate;
        job = job.replace("__JOB_NAME__", portalJob.getJobTitle());
        job = job.replace("__JOB_DETAILS__", jobDetails);
        job = job.replace("__JOB_URL__", portalJob.getJobUrl());

        mailTemplate = mailTemplate.replace("__CONTEUDO_VAGA__", job + "__CONTEUDO_VAGA__");
      }

      mailTemplate = mailTemplate.replace("__CONTEUDO_VAGA__", "");

      Message message = new MimeMessage(session);
      message.setFrom(
          new InternetAddress(mailPropertiesConfig.getFromAddress(), "Avisos de Vagas"));
      message.setReplyTo(
          new Address[] {new InternetAddress(mailPropertiesConfig.getFromAddress())});
      message.setRecipient(
          RecipientType.TO, new InternetAddress(user.getEmail(), user.getFirstName()));
      message.setRecipient(
          RecipientType.CC,
          new InternetAddress(
              mailPropertiesConfig.getAdminToAddress(), mailPropertiesConfig.getAdminToName()));
      message.setSubject(jobs.size() + " new jobs found!");
      message.setContent(mailTemplate, "text/html; charset=UTF-8");
      message.setSentDate(new java.util.Date());

      Transport.send(message);
      log.info("Notification email successfully sent to the user {}!", user.getEmail());
      return true;
    } catch (MessagingException | IOException | NullPointerException me) {
      me.printStackTrace();
      throw new RuntimeException("Unable to notify user's new job for " + user.getEmail());
    }
  }

  public void sendLogsToAdmin(String content) {
    log.info("Logs: {}", content);
  }
}
