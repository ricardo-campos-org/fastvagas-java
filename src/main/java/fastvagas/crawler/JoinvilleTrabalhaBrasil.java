package fastvagas.crawler;

import fastvagas.entity.Job;
import fastvagas.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class JoinvilleTrabalhaBrasil implements Crawler {

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
      Element aUrl = div.selectFirst("a");
      if (aUrl != null) {
        job.setJobUrl(aUrl.absUrl("href"));
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
      Element pJobDescription = div.selectFirst(".job__description");
      if (pJobDescription != null) {
        job.setJobDescription(
            StringUtil.capitalize(pJobDescription.text().trim().toLowerCase()));
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
