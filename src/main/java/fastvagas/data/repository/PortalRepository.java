package fastvagas.data.repository;

import fastvagas.data.entity.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {

    List<Portal> findAll();

    List<Portal> findAllByEnabled(Boolean enabled);

    List<Portal> findAllByCityId(Long city_id);
}
