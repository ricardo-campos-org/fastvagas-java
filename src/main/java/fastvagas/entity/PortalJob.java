package fastvagas.entity;

import fastvagas.util.ObjectUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "portal_job")
public class PortalJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=600)
    @Column(name = "job_title")
    private String jobTitle;

    @Size(max=600)
    @Column(name = "company_name")
    private String companyName;

    @Size(max=30)
    @Column(name = "job_type")
    private String jobType;

    @Size(max=600)
    @Column(name = "job_description")
    private String jobDescription;

    @Size(max=30)
    @Column(name = "published_at")
    private String publishedAt;

    @Size(max=1000)
    @Column(name = "job_url")
    private String jobUrl;

    @Column(name = "portal_id")
    private Long portalId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public PortalJob() {
        this(0L, "", "", "", "", "", "",
                0L, LocalDateTime.now());
    }

    public PortalJob(Long id, String jobTitle, String companyName,
                     String jobType, String jobDescription,
                     String publishedAt, String jobUrl, Long portalId,
                     LocalDateTime createdAt) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.jobType = jobType;
        this.jobDescription = jobDescription;
        this.publishedAt = publishedAt;
        this.jobUrl = jobUrl;
        this.portalId = portalId;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PortalJob{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobType='" + jobType + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", jobUrl='" + jobUrl + '\'' +
                ", portalId=" + portalId +
                ", createdAt=" + createdAt +
                '}';
    }

    public boolean isValid() {
        boolean valid =
            ObjectUtil.hasValue(jobTitle) && ObjectUtil.hasValue(jobUrl);

        if (valid) {
            jobTitle = ObjectUtil.fixMaxLength(jobTitle, 600);
            companyName = ObjectUtil.fixMaxLength(companyName, 600);
            jobType = ObjectUtil.fixMaxLength(jobType, 30);
            jobDescription = ObjectUtil.fixMaxLength(jobDescription, 600);
            publishedAt = ObjectUtil.fixMaxLength(publishedAt, 30);

            if (jobUrl.length() > 1000) {
                return false;
            }
        }

        return valid;
    }
}
