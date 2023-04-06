package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** This class represents a portal in the system. A portal holds the {@link Job} */
@Builder
@Getter
@Setter
@Entity
@Table(name = "portal")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Portal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column(name = "search_url")
  private String searchUrl;

  @Column private String city;

  @Column private String state;

  @Column private boolean enabled;
}
