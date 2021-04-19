package fastvagas.service;

import fastvagas.dal.entity.*;
import fastvagas.dal.service.CityService;
import fastvagas.dal.service.PortalJobService;
import fastvagas.dal.service.PortalService;
import fastvagas.dal.service.StateService;
import fastvagas.exception.EntityNotFoundException;
import fastvagas.json.AppUserJob;
import fastvagas.json.JobDetail;
import fastvagas.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppJobService {

    @Autowired
    CityService cityService;

    @Autowired
    StateService stateService;

    @Autowired
    PortalJobService portalJobService;

    @Autowired
    PortalService portalService;

    public AppUserJob getAppUserJobs(User user) {
        City city = cityService.findById(user.getCity_id());
        if (city == null) {
            throw new EntityNotFoundException(City.class, "city_id", String.valueOf(user.getCity_id()));
        }

        State state = stateService.findById(city.getState_id());
        if (state == null) {
            throw new EntityNotFoundException(State.class, "state_id", String.valueOf(city.getState_id()));
        }

        List<Portal> portals = portalService.findAllByCityId(user.getCity_id());

        final List<PortalJob> portalJobs = new ArrayList<>();
        final Date today = new Date();
        final Integer month = DateUtil.getMonthFromDate(today);
        final Integer year = DateUtil.getYearFromDate(today);
        final Date firstDayOfMonth = DateUtil.createDate(1, month, year);
        final Integer dayOfWeek = DateUtil.getCurrentDayOfWeek();
        final Date firstDateOfWeek = DateUtil.subtractDays(today, dayOfWeek-1);

        Integer monthJobs = 0;
        Integer weekJobs = 0;
        Integer todayJobs = 0;
        final List<JobDetail> lastJobList = new ArrayList<>();
        for (Portal portal : portals) {
            /* Month jobs for the user city */
            List<PortalJob> portalJobsTmp = portalJobService.findAllByPortalIdPublishedRange(
                portal.getPortal_id(),
                firstDayOfMonth
            );

            monthJobs += portalJobsTmp.size();

            for (PortalJob portalJob : portalJobsTmp) {
                JobDetail jobDetail = new JobDetail();
                jobDetail.setPortal_job_id(portalJob.getPortal_job_id());
                jobDetail.setName(portalJob.getName());
                jobDetail.setCompany_name(portalJob.getCompany_name());
                jobDetail.setJob_type(portalJob.getJob_type());
                jobDetail.setDescription(portalJob.getDescription());
                jobDetail.setPublished_at(portalJob.getPublished_at());
                jobDetail.setUrl(portalJob.getUrl());
                jobDetail.setPortal_id(portalJob.getPortal_id());
                jobDetail.setPortal_name(portal.getName());

                lastJobList.add(jobDetail);
            }

            /* Today jobs */
            long c = portalJobsTmp
                .stream()
                .filter(x -> DateUtil.equalsIgnoringHours(x.getPublished_at(), today))
                .count();

            todayJobs = Integer.parseInt(String.valueOf(c));

            /* Week jobs */
            weekJobs += portalJobService.findAllByPortalIdPublishedRange(
                portal.getPortal_id(),
                firstDayOfMonth
            ).size();
        }

        AppUserJob appUserJob = new AppUserJob();
        appUserJob.setCityName(city.getName());
        appUserJob.setStateName(state.getSigla_uf());
        appUserJob.setMonthJobs(monthJobs);
        appUserJob.setWeekJobs(weekJobs);
        appUserJob.setTodayJobs(todayJobs);

        //appUserJob.setUserJobList();
        appUserJob.setLastJobList(lastJobList);
        //appUserJob.setTopJobList();

        return appUserJob;
    }
}
