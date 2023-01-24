package fastvagas.repository;

import fastvagas.entity.CrawlerLog;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlerLogRepository extends JpaRepository<CrawlerLog, Integer> {

  @Query(
      value = "SELECT * FROM crawler_log WHERE created_at >= ? ORDER BY created_at, id",
      nativeQuery = true)
  List<CrawlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime);
}
