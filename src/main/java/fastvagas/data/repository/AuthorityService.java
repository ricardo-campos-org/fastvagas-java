package fastvagas.data.repository;

import fastvagas.data.dao.AuthorityDao;
import fastvagas.data.entity.Authority;
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
