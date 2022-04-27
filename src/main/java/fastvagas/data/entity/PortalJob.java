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
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "portal_jobs")
public class PortalJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max=600)
    @Column
    private String job_title;

    @Size(max=600)
    @Column
    private String company_name;

    @Size(max=30)
    @Column
    private String job_type;

    @Size(max=600)
    @Column
    private String job_description;

    @Size(max=30)
    @Column
    private String published_at;

    @Size(max=1000)
    @Column
    private String job_uri;

    @Column
    private Integer portal_id;

    @Column
    private LocalDateTime created_at;

    @Override
    public String toString() {
        return "PortalJob{" +
                "id=" + id +
                ", job_title='" + job_title + '\'' +
                ", company_name='" + company_name + '\'' +
                ", job_type='" + job_type + '\'' +
                ", job_description='" + job_description + '\'' +
                ", published_at='" + published_at + '\'' +
                ", job_uri='" + job_uri + '\'' +
                ", portal_id=" + portal_id +
                ", created_at=" + created_at +
                '}';
    }
}
