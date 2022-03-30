package fastvagas.crowler;

import java.util.ArrayList;
import java.util.List;

import fastvagas.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import fastvagas.data.entity.PortalJob;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class JoinvilleTrabalhaBrasil implements Crowler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        List<PortalJob> portalJobList = new ArrayList<>();

        Element divJobsWrapper = document.selectFirst(".jg__container");
        if (divJobsWrapper == null) {
            log.warn("Elemento não encontrado para o seletor id #jobs-wrapper");
            return portalJobList;
        }

        Elements divJobListing = divJobsWrapper.select(".jg__job");

        for (Element div : divJobListing) {
            PortalJob portalJob = new PortalJob();

            // URL
            Element aUrl = div.selectFirst("a");
            if (aUrl != null) {
                portalJob.setUrl(aUrl.absUrl("href"));
            }

            // Nome da Vaga
            Element h3JobName = div.selectFirst(".job__name");
            if (h3JobName != null) {
                portalJob.setName(StringUtil.parseJobName(h3JobName.text()));
            }

            // Nome da empresa
            Element h4JobCompany = div.selectFirst(".job__company");
            if (h4JobCompany != null) {
                portalJob.setCompany_name(h4JobCompany.text().trim().toLowerCase());
            }

            // Tipo da vaga
            portalJob.setJob_type("");

            // Descrição
            Element pJobDescription = div.selectFirst(".job__description");
            if (pJobDescription != null) {
                portalJob.setDescription(StringUtil.capitalize(pJobDescription.text().trim().toLowerCase()));
            }

            // Data da publicação
            portalJob.setPublished_at("");

            if (!portalJob.getName().isEmpty() && !portalJob.getUrl().isEmpty()) {
                portalJobList.add(portalJob);
            }
        }

        return portalJobList;
    }
}
