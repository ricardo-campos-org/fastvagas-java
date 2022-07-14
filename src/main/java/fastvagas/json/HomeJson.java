package fastvagas.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeJson {

    private Long cityId;
    private String cityName;
    private String stateName;
    private Integer monthJobs;
    private Integer weekJobs;
    private Integer todayJobs;
    private JobPagination userJobPagination;
    private JobPagination lastJobPagination;
    private JobPagination topJobPagination;

    public HomeJson() {
        this.cityId = 0L;
        this.cityName = "";
        this.stateName = "";
        this.monthJobs = 0;
        this.weekJobs = 0;
        this.todayJobs = 0;
        this.userJobPagination = new JobPagination();
        this.lastJobPagination = new JobPagination();
        this.topJobPagination = new JobPagination();
    }

}
