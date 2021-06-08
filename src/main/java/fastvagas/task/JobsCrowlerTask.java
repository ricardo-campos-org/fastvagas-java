package fastvagas.task;

import fastvagas.service.CrowlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JobsCrowlerTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final Logger logger = LoggerFactory.getLogger(JobsCrowlerTask.class);
    private final long SECOND = 1000;
    private final long MINUTE = SECOND * 60;
    private final long HOUR = MINUTE * 60;

    @Autowired
    private CrowlerService crowlerService;

    @Scheduled(cron = "0 0 */2 * * *")
    public void reportCurrentTime() {
        logger.info("Starting jobs crowler at {}", dateFormat.format(new Date()));

        crowlerService.start();
    }
}
