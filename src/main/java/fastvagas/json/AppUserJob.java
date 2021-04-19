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
    private List<JobDetail> userJobList;
    private List<JobDetail> lastJobList;
    private List<JobDetail> topJobList;

    public AppUserJob() {
        this.cityName = "";
        this.stateName = "";
        this.monthJobs = 0;
        this.weekJobs = 0;
        this.todayJobs = 0;
        this.userJobList = new ArrayList<>();
        this.lastJobList = new ArrayList<>();
        this.topJobList = new ArrayList<>();
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

    public List<JobDetail> getUserJobList() {
        return userJobList;
    }

    public void setUserJobList(List<JobDetail> userJobList) {
        this.userJobList = userJobList;
    }

    public List<JobDetail> getLastJobList() {
        return lastJobList;
    }

    public void setLastJobList(List<JobDetail> lastJobList) {
        this.lastJobList = lastJobList;
    }

    public List<JobDetail> getTopJobList() {
        return topJobList;
    }

    public void setTopJobList(List<JobDetail> topJobList) {
        this.topJobList = topJobList;
    }
}
