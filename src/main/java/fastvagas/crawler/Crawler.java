package fastvagas.crawler;

import fastvagas.entity.PortalJob;
import java.util.List;
import org.jsoup.nodes.Document;

public interface Crawler {

  List<PortalJob> findJobs(Document document);
}
