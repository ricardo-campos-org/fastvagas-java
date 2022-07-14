package fastvagas.repository;

import fastvagas.entity.PortalJob;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalJobRepository extends JpaRepository<PortalJob, Long> {

    List<PortalJob> findAllByPortalId(Long portalId);

    @Query(value = "select * from portal_job where created_at >= ?", nativeQuery = true)
    List<PortalJob> findAllByCreatedStartingAt(LocalDateTime startingAt);

    @Query(value = "select p.* from portal_job p join city c on (c.id = p.city_id) "
        + "where c.id = ?", nativeQuery = true)
    List<PortalJob> findAllByCityId(Long cityId);

    @Query(value = "SELECT * FROM portal_job WHERE city_id = ? ORDER BY published_at desc, "
        + "job_title asc", nativeQuery = true)
    List<PortalJob> findAllLastByCityIdPage(Long CityId);

    @Query(value = "SELECT * FROM portal_job WHERE created_at >= ? "
            + "ORDER BY created_at, id", nativeQuery = true)
    List<PortalJob> findAllByCreatedAtStartingAt(LocalDateTime starting_at);
}
