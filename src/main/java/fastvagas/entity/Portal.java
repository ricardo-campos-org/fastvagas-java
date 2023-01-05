package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "portal")
public class Portal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column(name = "jobs_url")
  private String jobsUrl;

  @Column(name = "city_id")
  private Long cityId;

  @Column private Boolean enabled;

  public Portal() {
    this(0L, "", "", 0L, Boolean.TRUE);
  }

  public Portal(Long id, String name, String jobsUrl, Long cityId, Boolean enabled) {
    this.id = id;
    this.name = name;
    this.jobsUrl = jobsUrl;
    this.cityId = cityId;
    this.enabled = enabled;
  }

  @Override
  public String toString() {
    return "Portal{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", jobsUrl='"
        + jobsUrl
        + '\''
        + ", cityId="
        + cityId
        + ", enabled="
        + enabled
        + '}';
  }
}
