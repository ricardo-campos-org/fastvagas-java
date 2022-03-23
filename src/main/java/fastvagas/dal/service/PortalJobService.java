package fastvagas.dal.service;

import fastvagas.dal.dao.PortalJobDao;
import fastvagas.dal.entity.PortalJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortalJobService {

    @Autowired
    PortalJobDao portalJobDao;

    public List<PortalJob> createBatch(List<PortalJob> list) {
        return portalJobDao.createBatch(list);
    }

    public List<PortalJob> findAllByPortalIdPublishedRange(Long portal_id, Date published_at_start) {
        return portalJobDao.findAllByPortalIdPublishedRange(portal_id, published_at_start);
    }

    public List<PortalJob> findAllByCityIdPublishedRange(Long city_id, Date published_at_start) {
        return portalJobDao.findAllByCityIdPublishedRange(city_id, published_at_start);
    }

    public List<PortalJob> findAllLastByCityIdPage(Long city_id, Integer page) {
        return portalJobDao.findAllLastByCityIdPage(city_id, page);
    }

    public List<PortalJob> findAllLastByCityId(Long city_id) {
        return portalJobDao.findAllLastByCityId(city_id);
    }

    public Map<String, PortalJob> listToMapByUrl(List<PortalJob> list) {
        Map<String, PortalJob> portalJobMap = new HashMap<>();
        list.forEach((portalJob) -> portalJobMap.put(portalJob.getUrl(), portalJob));
        return portalJobMap;
    }

    public List<PortalJob> findAllByCreatedAt(LocalDateTime localDateTime) {
        return portalJobDao.findAllByCreatedAt(localDateTime);
    }
}
