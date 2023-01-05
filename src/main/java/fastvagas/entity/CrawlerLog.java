package fastvagas.entity;

import fastvagas.util.DateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "crawler_log")
public class CrawlerLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "portal_id")
  private Long portalId;

  @Column private String text;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public CrawlerLog() {
    this(0L, 0L, "", null);
  }

  public CrawlerLog(Long id, Long portalId, String text, LocalDateTime createdAt) {
    this.id = id;
    this.portalId = portalId;
    this.text = text;
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Crawler_log={"
        + id
        + "="
        + id
        + ","
        + portalId
        + "="
        + portalId
        + ","
        + text
        + "='"
        + text
        + "'"
        + ","
        + createdAt
        + "='"
        + DateUtil.formatLocalDateTime(createdAt)
        + "'"
        + "}";
  }
}
