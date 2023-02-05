package fastvagas.repository;

import fastvagas.entity.UserJob;
import fastvagas.entity.UserJobPk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** This class holds methods to interact with {@link UserJob} in the database. */
public interface UserJobRepository extends JpaRepository<UserJob, UserJobPk> {

  @Query(value = "select * from fjobs.user_job where user_id = ?1", nativeQuery = true)
  List<UserJob> findAllByUserId(Long userId);
}
