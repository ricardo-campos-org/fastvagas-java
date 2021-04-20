package fastvagas.service;

import fastvagas.dal.entity.Contact;
import fastvagas.dal.entity.PortalJob;
import fastvagas.exception.SendMailException;
import fastvagas.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class MailService {

    public void send(Contact contact) {
        String origemEmail = "contato.fastvagas@ricardocampos.blog";
        String origemEmailSenha = "";
        String adminEmail = "ricardo@ricardocampos.blog";

        Properties propvls = System.getProperties();
        propvls.setProperty("mail.smtp.host", "smtp.zoho.com");
        propvls.put("mail.debug", "true");
        propvls.put("mail.smtp.port", "587");
        propvls.put("mail.smtp.auth", "true");
        propvls.put("mail.smtp.starttls.enable", "true");
        propvls.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(propvls, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(origemEmail, origemEmailSenha);
            }
        });

        // Envia um e-mail para alguém do fastvagas
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(contact.getEmail(), contact.getName()));
            message.setReplyTo(new Address[]{ new InternetAddress(contact.getEmail(), contact.getName()) });
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(adminEmail, "Ricardo Campos"));
            message.setSubject("Contato Fastvagas. Assunto: " + contact.getSubject());
            message.setText("Novo contato feito a partir do site: " + contact.getMessage());
            message.setSentDate(new java.util.Date());

            Transport.send(message);
            Logger.getLogger(getClass().getName()).info("E-mail admin enviado com sucesso!");
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
            message.setFrom(new InternetAddress(origemEmail, "Contato Fast Vagas"));
            message.setReplyTo(new Address[]{ new InternetAddress(origemEmail, "Contato Fast Vagas")});
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(contact.getEmail(), contact.getName()));
            message.setSubject("Contato Fastvagas. Assunto: " + contact.getSubject());
            message.setSentDate(new java.util.Date());
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);
            Logger.getLogger(getClass().getName()).info("E-mail para a pessoa enviado com sucesso!");
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
        String origemEmail = "contato.fastvagas@ricardocampos.blog";
        String origemEmailSenha = "";

        Properties propvls = System.getProperties();
        propvls.setProperty("mail.smtp.host", "smtp.zoho.com");
        propvls.put("mail.debug", "true");
        propvls.put("mail.smtp.port", "587");
        propvls.put("mail.smtp.auth", "true");
        propvls.put("mail.smtp.starttls.enable", "true");
        propvls.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propvls.put("mail.mime.charset", "UTF-8");

        Session session = Session.getDefaultInstance(propvls, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(origemEmail, origemEmailSenha);
            }
        });

        // Envia um e-mail para a pessoa que fez o contato
        try {
            File file = new File(getClass().getClassLoader().getResource("email_template.html").getFile());
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
            Logger.getLogger(getClass().getName()).info("E-mail para a pessoa enviado com sucesso!");
        } catch (MessagingException me) {
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                me,
                "Erro ao enviar e-mail para o usuário que solicitou: " + me.getLocalizedMessage()
            );
        } catch (NullPointerException npe) {
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                npe,
                "Erro ao enviar e-mail para o usuário que solicitou: " + npe.getLocalizedMessage()
            );
        } catch (IOException ioe) {
            throw new SendMailException(
                "Problema no servidor ao registrar contato.",
                ioe,
                "Erro ao enviar e-mail para o usuário que solicitou: " + ioe.getLocalizedMessage()
            );
        }
    }
}
