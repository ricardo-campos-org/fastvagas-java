package fastvagas.dal.service;

import fastvagas.dal.dao.UserTermPortalDao;
import fastvagas.dal.vo.UserTermPortal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTermPortalService {

    @Autowired
    private UserTermPortalDao userTermPortalDao;

    public List<UserTermPortal> findAllByPortalId(Long portal_id) {
        return userTermPortalDao.findAllByPortalId(portal_id);
    }
}
