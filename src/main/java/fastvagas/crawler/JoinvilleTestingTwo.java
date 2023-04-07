package fastvagas.crawler;

import fastvagas.entity.Job;
import java.util.List;
import org.jsoup.nodes.Document;

/** This class contain the method to read jobs from Joinville Testing. */
public class JoinvilleTestingTwo implements Crawler {

  @Override
  public List<Job> findJobs(Document document) {
    return List.of();
  }
}
