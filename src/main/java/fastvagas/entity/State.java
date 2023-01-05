package fastvagas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "state")
public class State {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column private String acronym;

  @OneToMany(mappedBy = "state")
  private Set<City> cities;

  public State() {
    this(0L, "", "", new HashSet<>());
  }

  public State(Long id, String name, String acronym, Set<City> cities) {
    this.id = id;
    this.name = name;
    this.acronym = acronym;
    this.cities = cities;
  }

  @Override
  public String toString() {
    return "State{" + "id=" + id + ", name='" + name + '\'' + ", acronym='" + acronym + '\'' + '}';
  }
}
