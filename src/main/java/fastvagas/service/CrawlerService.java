package fastvagas.service;

import fastvagas.crawler.Crawler;
import fastvagas.crawler.CrawlerFactory;
import fastvagas.entity.*;
import fastvagas.repository.*;
import fastvagas.util.DateUtil;
import fastvagas.util.PortalJobUtil;
import fastvagas.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CrawlerService {

    private final PortalRepository portalRepository;
    private final PortalJobRepository portalJobRepository;
    private final CityRepository cityRepository;
    private final CrawlerLogRepository crawlerLogRepository;
    private final MailService mailService;

    @Autowired
    public CrawlerService(PortalRepository portalRepository, PortalJobRepository portalJobRepository,
                          CityRepository cityRepository, CrawlerLogRepository crawlerLogRepository,
                          MailService mailService) {
        this.portalRepository = portalRepository;
        this.portalJobRepository = portalJobRepository;
        this.cityRepository = cityRepository;
        this.crawlerLogRepository = crawlerLogRepository;
        this.mailService = mailService;
    }

    @Transactional()
    public void start() {
        List<Portal> portals = portalRepository.findAll();
        if (portals.isEmpty()) {
            log.info("Zero portals with active users. Leaving..");
            return;
        }

        Map<Long, City> cityCache = cityRepository.findAll().stream()
                .collect(Collectors.toMap(City::getId, Function.identity()));

        for (Portal portal : portals) {
            int count = 0;
            City city = cityCache.get(portal.getCityId());
            if (city == null) {
                log.warn("City id not mapped: {}", portal.getCityId());
                continue;
            }

            String[] logsToSave = new String[7];

            logsToSave[count] = "Starting crawler for " + portal.getName() + " portal (city of " + city.getName() + ").";
            log.info(logsToSave[count++]);

            List<PortalJob> portalJobList = findJobs(portal, city);

            if (portalJobList.isEmpty()) {
                logsToSave[count] = "Zero jobs received from the portal. Going to next";
                log.info(logsToSave[count]);

                String[] smallCopy = new String[2];
                Arrays.asList(logsToSave).subList(0, 2).toArray(smallCopy);
                crawlerLogRepository.saveAll(fromStringArray(smallCopy, portal.getId()));
                continue;
            }

            logsToSave[count] = portalJobList.size() + " job(s) received from the portal.";
            log.info(logsToSave[count++]);

            // Last 30 days jobs for this portal
            logsToSave[count] = "Finding last 30 days jobs from this portal...";
            log.info(logsToSave[count++]);
            LocalDateTime oneMonthPast = LocalDateTime.now().minusMonths(1L);
            List<PortalJob> savedList = portalJobRepository.findAllByPortalId(portal.getId())
                    .stream()
                    .filter(x -> x.getCreatedAt().isAfter(oneMonthPast))
                    .collect(Collectors.toList());

            Map<String, PortalJob> portalJobMap = PortalJobUtil.listToMapByUrl(savedList);

            logsToSave[count] = portalJobMap.size() + " job(s) already saved at this portal.";
            log.info(logsToSave[count++]);

            List<PortalJob> portalJobToSave = new ArrayList<>();

            logsToSave[count] = "Iterating over job list received, looking for new jobs...";
            log.info(logsToSave[count++]);
            for (PortalJob portalJob : portalJobList) {
                // Save the job, if it's not already saved
                if (!portalJobMap.containsKey(portalJob.getJobUri())) {
                    portalJob.setPortalId(portal.getId());
                    portalJobToSave.add(portalJob);
                }
            }

            logsToSave[count] = portalJobToSave.size() + " new job(s) found. Registering...";
            log.info(logsToSave[count++]);
            portalJobRepository.saveAll(portalJobToSave);

            logsToSave[count] = "Done crowling for " + portal.getName() + " portal (city of " + city.getName() + ").";
            log.info(logsToSave[count]);

            // saving log
            List<CrawlerLog> crawlerLogs = fromStringArray(logsToSave, portal.getId());
            crawlerLogRepository.saveAll(crawlerLogs);
        }
    }

    public List<PortalJob> findJobs(Portal portal, City city) {
        try {
            Document doc = Jsoup
                .connect(portal.getJobsUri())
                .ignoreHttpErrors(true)
                .get();

            String cityName = StringUtil.replaceToPlainText(city.getName().replace(" ", ""));
            String portalName = StringUtil.replaceToPlainText(portal.getName().replace(" ", ""));

            Crawler crawler = CrawlerFactory.createInstance(cityName + portalName);
            return crawler.findJobs(doc);
        
        } catch (IOException ioe) {
            log.error("IOException: {}", ioe.getLocalizedMessage());
        } catch (ClassCastException cce) {
            log.error("ClassCastException: {}", cce.getLocalizedMessage());
        } catch (NullPointerException npe) {
            log.error("NullPointerException: {}", npe.getLocalizedMessage());
            npe.printStackTrace();
        }

        return new ArrayList<>();
    }

    private Boolean check(String jobName, List<String> terms) {
        for (String term : terms) {
            if (jobName.toLowerCase().contains(term.toLowerCase())) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    private List<CrawlerLog> fromStringArray(String[] logs, Long portal_id) {
        List<CrawlerLog> crawlerLogs = new ArrayList<>(logs.length);
        for (String log : logs) {
            CrawlerLog crawlerLog = CrawlerLog.builder()
                    .createdAt(DateUtil.getCurrentLocalDateTime())
                    .portalId(portal_id)
                    .text(log)
                    .build();
            crawlerLogs.add(crawlerLog);
        }

        return crawlerLogs;
    }
}
