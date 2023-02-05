package fastvagas.crawler;

import fastvagas.entity.Job;
import fastvagas.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class contain the method to read jobs from Joinville Trabalha Brasil in Joinville-SC City.
 */
@Slf4j
public class JoinvilleTrabalhaBrasil implements Crawler {

  /**
   * Finds all job from the first page of the website.
   *
   * @param document The HTML DOM element to be searched
   * @return A {@link List} of {@link Job} containing all found jobs
   */
  @Override
  public List<Job> findJobs(Document document) {
    List<Job> jobList = new ArrayList<>();

    Element divJobsWrapper = document.selectFirst(".jg__container");
    if (divJobsWrapper == null) {
      log.warn("Elemento não encontrado para o seletor id #jobs-wrapper");
      return jobList;
    }

    Elements divJobListing = divJobsWrapper.select(".jg__job");

    for (Element div : divJobListing) {
      Job job = new Job();

      // URL
      Element jobUrl = div.selectFirst("a");
      if (jobUrl != null) {
        job.setJobUrl(jobUrl.absUrl("href"));
      }

      // Nome da Vaga
      Element h3JobName = div.selectFirst(".job__name");
      if (h3JobName != null) {
        job.setJobTitle(StringUtil.parseJobName(h3JobName.text()));
      }

      // Nome da empresa
      Element h4JobCompany = div.selectFirst(".job__company");
      if (h4JobCompany != null) {
        job.setCompanyName(h4JobCompany.text().trim().toLowerCase());
      }

      // Tipo da vaga
      job.setJobType("");

      // Descrição
      Element jobDescription = div.selectFirst(".job__description");
      if (jobDescription != null) {
        job.setJobDescription(StringUtil.capitalize(jobDescription.text().trim().toLowerCase()));
      }

      // Data da publicação
      job.setPublishedAt("");

      if (job.isValid()) {
        jobList.add(job);
      }
    }

    return jobList;
  }
}
