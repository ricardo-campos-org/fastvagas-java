package fastvagas.data.repository;

import fastvagas.data.entity.PortalJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalJobRepository extends JpaRepository<PortalJob, Integer>, PortalJobCustomRepository {

    List<PortalJob> findAllByPortalId(Integer portal_id);
}
