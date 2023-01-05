package fastvagas.repository;

import fastvagas.entity.Portal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {

  List<Portal> findAll();

  List<Portal> findAllByEnabled(Boolean enabled);

  List<Portal> findAllByCityId(Long city_id);
}
