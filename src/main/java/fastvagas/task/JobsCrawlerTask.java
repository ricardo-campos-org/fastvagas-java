package fastvagas.task;

import fastvagas.service.CrawlerService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobsCrawlerTask {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Autowired private CrawlerService crawlerService;

  @Scheduled(cron = "0 0 */2 * * *")
  public void reportCurrentTime() {
    log.info("Starting jobs crawler at {}", dateFormat.format(new Date()));

    crawlerService.start();
  }
}
