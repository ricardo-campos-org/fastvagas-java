package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column private String email;

  @Column private String password;

  @Column(name = "city_id")
  private Long cityId;

  @Column private Boolean enabled;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "disabled_at")
  private LocalDateTime disabledAt;

  @Column(name = "terms")
  private String terms;

  @Override
  public String toString() {
    return "Person{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", password='"
        + password
        + '\''
        + ", cityId="
        + cityId
        + ", enabled="
        + enabled
        + ", lastLogin="
        + lastLogin
        + ", createdAt="
        + createdAt
        + ", disabledAt="
        + disabledAt
        + ", terms='"
        + terms
        + '\''
        + '}';
  }
}
