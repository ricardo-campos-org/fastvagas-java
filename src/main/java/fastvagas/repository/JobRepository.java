package fastvagas.repository;

import fastvagas.entity.Job;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** This class holds method to interact with {@link Job} in the database. */
public interface JobRepository extends JpaRepository<Job, Long> {

  List<Job> findAllByPortalId(Long portalId);

  @Query(value = "select j.* from fjobs.job j where j.created_at >= ?1", nativeQuery = true)
  List<Job> findAllByCreatedStartingAt(LocalDateTime startingAt);
}
