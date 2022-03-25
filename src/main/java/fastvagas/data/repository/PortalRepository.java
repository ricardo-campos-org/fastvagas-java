package fastvagas.data.repository;

import fastvagas.data.entity.Portal;

import java.util.List;

public interface PortalRepository {

    List<Portal> findAll();

    List<Portal> findAllByStatus(Character active);

    List<Portal> findAllByUsersActive();

    List<Portal> findAllByCityId(Long city_id);
}
