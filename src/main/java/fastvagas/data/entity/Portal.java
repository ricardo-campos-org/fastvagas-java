package fastvagas.data.entity;

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

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "portal")
public class Portal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String jobs_uri;

    @Column
    private Integer city_id;

    @Column
    private Boolean enabled;

    @Override
    public String toString() {
        return "Portal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobs_uri='" + jobs_uri + '\'' +
                ", city_id=" + city_id +
                ", enabled=" + enabled +
                '}';
    }
}
