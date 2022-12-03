package fastvagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "fastvagas")
@EnableScheduling
public class PostgressApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgressApplication.class, args);
    }
}
