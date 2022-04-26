package fastvagas.jpa;

import fastvagas.data.entity.CrowlerLog;
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
    public List<CrowlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime) {
        final String query = "SELECT * FROM crowler_log "
                + "WHERE created_at >= ?1 "
                + "ORDER BY created_at, id";
        Query q = entityManager.createNativeQuery(query, CrowlerLog.class);
        q.setParameter(1, localDateTime);
        List<?> list = q.getResultList();
        log.info("{} row(s)", list.size());
        return (List<CrowlerLog>) list;
    }

    @Override
    public List<CrowlerLog> fromStringArray(String[] logs, Long portal_id) {
        List<CrowlerLog> crowlerLogs = new ArrayList<>(logs.length);
        for (String log : logs) {
            CrowlerLog crowlerLog = CrowlerLog.builder()
                    .created_at(DateUtil.getCurrentLocalDateTime())
                    .portal_id(portal_id)
                    .text(log)
                    .build();
            crowlerLogs.add(crowlerLog);
        }

        return crowlerLogs;
    }
}
