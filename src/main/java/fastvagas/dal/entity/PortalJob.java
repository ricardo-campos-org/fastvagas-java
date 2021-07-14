package fastvagas.dal.entity;

import fastvagas.util.DateUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class PortalJob {
    public static final String TABLE = "portal_jobs";
    public static final String PORTAL_JOB_ID = "portal_job_id";
    public static final String NAME = "name";
    public static final String COMPANY_NAME = "company_name";
    public static final String JOB_TYPE = "job_type";
    public static final String DESCRIPTION = "description";
    public static final String PUBLISHED_AT = "published_at";
    public static final String URL = "url";
    public static final String SEEN = "seen";
    public static final String PORTAL_ID = "portal_id";
    public static final String CITY_ID = "city_id";
    public static final String CREATED_AT = "created_at";

    private Long portal_job_id;
    private String name;
    private String company_name;
    private String job_type;
    private String description;
    private String published_at;
    private String url;
    private Date seen;
    private Long portal_id;
    private Long city_id;
    private LocalDateTime createdAt;

    public PortalJob() {
        this.createdAt = DateUtil.getCurrentLocalDateTime();
    }

    public Long getPortal_job_id() {
        return portal_job_id;
    }

    public void setPortal_job_id(Long portal_job_id) {
        this.portal_job_id = portal_job_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getSeen() {
        return seen;
    }

    public void setSeen(Date seen) {
        this.seen = seen;
    }

    public Long getPortal_id() {
        return portal_id;
    }

    public void setPortal_id(Long portal_id) {
        this.portal_id = portal_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortalJob portalJob = (PortalJob) o;
        return portal_job_id.equals(portalJob.portal_job_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portal_job_id);
    }

    @Override
    public String toString() {
        return "portal_job={"
            + PORTAL_JOB_ID + "=" + portal_job_id
            + "," + NAME + "='" + name + "'"
            + "," + COMPANY_NAME + "='" + company_name + "'"
            + "," + JOB_TYPE + "='" + job_type + "'"
            + "," + DESCRIPTION + "='" + description + "'"
            + "," + PUBLISHED_AT + "='" + published_at + "'"
            + "," + URL + "='" + url + "'"
            + "," + SEEN + "='" + DateUtil.formatDate(seen, true) + "'"
            + "," + PORTAL_ID + "=" + portal_id
            + "," + CITY_ID + "=" + city_id
            + "," + CREATED_AT + "='" + DateUtil.formatLocalDateTime(createdAt) + "'"
            + "}";
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
