package fastvagas.data.entity;

import fastvagas.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "crowler_log")
public class CrowlerLog {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Long portal_id;

    @Column
    private String text;

    @Column
    private LocalDate created_at;

    @Override
    public String toString() {
        return "crowler_log={"
                + id + "=" + id
                + "," + portal_id + "=" + portal_id
                + "," + text + "='" + text + "'"
                + "," + created_at + "='" + DateUtil.formatLocalDate(created_at) + "'"
                + "}";
    }
}
