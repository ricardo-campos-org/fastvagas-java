package fastvagas.crowler;

import fastvagas.dal.entity.PortalJob;
import fastvagas.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JoinvilleJoinvilleVagas implements Crowler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<PortalJob> findJobs(Document document) {
        List<PortalJob> portalJobList = new ArrayList<>();

        Element olJobListings = document.selectFirst(".job_listings");
        if (olJobListings == null) {
            logger.warn("Nenhuma vaga encontrada para o seletor .job_listings");
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
                    portalJob.setName(StringUtil.capitalize(a.text().toLowerCase()));
                    portalJob.setUrl(a.absUrl("href"));
                }
            }

            // Nome da empresa
            Element divJobListingCompany = li.selectFirst(".job_listing-company");
            if (divJobListingCompany != null) {
                portalJob.setCompany_name(StringUtil.capitalize(divJobListingCompany.text().trim().toLowerCase()));
            }

            // Tipo da vaga
            Element divJType = li.selectFirst(".jtype");
            if (divJType != null) {
                portalJob.setJob_type(StringUtil.capitalize(divJType.text().trim().toLowerCase()));
            }

            // Descrição
            Elements divDescriptions = li.select(".ti");
            if (divDescriptions.size() >= 2) {
                Element divDescription = divDescriptions.get(1);
                if (divDescription != null) {
                    portalJob.setDescription(StringUtil.capitalize(divDescription.text().trim().toLowerCase()));
                }
            }

            // Data da publicação
            Element divDetails = li.selectFirst(".details");
            if (divDetails != null) {
                Element span = divDetails.selectFirst("span");
                if (span != null) {
                    portalJob.setPublished_at(span.text().trim());
                }
            }

            if (!portalJob.getName().isEmpty() && !portalJob.getUrl().isEmpty()) {
                portalJobList.add(portalJob);
            }
        }

        return portalJobList;
    }
}
