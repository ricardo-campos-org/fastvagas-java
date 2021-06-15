package fastvagas.dal.service;

import fastvagas.dal.dao.AuthorityDao;
import fastvagas.dal.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    public Authority create(Authority authority) {
        return authorityDao.create(authority);
    }
}
