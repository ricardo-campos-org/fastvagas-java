package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "person_job")
public class PersonJob {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "person_id")
  private Long personId;

  @Column(name = "portal_job_id")
  private Long portalJobId;

  @Column private LocalDateTime seen;

  public PersonJob() {
    this(0, 0L, 0L, null);
  }

  public PersonJob(Integer id, Long personId, Long portalJobId, LocalDateTime seen) {
    this.id = id;
    this.personId = personId;
    this.portalJobId = portalJobId;
    this.seen = seen;
  }

  @Override
  public String toString() {
    return "UserJob{"
        + "id="
        + id
        + ", personId="
        + personId
        + ", portalJobId="
        + portalJobId
        + ", seen="
        + seen
        + '}';
  }
}
