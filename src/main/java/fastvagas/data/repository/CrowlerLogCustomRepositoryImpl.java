package fastvagas.data.repository;

import fastvagas.data.entity.CrawlerLog;
import fastvagas.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CrowlerLogCustomRepositoryImpl implements CrowlerLogCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CrawlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime) {
        final String query = "SELECT * FROM crowler_log "
                + "WHERE created_at >= ?1 "
                + "ORDER BY created_at, id";
        log.info("SQL: {}", query);
        Query q = entityManager.createNativeQuery(query, CrawlerLog.class);
        q.setParameter(1, localDateTime);
        List<?> list = q.getResultList();
        log.info("{} row(s)", list.size());
        return (List<CrawlerLog>) list;
    }

    @Override
    public List<CrawlerLog> fromStringArray(String[] logs, Long portal_id) {
        List<CrawlerLog> crawlerLogs = new ArrayList<>(logs.length);
        for (String log : logs) {
            CrawlerLog crawlerLog = CrawlerLog.builder()
                    .createdAt(DateUtil.getCurrentLocalDateTime())
                    .portalId(portal_id)
                    .text(log)
                    .build();
            crawlerLogs.add(crawlerLog);
        }

        return crawlerLogs;
    }
}
