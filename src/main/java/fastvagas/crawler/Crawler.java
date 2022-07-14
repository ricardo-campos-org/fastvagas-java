package fastvagas.crawler;

import fastvagas.entity.PortalJob;
import org.jsoup.nodes.Document;

import java.util.List;

public interface Crawler {

    List<PortalJob> findJobs(Document document);
}
