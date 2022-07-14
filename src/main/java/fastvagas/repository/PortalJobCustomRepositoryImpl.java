package fastvagas.repository;

import fastvagas.entity.PortalJob;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class PortalJobCustomRepositoryImpl implements PortalJobCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PortalJob> findAllByCreatedAtStartintAt(LocalDateTime starting_at) {
        final String query = "SELECT * FROM portal_jobs "
                + "WHERE created_at >= ?1 "
                + "ORDER BY created_at, id";
        log.info("SQL: {}", query);
        Query q = entityManager.createNativeQuery(query, PortalJob.class);
        q.setParameter(1, starting_at);
        List<?> list = q.getResultList();
        log.info("{} row(s)", list.size());
        return (List<PortalJob>) list;
    }
}
