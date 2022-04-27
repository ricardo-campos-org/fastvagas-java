package fastvagas.data.repository;

import fastvagas.data.entity.PersonJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonJobRepository extends JpaRepository<PersonJob, Integer> {

    List<PersonJob> findAllByPersonId(Integer person_id);
}
