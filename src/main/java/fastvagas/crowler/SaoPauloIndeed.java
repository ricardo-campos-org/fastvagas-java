package fastvagas.crowler;

import fastvagas.crowler.shared.Indeed;
import fastvagas.dal.entity.PortalJob;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SaoPauloIndeed implements Crowler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<PortalJob> findJobs(Document document) {
        Indeed indeed = new Indeed();
        return indeed.findJobs(document);
    }
}
