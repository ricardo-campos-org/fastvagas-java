package fastvagas.crawler;

import fastvagas.entity.Job;
import fastvagas.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/** This class contain the method to read jobs from Joinville Vagas in Joinville-SC City. */
@Slf4j
public class JoinvilleJoinvilleVagas implements Crawler {

  /**
   * Finds all job from the first page of the website.
   *
   * @param document The HTML DOM element to be searched
   * @return A {@link List} of {@link Job} containing all found jobs
   */
  @Override
  public List<Job> findJobs(Document document) {
    List<Job> jobList = new ArrayList<>();

    Element olJobListings = document.selectFirst(".job_listings");
    if (olJobListings == null) {
      log.warn("Nenhuma vaga encontrada para o seletor .job_listings");
      return jobList;
    }

    Elements liJobListing = olJobListings.select(".job_listing");

    for (Element li : liJobListing) {
      Job job = new Job();

      // Nome da vaga e URL
      Element h3JobListingTitle = li.selectFirst(".job_listing-title");
      if (h3JobListingTitle != null) {
        Element a = h3JobListingTitle.selectFirst("a");
        if (a != null) {
          job.setJobTitle(StringUtil.parseJobName(a.text()));
          job.setJobUrl(a.absUrl("href"));
        }
      }

      // Nome da empresa
      Element divJobListingCompany = li.selectFirst(".job_listing-company");
      if (divJobListingCompany != null) {
        job.setCompanyName(divJobListingCompany.text().trim().toLowerCase());
      }

      // Tipo da vaga
      Element jobType = li.selectFirst(".jtype");
      if (jobType != null) {
        job.setJobType(StringUtil.capitalize(jobType.text().trim().toLowerCase()));
      }

      // Descrição
      Elements divDescriptions = li.select(".ti");
      if (divDescriptions.size() >= 2) {
        Element divDescription = divDescriptions.get(1);
        if (divDescription != null) {
          job.setJobDescription(StringUtil.capitalize(divDescription.text().trim().toLowerCase()));
        }
      }

      // Data da publicação
      Element divDetails = li.selectFirst(".details");
      if (divDetails != null) {
        Element span = divDetails.selectFirst("span");
        if (span != null) {
          job.setPublishedAt(span.text().trim());
        }
      }

      if (job.isValid()) {
        jobList.add(job);
      }
    }

    return jobList;
  }
}
