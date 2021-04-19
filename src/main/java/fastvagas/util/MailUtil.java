package fastvagas.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MailUtil {

    public static void validateEmailAddress(String email) throws AddressException {
        InternetAddress emailAddress = new InternetAddress(email);
        emailAddress.validate();
    }
}
