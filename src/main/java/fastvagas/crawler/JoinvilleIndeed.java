package fastvagas.crawler;

import fastvagas.crawler.shared.Indeed;
import fastvagas.entity.PortalJob;
import org.jsoup.nodes.Document;

import java.util.List;

public class JoinvilleIndeed implements Crawler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        Indeed indeed = new Indeed();
        return indeed.findJobs(document);
    }
}
