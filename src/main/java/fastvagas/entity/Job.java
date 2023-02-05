package fastvagas.entity;

import fastvagas.util.StringUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** This class represents a job in the system. */
@Getter
@Setter
@Entity
@Table(name = "job")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 50)
  @Column(name = "job_title")
  private String jobTitle;

  @Size(max = 50)
  @Column(name = "company_name")
  private String companyName;

  @Size(max = 20)
  @Column(name = "job_type")
  private String jobType;

  @Size(max = 300)
  @Column(name = "job_description")
  private String jobDescription;

  @Size(max = 30)
  @Column(name = "published_at")
  private String publishedAt;

  @Size(max = 200)
  @Column(name = "job_url")
  private String jobUrl;

  @Column(name = "portal_id")
  private Long portalId;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  /**
   * Check if a job that was just read from the source is valid.
   *
   * @return true if is, false otherwise
   */
  public boolean isValid() {
    boolean valid = !jobTitle.isBlank() && !jobUrl.isBlank();

    if (valid) {
      jobTitle = StringUtil.fixMaxLength(jobTitle, 600);
      companyName = StringUtil.fixMaxLength(companyName, 600);
      jobType = StringUtil.fixMaxLength(jobType, 30);
      jobDescription = StringUtil.fixMaxLength(jobDescription, 600);
      publishedAt = StringUtil.fixMaxLength(publishedAt, 30);

      if (jobUrl.length() > 1000) {
        return false;
      }
    }

    return valid;
  }
}
