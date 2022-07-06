package fastvagas.data.repository;

import fastvagas.data.entity.PersonTerm;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTermRepository extends JpaRepository<PersonTerm, Long> {

  List<PersonTerm> findAllByPersonId(Long userId);
}
