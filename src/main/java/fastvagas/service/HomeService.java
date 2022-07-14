package fastvagas.service;

import fastvagas.entity.*;
import fastvagas.repository.PortalJobRepository;
import fastvagas.repository.PortalRepository;
import fastvagas.exception.EntityNotFoundException;
import fastvagas.repository.CityRepository;
import fastvagas.json.HomeJson;
import fastvagas.json.JobDetail;
import fastvagas.json.JobPagination;
import fastvagas.util.DateUtil;
import fastvagas.util.ObjectUtil;
import fastvagas.util.PaginationUtil;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeService {

    private final CityRepository cityRepository;
    private final PortalJobRepository portalJobRepository;
    private final PortalRepository portalRepository;
    private final JobService jobService;

    @Autowired
    public HomeService(CityRepository cityRepository, PortalJobRepository portalJobRepository,
                       PortalRepository portalRepository, JobService jobService) {
        this.cityRepository = cityRepository;
        this.portalJobRepository = portalJobRepository;
        this.portalRepository = portalRepository;
        this.jobService = jobService;
    }

    public HomeJson getAllJobs(Person person) {
        Optional<City> city = cityRepository.findById(person.getCityId());
        if (city.isEmpty()) {
            throw new EntityNotFoundException(City.class, "cityId", String.valueOf(person.getCityId()));
        }

        State state = city.get().getState();
        if (state == null) {
            throw new EntityNotFoundException(State.class, "stateId", "state mapped by city");
        }

        final Date today = new Date();
        final Integer month = DateUtil.getMonthFromDate(today);
        final Integer year = DateUtil.getYearFromDate(today);
        final Date firstDayOfMonth = DateUtil.createDate(1, month, year);
        final LocalDateTime firstLocal = DateUtil.getLocalDateTimeFromDate(firstDayOfMonth);

        Calendar c = Calendar.getInstance();
        c.setTime(today);

        final Date weekDate = DateUtil.subtractDays(today, (c.get(Calendar.DAY_OF_WEEK)-1));

        int weekJobs = 0;
        int todayJobs = 0;

        List<PortalJob> cityJobs = portalJobRepository.findAllByCityId(city.get().getId());

        for (PortalJob portalJob : cityJobs) {
            if (!portalJob.getCreatedAt().isAfter(firstLocal)) {
                continue;
            }

            Date dataLeitura = DateUtil.getDateFromLocalDateTime(portalJob.getCreatedAt());
            /* This week jobs */
            if (DateUtil.isGreater(weekDate, dataLeitura)) {
                weekJobs += 1;
            }

            /* Today jobs */
            if (DateUtil.equalsIgnoringHours(dataLeitura, today)) {
                todayJobs += 1;
            }
        }

        HomeJson homeJson = new HomeJson();
        homeJson.setCityId(city.get().getId());
        homeJson.setCityName(city.get().getName());
        homeJson.setStateName(state.getAcronym());
        homeJson.setMonthJobs(cityJobs.size());
        homeJson.setWeekJobs(weekJobs);
        homeJson.setTodayJobs(todayJobs);
        homeJson.setUserJobPagination(getUserJobs(person, city.get().getId(), null));
        homeJson.setLastJobPagination(getLastJobs(city.get().getId(), null));
        homeJson.setTopJobPagination(createJobPagination(null, null, null));

        return homeJson;
    }

    // TODO: implement here
    public JobPagination getUserJobs(Person person, Long cityId, Integer page) {
        if (!ObjectUtil.hasValue(page)) {
            page = 1;
        }

        List<PortalJob> portalJobList = jobService.findUserJobsByTermsNotSeen(person.getId());

        Map<Long, String> portalNameMap = getPortalNameMap(cityId);

        List<JobDetail> jobList = new ArrayList<>();
        portalJobList.forEach((portalJob -> {
            JobDetail jobDetail = new JobDetail(portalJob);
            jobDetail.setPortal_name(portalNameMap.get(portalJob.getPortalId()));
            jobList.add(jobDetail);
        }));

        long count = portalJobList.size();
        if (count > 50) {
            count = 50L;
        }

        return createJobPagination(jobList, page, count);
    }

    public JobPagination getLastJobs(Long cityId, Integer page) {
        if (!ObjectUtil.hasValue(page)) {
            page = 1;
        }

        Map<Long, String> portalNameMap = getPortalNameMap(cityId);

        List<JobDetail> jobList = new ArrayList<>();

        List<PortalJob> portalJobsTmp = portalJobRepository.findAllLastByCityIdPage(cityId);
        portalJobsTmp.forEach((job) -> {
            JobDetail jobDetail = new JobDetail(job);
            jobDetail.setPortal_name(portalNameMap.get(job.getPortalId()));
            jobList.add(jobDetail);
        });

        long count = portalJobsTmp.size();
        if (count > 50) {
            count = 50L;
        }
        // sort jobList list

        return createJobPagination(jobList, page, count);
    }

    // TODO: implement here
    public JobPagination getTopJobs(Integer page) {
        return new JobPagination();
    }

    private JobPagination createJobPagination(List<JobDetail> jobList, Integer page, Long count) {
        if (!ObjectUtil.hasValue(jobList, page, count)) {
            return new JobPagination();
        }

        PaginationUtil util = new PaginationUtil(count, page);

        JobPagination pagination = new JobPagination();
        pagination.setCurrentPage(page);
        pagination.setPages(util.getPages());
        pagination.setHasNextPage(util.getHasNextPage());
        pagination.setHasPreviousPage(util.getHasPreviousPage());
        pagination.setJobList(jobList);

        return pagination;
    }

    private Map<Long, String> getPortalNameMap(Long cityId) {
        Map<Long, String> portalNameMap = new HashMap<>();
        List<Portal> portals = portalRepository.findAllByCityId(cityId);
        portals.forEach(portal -> portalNameMap.put(portal.getId(), portal.getName()));
        return portalNameMap;
    }
}
