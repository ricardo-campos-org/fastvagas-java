package fastvagas.task;

import fastvagas.service.CrawlerService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** This class contains the scheduled method that is triggered to find jobs. */
@Slf4j
@Setter
@Component
@NoArgsConstructor
public class JobsCrawlerTask {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  private CrawlerService crawlerService;

  /**
   * Creates a JobsCrawlerTask instance.
   *
   * @param crawlerService crawlerService instance
   */
  @Autowired
  public JobsCrawlerTask(CrawlerService crawlerService) {
    this.crawlerService = crawlerService;
  }

  /**
   * This method starts the crawling task. Every two hours.
   */
  @Scheduled(cron = "0 0 */2 * * *")
  public void reportCurrentTime() {
    log.info("Starting jobs crawler at {}", dateFormat.format(new Date()));

    crawlerService.start();
  }
}
