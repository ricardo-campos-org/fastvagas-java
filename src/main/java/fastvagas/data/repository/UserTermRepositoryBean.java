package fastvagas.data.repository;

import fastvagas.data.dao.UserTermDao;
import fastvagas.data.entity.UserTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserTermRepositoryBean implements UserTermRepository {

    private final UserTermDao userTermDao;

    @Autowired
    public UserTermRepositoryBean(UserTermDao userTermDao) {
        this.userTermDao = userTermDao;
    }

    @Override
    public List<UserTerm> findAllByUserId(Long user_id) {
        return userTermDao.findAllByUserId(user_id);
    }

    @Override
    public List<UserTerm> findAllEnabledUsersTerms() {
        return userTermDao.findAllEnabledUsersTerms();
    }
}
