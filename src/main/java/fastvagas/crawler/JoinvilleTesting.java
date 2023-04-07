package fastvagas.crawler;

import fastvagas.entity.Job;
import java.time.LocalDateTime;
import java.util.List;
import org.jsoup.nodes.Document;

/** This class contain the method to read jobs from Joinville Testing. */
public class JoinvilleTesting implements Crawler {

  @Override
  public List<Job> findJobs(Document document) {
    Job job = new Job();
    job.setId(1L);
    job.setJobTitle("Java Developer");
    job.setCompanyName("Encora");
    job.setJobType("Full-time");
    job.setJobDescription("Code for run, but all the time");
    job.setPublishedAt("Monday, 7th April, 2023");
    job.setJobUrl("http://test-job.com/work-here-with-java");
    job.setPortalId(1L);
    job.setCreatedAt(LocalDateTime.now());

    return List.of(job);
  }
}
