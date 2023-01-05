package fastvagas.crawler;

import fastvagas.crawler.shared.InfoJobs;
import fastvagas.entity.PortalJob;
import java.util.List;
import org.jsoup.nodes.Document;

public class JoinvilleInfoJobs implements Crawler {

  @Override
  public List<PortalJob> findJobs(Document document) {
    InfoJobs infoJobs = new InfoJobs();
    return infoJobs.findJobs(document);
  }
}
