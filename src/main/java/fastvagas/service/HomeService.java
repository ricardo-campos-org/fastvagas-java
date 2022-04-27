package fastvagas.service;

import fastvagas.data.entity.*;
import fastvagas.data.repository.PortalRepository;
import fastvagas.exception.EntityNotFoundException;
import fastvagas.data.repository.CityRepository;
import fastvagas.json.HomeJson;
import fastvagas.json.JobDetail;
import fastvagas.json.JobPagination;
import fastvagas.util.DateUtil;
import fastvagas.util.ObjectUtil;
import fastvagas.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeService {

    private final CityRepository cityRepository;
    private final PortalJobService portalJobService;
    private final PortalRepository portalRepository;

    @Autowired
    public HomeService(CityRepository cityRepository, PortalJobService portalJobService,
                       PortalRepository portalRepository) {
        this.cityRepository = cityRepository;
        this.portalJobService = portalJobService;
        this.portalRepository = portalRepository;
    }

    public HomeJson getAllJobs(Person person) {
        Optional<City> city = cityRepository.findById(person.getCity_id());
        if (city.isEmpty()) {
            throw new EntityNotFoundException(City.class, "city_id", String.valueOf(person.getCity_id()));
        }

        State state = city.get().getState();
        if (state == null) {
            throw new EntityNotFoundException(State.class, "state_id", "state mapped by city");
        }

        final Date today = new Date();
        final Integer month = DateUtil.getMonthFromDate(today);
        final Integer year = DateUtil.getYearFromDate(today);
        final Date firstDayOfMonth = DateUtil.createDate(1, month, year);

        Calendar c = Calendar.getInstance();
        c.setTime(today);

        final Date weekDate = DateUtil.subtractDays(today, (c.get(Calendar.DAY_OF_WEEK)-1));

        int weekJobs = 0;
        int todayJobs = 0;

        List<PortalJob> cityJobs = portalJobService.findAllByCityIdPublishedRange(
                city.get().getId(),
                firstDayOfMonth
        );

        for (PortalJob portalJob : cityJobs) {
            Date dataLeitura = DateUtil.getDateFromLocalDateTime(portalJob.getCreated_at());
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
        homeJson.setUserJobPagination(createJobPagination(null, null, null));
        homeJson.setLastJobPagination(getLastJobs(city.get().getId(), null));
        homeJson.setTopJobPagination(createJobPagination(null, null, null));

        return homeJson;
    }

    // TODO: implement here
    public JobPagination getUserJobs(Person person, Integer page) {
        return new JobPagination();
    }

    public JobPagination getLastJobs(Integer city_id, Integer page) {
        if (!ObjectUtil.hasValue(page)) {
            page = 1;
        }

        // Map para agilizar
        Map<Integer, String> portalNameMap = new HashMap<>();
        List<Portal> portals = portalRepository.findAllByCityId(city_id);
        portals.forEach(portal -> portalNameMap.put(portal.getId(), portal.getName()));

        List<JobDetail> jobList = new ArrayList<>();

        List<PortalJob> portalJobsTmp = portalJobService.findAllLastByCityIdPage(city_id, page);
        portalJobsTmp.forEach((job) -> {
            JobDetail jobDetail = new JobDetail(job);
            jobDetail.setPortal_name(portalNameMap.get(job.getPortal_id()));
            jobList.add(jobDetail);
        });

        long count = portalJobService.findAllLastByCityId(city_id).size();
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
}
