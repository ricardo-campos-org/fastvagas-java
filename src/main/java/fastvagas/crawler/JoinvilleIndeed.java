package fastvagas.crawler;

import fastvagas.crawler.shared.Indeed;
import fastvagas.entity.Job;
import java.util.List;
import org.jsoup.nodes.Document;

public class JoinvilleIndeed implements Crawler {

  @Override
  public List<Job> findJobs(Document document) {
    Indeed indeed = new Indeed();
    return indeed.findJobs(document);
  }
}
