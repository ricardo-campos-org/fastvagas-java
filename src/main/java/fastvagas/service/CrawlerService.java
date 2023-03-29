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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** This class contains all routines related to a crawler job. */
@Setter
@Slf4j
@Service
@NoArgsConstructor
public class CrawlerService {

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
    List<Portal> portals = portalRepository.findAll();
    if (portals.isEmpty()) {
      log.info("Zero portals with active users. Leaving..");
      return;
    }

    StringBuilder logsToWrite = new StringBuilder();
    LocalDateTime oneMonthPast = LocalDateTime.now().minusMonths(1L);
    char cr = '\n';

    Set<Portal> portalSet = new HashSet<>(portalRepository.findAllByEnabled(Boolean.TRUE));
    portalSet.forEach(
        portal -> {
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
        });

    mailService.sendLogsToAdmin(logsToWrite.toString());
  }

  /**
   * Finds a list of jobs based on a Portal and a City.
   *
   * @param portal {@link Portal} to be used as search parameter
   * @return A list of {@link Job} containing all jobs found or an empty list
   */
  public List<Job> findJobs(Portal portal) {
    try {
      Document doc = Jsoup.connect(portal.getSearchUrl()).ignoreHttpErrors(true).get();

      String cityName = StringUtil.replaceToPlainText(portal.getCity().replace(" ", ""));
      String portalName = StringUtil.replaceToPlainText(portal.getName().replace(" ", ""));

      Crawler crawler = CrawlerFactory.createInstance(cityName + portalName);
      if (Objects.isNull(crawler)) {
        return new ArrayList<>();
      }

      return crawler.findJobs(doc);

    } catch (IOException ioe) {
      log.error("IOException: {}", ioe.getLocalizedMessage());
    } catch (ClassCastException cce) {
      log.error("ClassCastException: {}", cce.getLocalizedMessage());
    }

    return new ArrayList<>();
  }
}
