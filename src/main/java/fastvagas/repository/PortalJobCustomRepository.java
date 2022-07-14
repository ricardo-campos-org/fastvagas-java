package fastvagas.repository;

import fastvagas.entity.PortalJob;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PortalJobCustomRepository {

    List<PortalJob> findAllByCreatedAtStartintAt(LocalDateTime starting_at);
}
