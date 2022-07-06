package fastvagas.data.entity;

import fastvagas.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "portal_jobs")
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
    @Column(name = "job_uri")
    private String jobUri;

    @Column(name = "portal_id")
    private Long portalId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "PortalJob{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobType='" + jobType + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", job_uri='" + jobUri + '\'' +
                ", portalId=" + portalId +
                ", createdAt=" + createdAt +
                '}';
    }

    public boolean isValid() {
        return ObjectUtil.hasValue(jobTitle) && ObjectUtil.hasValue(jobUri);
    }
}
