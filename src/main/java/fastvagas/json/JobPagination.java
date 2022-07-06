package fastvagas.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JobPagination {

    private Integer currentPage;
    private List<Integer> pages;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    private List<JobDetail> jobList;

    public JobPagination() {
        this.currentPage = 0;
        this.pages = new ArrayList<>();
        this.hasNextPage = Boolean.FALSE;
        this.hasPreviousPage = Boolean.FALSE;
        this.jobList = new ArrayList<>();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public List<JobDetail> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobDetail> jobList) {
        this.jobList = jobList;
    }
}
