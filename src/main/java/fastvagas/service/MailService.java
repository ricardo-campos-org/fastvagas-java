package fastvagas.service;

import fastvagas.config.MailConfig;
import fastvagas.entity.PortalJob;
import fastvagas.exception.SendMailException;
import fastvagas.util.ObjectUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {

  @Autowired MailConfig mailConfig;

  public void jobNotification(String name, String email, List<PortalJob> portalJobs) {
    if (!mailConfig.getEnabled()) {
      log.info("Mail system not enabled for job notifications!! Leaving!");
      return;
    }

    String origemEmail = mailConfig.getFromAdress();
    String origemEmailSenha = mailConfig.getFromPassword();

    Properties propvls = System.getProperties();
    propvls.setProperty("mail.smtp.host", mailConfig.getSmtpHost());
    if (ObjectUtil.hasValue(mailConfig.getDebug())) {
      propvls.put("mail.debug", mailConfig.getDebug());
    }
    propvls.put("mail.smtp.port", mailConfig.getSmtpPort());
    if (ObjectUtil.hasValue(mailConfig.getSmtpAuth())) {
      propvls.put("mail.smtp.auth", mailConfig.getSmtpAuth());
    }
    if (ObjectUtil.hasValue(mailConfig.getStartTlsEnable())) {
      propvls.put("mail.smtp.starttls.enable", mailConfig.getStartTlsEnable());
    }
    propvls.put("mail.smtp.socketFactory.class", mailConfig.getSocketFactoryClass());

    Session session =
        Session.getDefaultInstance(
            propvls,
            new Authenticator() {
              @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(origemEmail, origemEmailSenha);
              }
            });

    // Envia um e-mail para a pessoa que fez o contato
    try {
      URL url = getClass().getClassLoader().getResource("email_template.html");
      if (url == null) {
        throw new SendMailException(
            "Problema ao obter template para envio!", "Problema ao obter template para envio!");
      }
      File file = new File(url.getFile());
      String mailTemplate = new String(Files.readAllBytes(file.toPath()));

      // nome da pessoa
      mailTemplate = mailTemplate.replace("__PRIMEIRO_NOME__", name);

      final String jobTemplate =
          "<div class=\"content\">\n"
              + "   <table>\n"
              + "       <tr>\n"
              + "           <td class=\"small\" width=\"20%\" style=\"vertical-align: top; padding-right:10px;\">\n"
              + "               <img width=\"32\" src=\"http://www.fastvagas.com.br/user/img/fast_logo2a.png\" alt=\"Nova vaga detectada\"/>\n"
              + "           </td>\n"
              + "           <td>\n"
              + "               <h4>__JOB_NAME__</h4>\n"
              + "               <p>__JOB_DETAILS__</p>\n"
              + "               <a href=\"__JOB_URL__\" target=\"_blank\" title=\"Abrir link no navegador\" class=\"btn\">Acessar o site da vaga</a>\n"
              + "           </td>\n"
              + "       </tr>\n"
              + "   </table>\n"
              + "</div>";

      for (PortalJob portalJob : portalJobs) {
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

          if (ObjectUtil.hasValue(portalJob.getPublishedAt())) {
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
      message.setFrom(new InternetAddress(origemEmail, "Avisos de Vagas"));
      message.setReplyTo(new Address[] {new InternetAddress(origemEmail)});
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
      message.setSubject(portalJobs.size() + " nova(s) vaga(s) encontrada(s)!");
      message.setContent(mailTemplate, "text/html; charset=UTF-8");
      message.setSentDate(new java.util.Date());

      Transport.send(message);
      log.info("E-mail para a pessoa enviado com sucesso!");
    } catch (MessagingException | IOException | NullPointerException me) {
      throw new SendMailException(
          "Problema no servidor ao registrar contato.",
          me,
          "Erro ao enviar e-mail para o usuário que solicitou: " + me.getLocalizedMessage());
    }
  }
}
