package fastvagas.data.repository;

import fastvagas.data.entity.CrawlerLog;

import java.time.LocalDateTime;
import java.util.List;

public interface CrowlerLogCustomRepository {

    List<CrawlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime);

    List<CrawlerLog> fromStringArray(String[] logs, Long portalId);
}
