package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** This class represents a primary key of {@link UserJob}. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserJobPk {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "job_id")
  private Long jobId;
}
