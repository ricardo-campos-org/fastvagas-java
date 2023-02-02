package fastvagas.crawler;

import fastvagas.crawler.shared.InfoJobs;
import fastvagas.entity.Job;
import java.util.List;
import org.jsoup.nodes.Document;

/** This class contain the method to read jobs from Info Jobs in Joinville-SC City. */
public class JoinvilleInfoJobs implements Crawler {

  /**
   * Finds all job from the first page of the website.
   *
   * @param document The HTML DOM element to be searched
   * @return A {@link List} of {@link Job} containing all found jobs
   */
  @Override
  public List<Job> findJobs(Document document) {
    InfoJobs infoJobs = new InfoJobs();
    return infoJobs.findJobs(document);
  }
}
