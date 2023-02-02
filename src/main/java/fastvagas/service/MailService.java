package fastvagas.service;

import fastvagas.entity.Job;
import fastvagas.entity.User;
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
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** This class contains methods to send mail notifications. */
@Slf4j
@Setter
@Service
public class MailService {

  @Value("${config.mail.enabled}")
  private String enabled;

  @Value("${config.mail.from.address}")
  private String fromAdress;

  @Value("${config.mail.from.name}")
  private String fromName;

  @Value("${config.mail.from.password}")
  private String fromPassword;

  @Value("${config.mail.debug}")
  private String debug;

  @Value("${config.mail.smtp.host}")
  private String smtpHost;

  @Value("${config.mail.smtp.port}")
  private String smtpPort;

  @Value("${config.mail.smtp.auth}")
  private String smtpAuth;

  @Value("${config.mail.smtp.starttls.enable}")
  private String startTlsEnable;

  @Value("${config.mail.smtp.socketFactory.class}")
  private String socketFactoryClass;

  @Value("${config.mail.admin.to.address}")
  private String adminToAddress;

  @Value("${config.mail.admin.to.name}")
  private String adminToName;

  @Value("${config.mail.admin.cc.address}")
  private String adminCcAddress;

  @Value("${config.mail.admin.cc.name}")
  private String adminCcName;

  /** Creates an instance of MailService. */
  @Autowired
  public MailService() {}

  /**
   * Send a notification to a user with his job list.
   *
   * @param user the {@link User} destiny
   * @param jobs list of {@link Job} with all found jobs
   */
  public void jobNotification(User user, Set<Job> jobs) {
    if (!enabled.equals("true")) {
      log.info("Mail system not enabled for job notifications!! Leaving!");
      return;
    }

    Properties propvls = System.getProperties();
    propvls.setProperty("mail.smtp.host", smtpHost);
    if (!Objects.isNull(debug) && debug.isBlank()) {
      propvls.put("mail.debug", debug);
    }
    propvls.put("mail.smtp.port", smtpPort);
    if (!Objects.isNull(smtpAuth) && smtpAuth.isBlank()) {
      propvls.put("mail.smtp.auth", smtpAuth);
    }
    if (!Objects.isNull(startTlsEnable) && startTlsEnable.isBlank()) {
      propvls.put("mail.smtp.starttls.enable", startTlsEnable);
    }
    propvls.put("mail.smtp.socketFactory.class", socketFactoryClass);

    Session session =
        Session.getDefaultInstance(
            propvls,
            new Authenticator() {
              @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAdress, fromPassword);
              }
            });

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
      message.setFrom(new InternetAddress(fromAdress, "Avisos de Vagas"));
      message.setReplyTo(new Address[] {new InternetAddress(fromAdress)});
      message.setRecipient(
          RecipientType.TO, new InternetAddress(user.getEmail(), user.getFirstName()));
      message.setRecipient(RecipientType.CC, new InternetAddress(adminToAddress, adminToName));
      message.setSubject(jobs.size() + " nova(s) vaga(s) encontrada(s)!");
      message.setContent(mailTemplate, "text/html; charset=UTF-8");
      message.setSentDate(new java.util.Date());

      Transport.send(message);
      log.info("Notification email successfully sent to the user {}!", user.getEmail());
    } catch (MessagingException | IOException | NullPointerException me) {
      me.printStackTrace();
      throw new RuntimeException("Unable to notify user's new job for " + user.getEmail());
    }
  }

  public void sendLogsToAdmin(String content) {
    log.info("Logs: {}", content);
  }
}
