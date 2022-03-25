package fastvagas.crowler;

import fastvagas.crowler.shared.Indeed;
import fastvagas.data.entity.PortalJob;
import org.jsoup.nodes.Document;

import java.util.List;

public class JoinvilleIndeed implements Crowler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        Indeed indeed = new Indeed();
        return indeed.findJobs(document);
    }
}
