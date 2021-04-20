package fastvagas.crowler;

import fastvagas.dal.entity.PortalJob;
import fastvagas.util.DateUtil;
import fastvagas.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JoinvilleJoinvilleVagas implements Crowler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<PortalJob> findJobs(Document document) {
        List<PortalJob> portalJobList = new ArrayList<>();

        Element olJobListings = document.selectFirst(".job_listings");
        if (olJobListings != null) {
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
                        portalJob.setPublished_at(getPublishedAtDate(span.text().trim()));
                    }
                }

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
        }

        return portalJobList;
    }

    private Date getPublishedAtDate(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        try {
            String digitos = str.replaceAll("\\D+", ""); //.replace(anoAtual, ""); // 14
            String ano = "";
            if (digitos.length() >= 5) {
                ano = digitos.substring(digitos.length()-4);
                digitos = digitos.replace(ano, "");
            }

            if (!digitos.isEmpty()) {
                String doisPrimeiros = digitos;
                if (doisPrimeiros.length() > 2) {
                    doisPrimeiros = doisPrimeiros.substring(0, 2);
                }

                int pos = str.indexOf(doisPrimeiros);
                String apenasData = str.substring(pos).trim();
                String[] parts = apenasData.split(" ");
                if (parts.length > 1) {
                    Integer dia = Integer.parseInt(parts[0]);
                    Integer mes = getMonth(parts[1].toLowerCase());

                    return new SimpleDateFormat("dd/MM/yyyy").parse(
                            String.format("%02d", dia) + "/" +
                            String.format("%02d", mes) + "/" +
                            ano
                    );
                }
            }
        } catch (NullPointerException npe) {
            logger.error("NullPointerException at getPublishedAtDate with {}: {}", str, npe.getLocalizedMessage());
        } catch (NumberFormatException nfe) {
            logger.error("NumberFormatException at getPublishedAtDate with {}: {}", str, nfe.getLocalizedMessage());
        } catch (ParseException pe) {
            logger.error("ParseException at getPublishedAtDate with {}: {}", str, pe.getLocalizedMessage());
        }

        return null;
    }

    private Integer getMonth(String monthStr) {
        if (monthStr.startsWith("jan")) return 1;
        if (monthStr.startsWith("fev")) return 2;
        if (monthStr.startsWith("mar")) return 3;
        if (monthStr.startsWith("abr")) return 4;
        if (monthStr.startsWith("mai")) return 5;
        if (monthStr.startsWith("jun")) return 6;
        if (monthStr.startsWith("jul")) return 7;
        if (monthStr.startsWith("ago")) return 8;
        if (monthStr.startsWith("set")) return 9;
        if (monthStr.startsWith("out")) return 10;
        if (monthStr.startsWith("nov")) return 11;
        if (monthStr.startsWith("dez")) return 12;

        return null;
    }
}
