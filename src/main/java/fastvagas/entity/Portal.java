package fastvagas.entity;

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
@EqualsAndHashCode
@Entity(name = "portal")
public class Portal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "jobs_url")
    private String jobsUrl;

    @Column(name = "city_id")
    private Long cityId;

    @Column
    private Boolean enabled;

    public Portal() {
        this(0L, "", "", 0L, Boolean.TRUE);
    }

    public Portal(Long id, String name, String jobsUrl,
                  Long cityId, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.jobsUrl = jobsUrl;
        this.cityId = cityId;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Portal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobsUrl='" + jobsUrl + '\'' +
                ", cityId=" + cityId +
                ", enabled=" + enabled +
                '}';
    }
}
