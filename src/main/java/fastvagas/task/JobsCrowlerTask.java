package fastvagas.task;

import fastvagas.service.CrowlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class JobsCrowlerTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private CrowlerService crowlerService;

    @Scheduled(cron = "0 0 */2 * * *")
    public void reportCurrentTime() {
        log.info("Starting jobs crowler at {}", dateFormat.format(new Date()));

        crowlerService.start();
    }
}
