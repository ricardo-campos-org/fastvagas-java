package fastvagas.task;

import fastvagas.service.CrawlerService;
import fastvagas.service.JobService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** This class contains the scheduled method that is triggered to find jobs. */
@Slf4j
@Setter
@Component
@NoArgsConstructor
public class JobsCrawlerTask {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  private CrawlerService crawlerService;
  private JobService jobService;

  /**
   * Creates a JobsCrawlerTask instance.
   *
   * @param crawlerService {@link CrawlerService} instance
   * @param jobService {@link JobService} instance
   */
  @Autowired
  JobsCrawlerTask(CrawlerService crawlerService, JobService jobService) {
    this.crawlerService = crawlerService;
    this.jobService = jobService;
  }

  /** This method starts the crawling task. Every two hours. */
  // @Scheduled(cron = "0 0 */2 * * *")
  public void reportCurrentTime() {
    log.info("Starting jobs crawler at {}", dateFormat.format(new Date()));
    crawlerService.start();
    log.info("Finished jobs crawler at {}", dateFormat.format(new Date()));

    log.info("Starting user processing jobs at {}", dateFormat.format(new Date()));
    jobService.processAllUsers();
    log.info("Finished user processing jobs at {}", dateFormat.format(new Date()));
  }
}
