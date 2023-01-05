package fastvagas.repository;

import fastvagas.entity.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findByEmail(String email);

  List<Person> findAllByEnabled(Boolean enabled);
}
