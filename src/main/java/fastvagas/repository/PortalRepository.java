package fastvagas.repository;

import fastvagas.entity.Portal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** This class holds methods to interact with {@link Portal} in the database. */
public interface PortalRepository extends JpaRepository<Portal, Long> {

  List<Portal> findAllByEnabled(Boolean enabled);
}
