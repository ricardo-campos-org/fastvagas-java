package fastvagas.crowler.shared;

import fastvagas.crowler.Crowler;
import fastvagas.dal.entity.PortalJob;
import fastvagas.util.ObjectUtil;
import fastvagas.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InfoJobs implements Crowler {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public List<PortalJob> findJobs(Document document) {
        List<PortalJob> portalJobList = new ArrayList<>();

        Element divMultiple = document.selectFirst("div.multiple");
        if (divMultiple == null) {
            logger.warn("div.multiple não localizado no documento!");
            return portalJobList;
        }

        Elements divVagas = divMultiple.select("div.element-vaga");
        logger.info("Divs encontradas: {}", divVagas.size());
        for (Element div : divVagas) {
            PortalJob portalJob = new PortalJob();

            // Nome da vaga e URL
            Element divVaga = div.selectFirst("div.vaga");
            if (divVaga != null) {
                Element a = divVaga.selectFirst("a");
                if (a != null) {
                    portalJob.setName(StringUtil.capitalize(a.attr("title").toLowerCase()));
                    portalJob.setUrl(a.absUrl("href"));
                }
            }

            // Nome da empresa
            Element divContainerVaga = div.selectFirst("div.container-vaga");
            if (divContainerVaga != null) {
                Element divComp = divContainerVaga.selectFirst("div.vaga-company");
                if (divComp != null) {
                    Element a = divComp.selectFirst("a");
                    if (a != null) {
                        portalJob.setCompany_name(StringUtil.capitalize(a.text().trim().toLowerCase()));
                    }
                }
            }

            // Tipo da vaga
            // Não tem no site

            // Descrição
            if (divContainerVaga != null) {
                Element divVagaDesc = divContainerVaga.selectFirst("div.vagaDesc");
                if (divVagaDesc != null) {
                    portalJob.setDescription(StringUtil.capitalize(divVagaDesc.text().trim().toLowerCase()));
                }
            }

            // Data da publicação
            if (divContainerVaga != null) {
                Elements divs = divContainerVaga.select("div");
                for (Element divCVaga : divs) {
                    Element p = divCVaga.selectFirst("p.location2");
                    if (p == null) {
                        continue;
                    }

                    Element spanData = p.selectFirst("span.data");
                    if (spanData != null) {
                        portalJob.setPublished_at(spanData.text().trim());
                    }
                }
            }

            if (ObjectUtil.hasValue(portalJob.getName()) && ObjectUtil.hasValue(portalJob.getUrl())) {
                if (!ObjectUtil.hasValue(portalJob.getCompany_name())) {
                    portalJob.setCompany_name("");
                }

                portalJobList.add(portalJob);
            }
        }

        return portalJobList;
    }
}
