package fastvagas.crowler.shared;

import fastvagas.crowler.Crowler;
import fastvagas.dal.entity.PortalJob;
import fastvagas.util.DateUtil;
import fastvagas.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Indeed implements Crowler {

    @Override
    public List<PortalJob> findJobs(Document document) {
        List<PortalJob> portalJobList = new ArrayList<>();

        Elements divResult = document.select(".row.result");
        for (Element div : divResult) {
            PortalJob portalJob = new PortalJob();

            // Nome da vaga e URL
            Element a = div.selectFirst("a");
            if (a != null) {
                portalJob.setName(StringUtil.capitalize(a.text().toLowerCase()));
                portalJob.setUrl(a.absUrl("href"));
            }

            // Nome da empresa
            Element divSjcl = div.selectFirst("div.sjcl");
            if (divSjcl != null) {
                Element divAux = divSjcl.selectFirst("div");
                if (divAux != null) {
                    Element span = divAux.selectFirst("span.company");
                    if (span != null) {
                        Element aComp = span.selectFirst("a");
                        if (aComp != null) {
                            portalJob.setCompany_name(StringUtil.capitalize(aComp.text().trim().toLowerCase()));
                        }
                    }
                }
            }

            // Tipo da vaga
            portalJob.setJob_type("Not set!");

            // Descrição
            Element divSummary = div.selectFirst("div.summary");
            if (divSummary != null) {
                portalJob.setDescription(StringUtil.capitalize(divSummary.text().trim().toLowerCase()));
            }

            // Data da publicação


            if (!portalJob.getName().isEmpty() && !portalJob.getUrl().isEmpty()) {
                if (portalJob.getPublished_at() == null) {
                    portalJob.setPublished_at(new Date());
                } else if (DateUtil.formatHour(portalJob.getPublished_at()).equals("00:00:00")) {
                    Date newDate = DateUtil.setCurrentHour(portalJob.getPublished_at());
                    portalJob.setPublished_at(newDate);
                }
                portalJobList.add(portalJob);
            }
        }
        return portalJobList;
    }
}
