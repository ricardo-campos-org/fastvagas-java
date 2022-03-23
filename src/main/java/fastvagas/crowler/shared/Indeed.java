package fastvagas.crowler.shared;

import fastvagas.crowler.Crowler;
import fastvagas.dal.entity.PortalJob;
import fastvagas.util.ObjectUtil;
import fastvagas.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Indeed implements Crowler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        List<PortalJob> portalJobList = new ArrayList<>();

        Element divCard = document.getElementById("mosaic-zone-jobcards");
        if (divCard != null) {
            Elements aList = divCard.select("a.tapItem");
            for (Element a : aList) {
                PortalJob portalJob = new PortalJob();

                // URL
                portalJob.setUrl(a.absUrl("href"));

                // Nome da vaga e URL
                Element h2JobTitle = a.selectFirst("h2.jobTitle");
                if (h2JobTitle != null) {
                    Elements spanList = h2JobTitle.select("span");
                    for (Element span : spanList) {
                        if (span.hasAttr("title")) {
                            portalJob.setName(StringUtil.capitalize(span.text().toLowerCase()));
                            break;
                        }
                    }
                }

                // Nome da empresa
                Element divCompany = a.selectFirst("div.companyInfo");
                if (divCompany != null) {
                    Element span = divCompany.selectFirst("span.companyName");
                    if (span != null) {
                        portalJob.setCompany_name(StringUtil.capitalize(span.text().trim().toLowerCase()));
                    }
                }

                // Salário
                Element divMetadata = a.selectFirst("div.salary-snippet-container");
                if (divMetadata != null) {
                    Element divAria = divMetadata.selectFirst("div.salary-snippet");
                    if (divAria != null) {
                        String salario = divAria.attr("aria-label").trim();
                        if (!salario.equals("null")) {
                            portalJob.setDescription(salario + ". ");
                        }
                    }
                }

                // Tipo da vaga
                portalJob.setJob_type("");

                // Descrição
                Element divJobSnippet = a.selectFirst("div.job-snippet");
                if (divJobSnippet != null) {
                    Elements liList = divJobSnippet.select("li");
                    for (Element li : liList) {
                        portalJob.setDescription(portalJob.getDescription() +
                                StringUtil.capitalize(li.text().trim().toLowerCase()));
                    }

                    if (liList.isEmpty()) {
                        portalJob.setDescription(portalJob.getDescription() +
                                StringUtil.capitalize(divJobSnippet.text().trim().toLowerCase()));
                    }

                }

                if (!portalJob.getName().isEmpty() && !portalJob.getUrl().isEmpty()) {
                    if (!ObjectUtil.hasValue(portalJob.getCompany_name())) {
                        portalJob.setCompany_name("");
                    }

                    portalJobList.add(portalJob);
                }
            }
        }
        return portalJobList;
    }
}
