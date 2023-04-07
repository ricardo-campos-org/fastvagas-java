package fastvagas.task;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import fastvagas.service.CrawlerService;
import fastvagas.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JobsCrawlerTaskTest {

  @Mock CrawlerService crawlerService;

  @Mock JobService jobService;

  private JobsCrawlerTask jobsCrawlerTask;

  @BeforeEach
  void setup() {
    jobsCrawlerTask = new JobsCrawlerTask(crawlerService, jobService);
  }

  @Test
  @DisplayName("reportCurrentTimeTest")
  void reportCurrentTimeTest() {

    jobsCrawlerTask.startScheduledTask();

    verify(crawlerService, times(1)).start();
    verify(jobService, times(1)).processAllUsers();
  }
}
