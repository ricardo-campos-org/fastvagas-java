package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** This class represents a user in the system. */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", length = 15, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 30, nullable = false)
  private String lastName;

  @Column(length = 100, nullable = false)
  private String email;

  @Column(length = 30, nullable = false)
  private String city;

  @Column(length = 30, nullable = false)
  private String state;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "disabled_at")
  private LocalDateTime disabledAt;

  @Column(length = 200, nullable = false)
  private String terms;

  @Column(name = "last_search")
  private LocalDateTime lastSearch;
}
