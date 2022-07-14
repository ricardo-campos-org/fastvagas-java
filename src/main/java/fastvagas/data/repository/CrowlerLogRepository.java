package fastvagas.data.repository;

import fastvagas.data.entity.CrawlerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrowlerLogRepository extends JpaRepository<CrawlerLog, Integer>, CrowlerLogCustomRepository {

}
