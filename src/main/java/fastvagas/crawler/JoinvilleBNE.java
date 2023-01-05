package fastvagas.crawler;

import fastvagas.entity.PortalJob;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;

public class JoinvilleBNE implements Crawler {

  @Override
  public List<PortalJob> findJobs(Document document) {
    return new ArrayList<>();
  }
}
