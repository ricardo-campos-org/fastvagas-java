package fastvagas.crawler.shared;

import fastvagas.crawler.Crawler;
import fastvagas.entity.Job;
import fastvagas.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/** This class search jobs at InfoJobs. */
@Slf4j
public class InfoJobs implements Crawler {

  /**
   * Finds all job from the first page of the website.
   *
   * @param document The HTML DOM element to be searched
   * @return A {@link List} of {@link Job} containing all found jobs
   */
  @Override
  public List<Job> findJobs(Document document) {
    List<Job> jobList = new ArrayList<>();

    Element divMultiple = document.selectFirst("div.multiple");
    if (divMultiple == null) {
      log.warn("div.multiple não localizado no documento!");
      return jobList;
    }

    Elements divVagas = divMultiple.select("div.element-vaga");
    log.info("Divs encontradas: {}", divVagas.size());
    for (Element div : divVagas) {
      Job job = new Job();
      job.setCompanyName("");

      // Nome da vaga e URL
      Element divVaga = div.selectFirst("div.vaga");
      if (divVaga != null) {
        Element a = divVaga.selectFirst("a");
        if (a != null) {
          job.setJobTitle(StringUtil.capitalize(a.attr("title").toLowerCase()));
          job.setJobUrl(a.absUrl("href"));
        }
      }

      // Nome da empresa
      Element divContainerVaga = div.selectFirst("div.container-vaga");
      if (divContainerVaga != null) {
        Element divComp = divContainerVaga.selectFirst("div.vaga-company");
        if (divComp != null) {
          Element a = divComp.selectFirst("a");
          if (a != null) {
            job.setCompanyName(StringUtil.capitalize(a.text().trim().toLowerCase()));
          }
        }
      }

      // Tipo da vaga
      // Não tem no site

      // Descrição
      if (divContainerVaga != null) {
        Element divVagaDesc = divContainerVaga.selectFirst("div.vagaDesc");
        if (divVagaDesc != null) {
          job.setJobDescription(StringUtil.capitalize(divVagaDesc.text().trim().toLowerCase()));
        }
      }

      // Data da publicação
      if (divContainerVaga != null) {
        Elements divs = divContainerVaga.select("div");
        for (Element divVagaFor : divs) {
          Element p = divVagaFor.selectFirst("p.location2");
          if (p == null) {
            continue;
          }

          Element spanData = p.selectFirst("span.data");
          if (spanData != null) {
            job.setPublishedAt(spanData.text().trim());
          }
        }
      }

      if (job.isValid()) {
        jobList.add(job);
      }
    }

    return jobList;
  }
}
