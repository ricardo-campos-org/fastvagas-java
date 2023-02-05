package fastvagas.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.mail")
@Getter
@Setter
public class MailPropertiesConfig {

  private String enabled;

  private String fromAdress;

  private String fromName;

  private String fromPassword;

  private String debug;

  private String smtpHost;

  private String smtpPort;

  private String smtpAuth;

  private String smtpStarttlsEnabled;

  private String smtpSocketFactoryClass;

  private String adminToAddress;

  private String adminToName;

  private String adminCcAddress;

  private String adminCcName;
}
