package fastvagas.crowler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import fastvagas.dal.entity.PortalJob;

public class JoinvilleBNE implements Crowler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        return new ArrayList<>();
    }
}
