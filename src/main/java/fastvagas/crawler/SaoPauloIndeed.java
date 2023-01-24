package fastvagas.crawler;

import fastvagas.crawler.shared.Indeed;
import fastvagas.entity.PortalJob;
import java.util.List;
import org.jsoup.nodes.Document;

public class SaoPauloIndeed implements Crawler {

  @Override
  public List<PortalJob> findJobs(Document document) {
    Indeed indeed = new Indeed();
    return indeed.findJobs(document);
  }
}
