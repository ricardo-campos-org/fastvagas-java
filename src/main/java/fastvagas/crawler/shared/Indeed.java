package fastvagas.crawler.shared;

import fastvagas.crawler.Crawler;
import fastvagas.entity.Job;
import fastvagas.util.ObjectUtil;
import fastvagas.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Indeed implements Crawler {

  @Override
  public List<Job> findJobs(Document document) {
    List<Job> jobList = new ArrayList<>();

    Element divCard = document.getElementById("mosaic-zone-jobcards");
    if (divCard != null) {
      Elements aList = divCard.select("a.tapItem");
      for (Element a : aList) {
        Job job = new Job();

        // URL
        job.setJobUrl(a.absUrl("href"));

        // Nome da vaga e URL
        Element h2JobTitle = a.selectFirst("h2.jobTitle");
        if (h2JobTitle != null) {
          Elements spanList = h2JobTitle.select("span");
          for (Element span : spanList) {
            if (span.hasAttr("title")) {
              job.setJobTitle(StringUtil.parseJobName(span.text()));
              break;
            }
          }
        }

        // Nome da empresa
        Element divCompany = a.selectFirst("div.companyInfo");
        if (divCompany != null) {
          Element span = divCompany.selectFirst("span.companyName");
          if (span != null) {
            job.setCompanyName(span.text().trim());
          }
        }

        // Salário
        Element divMetadata = a.selectFirst("div.salary-snippet-container");
        if (divMetadata != null) {
          Element divAria = divMetadata.selectFirst("div.salary-snippet");
          if (divAria != null) {
            String salario = divAria.attr("aria-label").trim();
            if (!salario.equals("null")) {
              job.setJobDescription(salario + ". ");
            }
          }
        }

        // Tipo da vaga
        job.setJobType("");

        // Descrição
        Element divJobSnippet = a.selectFirst("div.job-snippet");
        if (divJobSnippet != null) {
          Elements liList = divJobSnippet.select("li");
          for (Element li : liList) {
            job.setJobDescription(
                job.getJobDescription()
                    + StringUtil.capitalize(li.text().trim().toLowerCase()));
          }

          if (liList.isEmpty()) {
            job.setJobDescription(
                job.getJobDescription()
                    + StringUtil.capitalize(divJobSnippet.text().trim().toLowerCase()));
          }
        }

        if (job.isValid()) {
          if (!ObjectUtil.hasValue(job.getCompanyName())) {
            job.setCompanyName("");
          }

          jobList.add(job);
        }
      }
    }
    return jobList;
  }
}
