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

        int nextSequence = crowlerLogService.getLastSequenceByDate(DateUtil.getCurrentLocalDate()) + 1;

        for (Portal portal : portals) {
            City city = cityCache.get(portal.getCity_id());
            if (city == null) {
                city = new City();
            }

            List<String> logToSave = new ArrayList<>();

            logToSave.add("Starting crowler for " + portal.getName() + " portal (city of " + city.getName() + ").");
            log.info(logToSave.get(logToSave.size()-1));

            List<PortalJob> portalJobList = findJobs(portal, city);

            if (portalJobList.isEmpty()) {
                logToSave.add("Zero jobs received from the portal. Going to next");
                log.info(logToSave.get(logToSave.size()-1));
                continue;
            }

            logToSave.add(portalJobList.size() + " job(s) received from the portal.");
            log.info(logToSave.get(logToSave.size()-1));

            // Last 30 days jobs for this portal
            logToSave.add("Finding last 30 days jobs from this portal...");
            log.info(logToSave.get(logToSave.size()-1));
            List<PortalJob> savedList = portalJobService.findAllByPortalIdPublishedRange(
                    portal.getPortal_id(),
                    DateUtil.subtractMonths(new Date(), 1)
            );

            Map<String, PortalJob> portalJobMap = portalJobService.listToMapByUrl(savedList);

            logToSave.add(portalJobMap.size() + " job(s) already saved at this portal.");
            log.info(logToSave.get(logToSave.size()-1));

            logToSave.add("Finding users and terms to search...");
            log.info(logToSave.get(logToSave.size()-1));
            // obter termos de todos os usuários desse portal
            List<UserTermPortal> userTermPortals = userTermPortalService.findAllByPortalId(
                    portal.getPortal_id()
            );

            if (userTermPortals.isEmpty()) {
                logToSave.add("No users or terms on this portal.");
                log.info(logToSave.get(logToSave.size()-1));
            }

            Map<Long, List<PortalJob>> userIdPortalJobMap = new HashMap<>();
            List<PortalJob> portalJobToSave = new ArrayList<>();

            logToSave.add("Iterating over job list received, looking for new jobs and users terms that match.");
            log.info(logToSave.get(logToSave.size()-1));
            for (PortalJob portalJob : portalJobList) {
                // Save the job, if it's not already saved
                if (!portalJobMap.containsKey(portalJob.getUrl())) {
                    portalJob.setPortal_id(portal.getPortal_id());
                    portalJob.setCity_id(portal.getCity_id());
                    portalJobToSave.add(portalJob);
                }

                // Search for user terms and notify them
                for (UserTermPortal userTermPortal : userTermPortals) {
                    List<String> terms = Arrays.asList(userTermPortal.getTerms().split(";"));

                    Boolean match = check(portalJob.getName(), terms);
                    if (match) {
                        if (!userIdPortalJobMap.containsKey(userTermPortal.getUser_id())) {
                            userIdPortalJobMap.put(userTermPortal.getUser_id(), new ArrayList<>());
                        }

                        userIdPortalJobMap.get(userTermPortal.getUser_id()).add(portalJob);
                    }
                }
            }

            logToSave.add(userIdPortalJobMap.size() + " user(s) to notify. " + userIdPortalJobMap.values().size() + " job(s).");
            log.info(logToSave.get(logToSave.size()-1));
            for (Map.Entry<Long, List<PortalJob>> entry : userIdPortalJobMap.entrySet()) {
                Long userId = entry.getKey();

                List<UserTermPortal> newList = userTermPortals.stream()
                    .filter(item -> item.getUser_id().equals(userId))
                    .collect(Collectors.toList());

                if (!newList.isEmpty()) {
                    String userName = newList.get(0).getFirst_name();
                    String email = newList.get(0).getEmail();
                    List<PortalJob> jobs = entry.getValue();

                    mailService.jobNotification(userName, email, jobs);
                }
            }

            logToSave.add(portalJobToSave.size() + " new job(s) found. Registering...");
            log.info(logToSave.get(logToSave.size()-1));
            portalJobService.createBatch(portalJobToSave);

            logToSave.add("Done crowling for " + portal.getName() + " portal (city of " + city.getName() + ").");
            log.info(logToSave.get(logToSave.size()-1));

            // saving log
            List<CrowlerLog> crowlerLogs = new ArrayList<>();
            for (String log : logToSave) {
                CrowlerLog crowlerLog = new CrowlerLog();
                crowlerLog.setCreated_at(DateUtil.getCurrentLocalDate());
                crowlerLog.setSequence(nextSequence++);
                crowlerLog.setPortal_id(portal.getPortal_id());
                crowlerLog.setText(log);
                crowlerLogs.add(crowlerLog);
            }

            crowlerLogService.createBatch(crowlerLogs);
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
