package fastvagas.crowler.shared;

import fastvagas.crowler.Crowler;
import fastvagas.dal.entity.PortalJob;
import org.jsoup.nodes.Document;

import java.util.List;

public class Indeed implements Crowler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        return null;
    }
}
