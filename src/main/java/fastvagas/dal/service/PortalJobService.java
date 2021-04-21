package fastvagas.dal.service;

import fastvagas.dal.dao.PortalJobDao;
import fastvagas.dal.entity.PortalJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PortalJobService {

    @Autowired
    private PortalJobDao portalJobDao;

    public List<PortalJob> createBatch(List<PortalJob> list) {
        return portalJobDao.createBatch(list);
    }

    public List<PortalJob> findAllByPortalIdPublishedRange(Long portal_id, Date published_at_start) {
        return portalJobDao.findAllByPortalIdPublishedRange(portal_id, published_at_start);
    }

    public List<PortalJob> findAllByPortalIdPublishedRangePage(Long portal_id, Date published_at_start,
                                                               Integer page) {
        return portalJobDao.findAllByPortalIdPublishedRangePage(portal_id, published_at_start, page);
    }

    public Map<String, PortalJob> listToMapByUrl(List<PortalJob> list) {
        Map<String, PortalJob> portalJobMap = new HashMap<>();
        list.forEach((portalJob) -> portalJobMap.put(portalJob.getUrl(), portalJob));
        return portalJobMap;
    }
}
