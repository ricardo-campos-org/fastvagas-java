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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "crawler_log")
public class CrawlerLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "portal_id")
    private Long portalId;

    @Column
    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "crowler_log={"
                + id + "=" + id
                + "," + portalId + "=" + portalId
                + "," + text + "='" + text + "'"
                + "," + createdAt + "='" + DateUtil.formatLocalDateTime(createdAt) + "'"
                + "}";
    }
}
