package fastvagas.crawler;

import fastvagas.entity.PortalJob;
import fastvagas.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class JoinvilleJoinvilleVagas implements Crawler {

  @Override
  public List<PortalJob> findJobs(Document document) {
    List<PortalJob> portalJobList = new ArrayList<>();

    Element olJobListings = document.selectFirst(".job_listings");
    if (olJobListings == null) {
      log.warn("Nenhuma vaga encontrada para o seletor .job_listings");
      return portalJobList;
    }

    Elements liJobListing = olJobListings.select(".job_listing");

    for (Element li : liJobListing) {
      PortalJob portalJob = new PortalJob();

      // Nome da vaga e URL
      Element h3JobListingTitle = li.selectFirst(".job_listing-title");
      if (h3JobListingTitle != null) {
        Element a = h3JobListingTitle.selectFirst("a");
        if (a != null) {
          portalJob.setJobTitle(StringUtil.parseJobName(a.text()));
          portalJob.setJobUrl(a.absUrl("href"));
        }
      }

      // Nome da empresa
      Element divJobListingCompany = li.selectFirst(".job_listing-company");
      if (divJobListingCompany != null) {
        portalJob.setCompanyName(divJobListingCompany.text().trim().toLowerCase());
      }

      // Tipo da vaga
      Element divJType = li.selectFirst(".jtype");
      if (divJType != null) {
        portalJob.setJobType(StringUtil.capitalize(divJType.text().trim().toLowerCase()));
      }

      // Descrição
      Elements divDescriptions = li.select(".ti");
      if (divDescriptions.size() >= 2) {
        Element divDescription = divDescriptions.get(1);
        if (divDescription != null) {
          portalJob.setJobDescription(
              StringUtil.capitalize(divDescription.text().trim().toLowerCase()));
        }
      }

      // Data da publicação
      Element divDetails = li.selectFirst(".details");
      if (divDetails != null) {
        Element span = divDetails.selectFirst("span");
        if (span != null) {
          portalJob.setPublishedAt(span.text().trim());
        }
      }

      if (portalJob.isValid()) {
        portalJobList.add(portalJob);
      }
    }

    return portalJobList;
  }
}
