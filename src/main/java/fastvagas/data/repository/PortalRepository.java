package fastvagas.data.repository;

import fastvagas.data.entity.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Integer> {

    List<Portal> findAll();

    List<Portal> findAllByEnabed(Boolean enabled);

    List<Portal> findAllByCityId(Integer city_id);
}
