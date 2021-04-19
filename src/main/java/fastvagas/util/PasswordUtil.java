package fastvagas.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

public class PasswordUtil {
    // https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java

    private final static int iterations = 20*1000;
    private final static int saltLen = 32;
    private final static int desiredKeyLen = 256;

    public static String getSaltedHash(String password) {
        try {
            byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
            return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static boolean check(String password, String stored) {
        String[] saltAndHash = stored.split("\\$");
        if (saltAndHash.length != 2) {
            throw new IllegalArgumentException("The stored password must have the form 'salt$hash'");
        }

        String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    private static String hash(String password, byte[] salt) {
        try {
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("Empty password are not supported.");
            }

            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen
            ));

            return Base64.encodeBase64String(key.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
