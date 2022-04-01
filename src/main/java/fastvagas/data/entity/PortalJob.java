package fastvagas.data.entity;

import fastvagas.util.DateUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class PortalJob {
    public static final String TABLE = "portal_jobs";
    public static final String PORTAL_JOB_ID = "portal_job_id";
    public static final String NAME = "name";
    public static final String COMPANY_NAME = "company_name";
    public static final String JOB_TYPE = "job_type";
    public static final String DESCRIPTION = "description";
    public static final String PUBLISHED_AT = "published_at";
    public static final String URL = "url";
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
    private Long portal_id;
    private Long city_id;
    private LocalDateTime created_at;

    public PortalJob() {
        this.created_at = DateUtil.getCurrentLocalDateTime();
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
            + "," + PORTAL_ID + "=" + portal_id
            + "," + CITY_ID + "=" + city_id
            + "," + CREATED_AT + "='" + DateUtil.formatLocalDateTime(created_at) + "'"
            + "}";
    }
}
