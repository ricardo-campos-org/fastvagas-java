package fastvagas.dal.service;

import fastvagas.dal.dao.PortalDao;
import fastvagas.dal.entity.Portal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalService {

    @Autowired
    private PortalDao portalDao;

    public List<Portal> findAll() {
        return findAllByStatus('Y');
    }

    public List<Portal> findAllByStatus(Character active) {
        return portalDao.findAll(active);
    }

    public List<Portal> findAllByUsersActive() {
        return portalDao.findAllByUsersActive();
    }

    public List<Portal> findAllByCityId(Long city_id) {
        return portalDao.findAllByCityId(city_id);
    }
}
