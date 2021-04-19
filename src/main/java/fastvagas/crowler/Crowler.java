package fastvagas.crowler;

import fastvagas.dal.entity.PortalJob;
import org.jsoup.nodes.Document;

import java.util.List;

public interface Crowler {

    List<PortalJob> findJobs(Document document);
}
