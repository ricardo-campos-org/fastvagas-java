package fastvagas.service;

import fastvagas.crowler.Crowler;
import fastvagas.crowler.CrowlerFactory;
import fastvagas.data.entity.City;
import fastvagas.data.entity.CrowlerLog;
import fastvagas.data.entity.Portal;
import fastvagas.data.entity.PortalJob;
import fastvagas.data.repository.*;
import fastvagas.data.vo.UserTermPortal;
import fastvagas.util.DateUtil;
import fastvagas.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CrowlerService {

    private final PortalRepositoryBean portalServiceBean;
    private final UserTermPortalService userTermPortalService;
    private final PortalJobService portalJobService;
    private final CityService cityService;
    private final CrowlerLogService crowlerLogService;
    private final MailService mailService;

    @Autowired
    public CrowlerService(PortalRepositoryBean portalServiceBean, UserTermPortalService userTermPortalService,
                          PortalJobService portalJobService, CityService cityService,
                          CrowlerLogService crowlerLogService, MailService mailService) {
        this.portalServiceBean = portalServiceBean;
        this.userTermPortalService = userTermPortalService;
        this.portalJobService = portalJobService;
        this.cityService = cityService;
        this.crowlerLogService = crowlerLogService;
        this.mailService = mailService;
    }

    @Transactional()
    public void start() {
        List<Portal> portals = portalServiceBean.findAll();
        if (portals.isEmpty()) {
            log.info("Zero portals with active users. Leaving..");
            return;
        }

        Map<Long, City> cityCache = cityService.findAll().stream()
                .collect(Collectors.toMap(City::getCity_id, Function.identity()));

        Integer nextSequence = crowlerLogService.getNextSequence();

        for (Portal portal : portals) {
            int count = 0;
            City city = cityCache.get(portal.getCity_id());
            if (city == null) {
                log.warn("City id not mapped: {}", portal.getCity_id());
                continue;
            }

            String[] logsToSave = new String[7];

            logsToSave[count] = "Starting crowler for " + portal.getName() + " portal (city of " + city.getName() + ").";
            log.info(logsToSave[count++]);

            List<PortalJob> portalJobList = findJobs(portal, city);

            if (portalJobList.isEmpty()) {
                logsToSave[count] = "Zero jobs received from the portal. Going to next";
                log.info(logsToSave[count]);

                String[] smallCopy = new String[2];
                Arrays.asList(logsToSave).subList(0, 2).toArray(smallCopy);
                crowlerLogService.createBatch(crowlerLogService.fromStringArray(smallCopy, nextSequence, portal.getPortal_id()));
                nextSequence += smallCopy.length;
                continue;
            }

            logsToSave[count] = portalJobList.size() + " job(s) received from the portal.";
            log.info(logsToSave[count++]);

            // Last 30 days jobs for this portal
            logsToSave[count] = "Finding last 30 days jobs from this portal...";
            log.info(logsToSave[count++]);
            List<PortalJob> savedList = portalJobService.findAllByPortalIdCreatedAtFrom(
                    portal.getPortal_id(),
                    LocalDateTime.now().minusMonths(1L)
            );

            Map<String, PortalJob> portalJobMap = portalJobService.listToMapByUrl(savedList);

            logsToSave[count] = portalJobMap.size() + " job(s) already saved at this portal.";
            log.info(logsToSave[count++]);

            List<PortalJob> portalJobToSave = new ArrayList<>();

            logsToSave[count] = "Iterating over job list received, looking for new jobs...";
            log.info(logsToSave[count++]);
            for (PortalJob portalJob : portalJobList) {
                // Save the job, if it's not already saved
                if (!portalJobMap.containsKey(portalJob.getUrl())) {
                    portalJob.setPortal_id(portal.getPortal_id());
                    portalJob.setCity_id(portal.getCity_id());
                    portalJobToSave.add(portalJob);
                }
            }

            logsToSave[count] = portalJobToSave.size() + " new job(s) found. Registering...";
            log.info(logsToSave[count++]);
            portalJobService.createBatch(portalJobToSave);

            logsToSave[count] = "Done crowling for " + portal.getName() + " portal (city of " + city.getName() + ").";
            log.info(logsToSave[count]);

            // saving log
            List<CrowlerLog> crowlerLogs = crowlerLogService.fromStringArray(logsToSave, nextSequence, portal.getPortal_id());
            crowlerLogService.createBatch(crowlerLogs);
            nextSequence += logsToSave.length;
        }
    }

    public List<PortalJob> findJobs(Portal portal, City city) {
        try {
            Document doc = Jsoup
                .connect(portal.getUrl())
                .ignoreHttpErrors(true)
                .get();

            String cityName = StringUtil.replaceToPlainText(city.getName().replace(" ", ""));
            String portalName = StringUtil.replaceToPlainText(portal.getName().replace(" ", ""));

            Crowler crowler = CrowlerFactory.createInstance(cityName + portalName);
            return crowler.findJobs(doc);
        
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
}
