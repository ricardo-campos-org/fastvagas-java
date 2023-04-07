package fastvagas.service;

import fastvagas.crawler.Crawler;
import fastvagas.crawler.CrawlerFactory;
import fastvagas.entity.Job;
import fastvagas.entity.Portal;
import fastvagas.repository.JobRepository;
import fastvagas.repository.PortalRepository;
import fastvagas.util.PortalJobUtil;
import fastvagas.util.StringUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** This class contains all routines related to a crawler job. */
@Slf4j
@Service
public class CrawlerService {

  private CrawlerService() {}

  private PortalRepository portalRepository;
  private JobRepository jobRepository;
  private MailService mailService;

  /**
   * Creates an instance of CrawlerService.
   *
   * @param portalRepository {@link PortalRepository} instance
   * @param jobRepository {@link JobRepository} instance
   * @param mailService {@link MailService} instance
   */
  @Autowired
  public CrawlerService(
      PortalRepository portalRepository, JobRepository jobRepository, MailService mailService) {
    this.portalRepository = portalRepository;
    this.jobRepository = jobRepository;
    this.mailService = mailService;
  }

  /** Starts the process of seeking for jobs. */
  @Transactional()
  public void start() {
    List<Portal> portals = portalRepository.findAllByEnabled(Boolean.TRUE);
    if (portals.isEmpty()) {
      log.info("Zero portals with active users. Leaving..");
      return;
    }

    StringBuilder logsToWrite = new StringBuilder();
    LocalDateTime oneMonthPast = LocalDateTime.now().minusMonths(1L);
    char cr = '\n';

    for (Portal portal : portals) {
      String template = "Starting crawler on %s - %s/%s.";
      String message =
          String.format(template, portal.getName(), portal.getCity(), portal.getState());

      log.info(message);
      logsToWrite.append(template).append(cr);

      List<Job> jobList = findJobs(portal);
      if (jobList.isEmpty()) {
        message = "No new jobs found!";
        log.info(message);
        logsToWrite.append(message).append(cr);
      } else {
        message = jobList.size() + " new job(s) found on the portal.";
        logsToWrite.append(message).append(cr);

        // Last 30 days jobs for this portal - to compare to see if it's new
        List<Job> savedList =
            jobRepository.findAllByPortalId(portal.getId()).stream()
                .filter(x -> x.getCreatedAt().isAfter(oneMonthPast))
                .toList();

        Map<String, Job> portalJobMap = PortalJobUtil.listToMapByUrl(savedList);

        message = portalJobMap.size() + " job(s) already saved at this portal.";
        logsToWrite.append(message).append(cr);

        List<Job> jobToSave = new ArrayList<>();
        message = "Iterating over job list received, looking for new jobs...";
        log.info(message);
        logsToWrite.append(message).append(cr);

        for (Job job : jobList) {
          // Save the job, if it's not already saved
          if (!portalJobMap.containsKey(job.getJobUrl())) {
            job.setPortalId(portal.getId());
            jobToSave.add(job);
          }
        }

        message = jobToSave.size() + " new job(s) found. Registering...";
        log.info(message);
        logsToWrite.append(message).append(cr);
        jobRepository.saveAll(jobToSave);

        message = "Done crawling for this portal!";
        log.info(message);
        logsToWrite.append(message).append(cr);
      }
    }

    mailService.sendLogsToAdmin(logsToWrite.toString());
  }

  private List<Job> findJobs(Portal portal) {
    Document doc = null;
    try {
      doc = Jsoup.connect(portal.getSearchUrl()).ignoreHttpErrors(true).get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String cityName = StringUtil.replaceToPlainText(portal.getCity().replace(" ", ""));
    String portalName = StringUtil.replaceToPlainText(portal.getName().replace(" ", ""));

    Crawler crawler = CrawlerFactory.createInstance(cityName + portalName);
    if (Objects.isNull(crawler)) {
      return List.of();
    }

    return crawler.findJobs(doc);
  }
}
