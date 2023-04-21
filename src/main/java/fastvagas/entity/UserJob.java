package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** This class represents a {@link Job} found to a {@link UserEntity}. */
@Getter
@Setter
@Entity
@Table(name = "user_job")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserJob {

  @EmbeddedId private UserJobPk id;

  @Column(name = "email_sent")
  private LocalDateTime emailSent;

  @Column private String term;
}
