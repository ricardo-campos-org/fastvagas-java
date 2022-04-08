package fastvagas.jpa;

import fastvagas.data.entity.CrowlerLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CrowlerLogRepository extends JpaRepository<CrowlerLog, Integer> {

    List<CrowlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime);

    List<CrowlerLog> fromStringArray(String[] logs, Long portal_id);
}
