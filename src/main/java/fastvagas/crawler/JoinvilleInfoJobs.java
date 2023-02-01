package fastvagas.crawler;

import fastvagas.crawler.shared.InfoJobs;
import fastvagas.entity.Job;
import java.util.List;
import org.jsoup.nodes.Document;

public class JoinvilleInfoJobs implements Crawler {

  @Override
  public List<Job> findJobs(Document document) {
    InfoJobs infoJobs = new InfoJobs();
    return infoJobs.findJobs(document);
  }
}
