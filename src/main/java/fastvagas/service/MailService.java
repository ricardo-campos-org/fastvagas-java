package fastvagas.service;

import fastvagas.config.MailConfig;
import fastvagas.dal.entity.Contact;
import fastvagas.dal.entity.PortalJob;
import fastvagas.exception.SendMailException;
import fastvagas.util.DateUtil;
import fastvagas.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    MailConfig mailConfig;

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public void send(Contact contact) {
        if (!mailConfig.getEnabled()) {
            logger.info("Mail system not enabled!! Leaving!");
            return;
        }

        String origemEmail = mailConfig.getFromAdress();
        String origemEmailSenha = mailConfig.getFromPassword();
        String origemNome = mailConfig.getFromName();
        String adminEmail = mailConfig.getAdminToAddress();

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

        Session session = Session.getInstance(propvls, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(origemEmail, origemEmailSenha);
            }
        });

        // Envia um e-mail para alguém do fastvagas
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(origemEmail, origemNome));
            message.setReplyTo(new Address[]{ new InternetAddress(origemEmail, origemNome) });
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(adminEmail, mailConfig.getAdminToName()));
            if (ObjectUtil.hasValue(mailConfig.getAdminCcAddress())) {
                message.setRecipient(Message.RecipientType.CC, new InternetAddress(mailConfig.getAdminCcAddress(), mailConfig.getAdminCcName()));
            }
            message.setSubject("Contato Onde Tem Vagas. Assunto: " + contact.getSubject());
            message.setText("Novo contato feito a partir do site: " + contact.getMessage());
            message.setSentDate(new java.util.Date());

            Transport.send(message);
            logger.info("E-mail admin enviado com sucesso!");
        } catch (MessagingException | UnsupportedEncodingException me) {
            me.printStackTrace();
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                me,
                "Erro ao enviar e-mail para o admin: " + me.getLocalizedMessage()
            );
        }

        // Envia um e-mail para a pessoa que fez o contato
        try {
            String content = "Obrigado por entrar em contato. " +
                    "Em breve responderemos!<br><br>Sua mensagem:<br>" + contact.getMessage();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(origemEmail, origemNome));
            message.setReplyTo(new Address[]{ new InternetAddress(origemEmail, origemNome)});
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(contact.getEmail(), contact.getName()));
            message.setSubject("Contato Onte Tem Vagas. Assunto: " + contact.getSubject());
            message.setSentDate(new java.util.Date());
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);
            logger.info("E-mail para a pessoa enviado com sucesso!");
        } catch (MessagingException | UnsupportedEncodingException me) {
            me.printStackTrace();
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                me,
                "Erro ao enviar e-mail para o usuário que solicitou: " + me.getLocalizedMessage()
            );
        }
    }

    public void jobNotification(String name, String email, List<PortalJob> portalJobs) {
        if (!mailConfig.getEnabled()) {
            logger.info("Mail system not enabled for job notifications!! Leaving!");
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

        Session session = Session.getDefaultInstance(propvls, new Authenticator() {
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
                        "Problema ao obter template para envio!",
                        "Problema ao obter template para envio!"
                );
            }
            File file = new File(url.getFile());
            String mailTemplate = new String(Files.readAllBytes(file.toPath()));

            // nome da pessoa
            mailTemplate = mailTemplate.replace("__PRIMEIRO_NOME__", name);

            final String jobTemplate =
                "<div class=\"content\">\n" +
                "   <table>\n" +
                "       <tr>\n" +
                "           <td class=\"small\" width=\"20%\" style=\"vertical-align: top; padding-right:10px;\">\n" +
                "               <img width=\"32\" src=\"http://www.fastvagas.com.br/user/img/fast_logo2a.png\" alt=\"Nova vaga detectada\"/>\n" +
                "           </td>\n" +
                "           <td>\n" +
                "               <h4>__JOB_NAME__</h4>\n" +
                "               <p>__JOB_DETAILS__</p>\n" +
                "               <a href=\"__JOB_URL__\" target=\"_blank\" title=\"Abrir link no navegador\" class=\"btn\">Acessar o site da vaga</a>\n" +
                "           </td>\n" +
                "       </tr>\n" +
                "   </table>\n" +
                "</div>";

            for (PortalJob portalJob : portalJobs) {
                String jobDetails = portalJob.getDescription();
                if (portalJob.getCompany_name() != null && !portalJob.getCompany_name().isEmpty()) {
                    boolean addDot =
                            !portalJob.getDescription().endsWith(".")
                            && !portalJob.getDescription().endsWith("!");

                    if (addDot) {
                        jobDetails += ".";
                    }

                    jobDetails += " Empresa: " + portalJob.getCompany_name();
                }

                if (portalJob.getPublished_at() != null) {
                    boolean addDot = !jobDetails.endsWith(".") && !jobDetails.endsWith("!");

                    if (addDot) {
                        jobDetails += ".";
                    }

                    jobDetails += " Publicado em: " + DateUtil.formatDate(portalJob.getPublished_at()) + ".";
                }

                String job = jobTemplate;
                job = job.replace("__JOB_NAME__", portalJob.getName());
                job = job.replace("__JOB_DETAILS__", jobDetails);
                job = job.replace("__JOB_URL__", portalJob.getUrl());

                mailTemplate = mailTemplate.replace("__CONTEUDO_VAGA__", job + "__CONTEUDO_VAGA__");
            }

            mailTemplate = mailTemplate.replace("__CONTEUDO_VAGA__", "");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(origemEmail, "Avisos de Vagas"));
            message.setReplyTo(new Address[]{ new InternetAddress(origemEmail)});
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(portalJobs.size() + " nova(s) vaga(s) encontrada(s)!");
            message.setContent(mailTemplate, "text/html; charset=UTF-8");
            message.setSentDate(new java.util.Date());

            Transport.send(message);
            logger.info("E-mail para a pessoa enviado com sucesso!");
        } catch (MessagingException | IOException | NullPointerException me) {
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                me,
                "Erro ao enviar e-mail para o usuário que solicitou: " + me.getLocalizedMessage()
            );
        }
    }
}
