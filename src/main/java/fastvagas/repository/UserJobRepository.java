package fastvagas.repository;

import fastvagas.entity.UserJob;
import fastvagas.entity.UserJobPk;
import org.springframework.data.jpa.repository.JpaRepository;

/** This class holds methods to interact with {@link UserJob} in the database. */
public interface UserJobRepository extends JpaRepository<UserJob, UserJobPk> {}
