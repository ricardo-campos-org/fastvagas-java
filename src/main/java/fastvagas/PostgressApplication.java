package fastvagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "fastvagas")
//@EnableScheduling
public class PostgressApplication {

    public static void main(String[] args) {
        //System.out.println("123456: " + new BCryptPasswordEncoder().encode("123456"));
        SpringApplication.run(PostgressApplication.class, args);
    }
}
