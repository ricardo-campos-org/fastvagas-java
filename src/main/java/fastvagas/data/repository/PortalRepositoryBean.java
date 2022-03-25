package fastvagas.data.repository;

import fastvagas.data.dao.PortalDao;
import fastvagas.data.entity.Portal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PortalRepositoryBean implements PortalRepository {

    private final PortalDao portalDao;

    @Autowired
    public PortalRepositoryBean(PortalDao portalDao) {
        this.portalDao = portalDao;
    }

    @Override
    public List<Portal> findAll() {
        return findAllByStatus('Y');
    }

    @Override
    public List<Portal> findAllByStatus(Character active) {
        return portalDao.findAll(active);
    }

    @Override
    public List<Portal> findAllByUsersActive() {
        return portalDao.findAllByUsersActive();
    }

    @Override
    public List<Portal> findAllByCityId(Long city_id) {
        return portalDao.findAllByCityId(city_id);
    }
}
