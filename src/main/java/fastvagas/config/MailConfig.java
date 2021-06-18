package fastvagas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {

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

    public Boolean getEnabled() {
        return "true".equals(enabled);
    }

    public String getFromAdress() {
        return fromAdress;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromPassword() {
        return fromPassword;
    }

    public String getDebug() {
        return debug;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public String getSmtpAuth() {
        return smtpAuth;
    }

    public String getStartTlsEnable() {
        return startTlsEnable;
    }

    public String getSocketFactoryClass() {
        return socketFactoryClass;
    }

    public String getAdminToAddress() {
        return adminToAddress;
    }

    public String getAdminToName() {
        return adminToName;
    }

    public String getAdminCcAddress() {
        return adminCcAddress;
    }

    public String getAdminCcName() {
        return adminCcName;
    }

    public String getSmtpHost() {
        return smtpHost;
    }
}
