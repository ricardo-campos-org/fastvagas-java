package fastvagas.crawler;

import fastvagas.crawler.shared.InfoJobs;
import fastvagas.entity.PortalJob;
import org.jsoup.nodes.Document;

import java.util.List;

public class JoinvilleInfoJobs implements Crawler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        InfoJobs infoJobs = new InfoJobs();
        return infoJobs.findJobs(document);
    }
}
