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
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "person_jobs")
public class PersonJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "portal_job_id")
    private Long portalJobId;

    @Column
    private LocalDateTime seen;

    @Override
    public String toString() {
        return "UserJob{" +
                "id=" + id +
                ", personId=" + personId +
                ", portalJobId=" + portalJobId +
                ", seen=" + seen +
                '}';
    }
}
