package fastvagas.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppUserJob implements Serializable {

    private static final long serialVersionUID = -6640904968315898140L;
    private String cityName;
    private String stateName;
    private Integer monthJobs;
    private Integer weekJobs;
    private Integer todayJobs;
    private JobPagination userJobPagination;
    private JobPagination lastJobPagination;
    private JobPagination topJobPagination;

    public AppUserJob() {
        this.cityName = "";
        this.stateName = "";
        this.monthJobs = 0;
        this.weekJobs = 0;
        this.todayJobs = 0;
        this.userJobPagination = new JobPagination();
        this.lastJobPagination = new JobPagination();
        this.topJobPagination = new JobPagination();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getMonthJobs() {
        return monthJobs;
    }

    public void setMonthJobs(Integer monthJobs) {
        this.monthJobs = monthJobs;
    }

    public Integer getWeekJobs() {
        return weekJobs;
    }

    public void setWeekJobs(Integer weekJobs) {
        this.weekJobs = weekJobs;
    }

    public Integer getTodayJobs() {
        return todayJobs;
    }

    public void setTodayJobs(Integer todayJobs) {
        this.todayJobs = todayJobs;
    }

    public JobPagination getUserJobPagination() {
        return userJobPagination;
    }

    public void setUserJobPagination(JobPagination userJobPagination) {
        this.userJobPagination = userJobPagination;
    }

    public JobPagination getLastJobPagination() {
        return lastJobPagination;
    }

    public void setLastJobPagination(JobPagination lastJobPagination) {
        this.lastJobPagination = lastJobPagination;
    }

    public JobPagination getTopJobPagination() {
        return topJobPagination;
    }

    public void setTopJobPagination(JobPagination topJobPagination) {
        this.topJobPagination = topJobPagination;
    }
}
