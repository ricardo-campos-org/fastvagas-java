package fastvagas.json;

import fastvagas.entity.PortalJob;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobDetail {

    private Long portal_job_id;
    private String name;
    private String company_name;
    private String job_type;
    private String description;
    private String published_at;
    private String url;
    private Long portal_id;
    private String portal_name;
    private LocalDateTime created_at;

    public JobDetail(PortalJob portalJob) {
        setPortal_job_id(portalJob.getId());
        setName(portalJob.getJobTitle());
        setCompany_name(portalJob.getCompanyName());
        setJob_type(portalJob.getJobType());
        setDescription(portalJob.getJobDescription());
        setPublished_at(portalJob.getPublishedAt());
        setUrl(portalJob.getJobUrl());
        setPortal_id(portalJob.getPortalId());
        setCreated_at(portalJob.getCreatedAt());
    }
}
