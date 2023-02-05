package fastvagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** This class represents the starting point of the application. */
@SpringBootApplication
// @EnableScheduling
public class FastJobsApplication {

  /**
   * Entry point of the service.
   *
   * @param args An array of {@link String}
   */
  public static void main(String[] args) {
    SpringApplication.run(FastJobsApplication.class, args);
  }
}
