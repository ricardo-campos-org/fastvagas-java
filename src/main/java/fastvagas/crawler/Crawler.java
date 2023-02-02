package fastvagas.crawler;

import fastvagas.entity.Job;
import java.util.List;
import org.jsoup.nodes.Document;

/** This class represents an interface of all classes that search for jobs. */
public interface Crawler {

  List<Job> findJobs(Document document);
}
