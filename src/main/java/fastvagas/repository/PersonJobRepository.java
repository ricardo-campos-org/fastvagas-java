package fastvagas.repository;

import fastvagas.entity.PersonJob;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJobRepository extends JpaRepository<PersonJob, Integer> {

  List<PersonJob> findAllByPersonId(Long person_id);
}
