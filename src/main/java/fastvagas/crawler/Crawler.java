package fastvagas.crawler;

import fastvagas.entity.Job;
import java.util.List;
import org.jsoup.nodes.Document;

public interface Crawler {

  List<Job> findJobs(Document document);
}
