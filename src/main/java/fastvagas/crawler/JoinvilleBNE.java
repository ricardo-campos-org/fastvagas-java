package fastvagas.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import fastvagas.entity.PortalJob;

public class JoinvilleBNE implements Crawler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        return new ArrayList<>();
    }
}
