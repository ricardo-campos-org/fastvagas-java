package fastvagas.crowler;

import fastvagas.crowler.shared.InfoJobs;
import fastvagas.dal.entity.PortalJob;
import org.jsoup.nodes.Document;

import java.util.List;

public class JoinvilleInfoJobs implements Crowler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        InfoJobs infoJobs = new InfoJobs();
        return infoJobs.findJobs(document);
    }
}
