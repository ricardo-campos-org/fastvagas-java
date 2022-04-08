package fastvagas.data.repository;

import fastvagas.data.dao.UserJobDao;
import fastvagas.data.entity.UserJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class UserJobRepositoryBean implements UserJobRepository {

    private final UserJobDao userJobDao;

    @Autowired
    public UserJobRepositoryBean(UserJobDao userJobDao) {
        this.userJobDao = userJobDao;
    }

    @Override
    public void createBatch(List<UserJob> userJobs) {
        userJobDao.createBatch(userJobs);
    }

    @Override
    public List<UserJob> findAllNotSeen(Long user_id) {
        return userJobDao.findAllNotSeen(user_id);
    }

    @Override
    public boolean exists(Long user_id, Long portal_job_id) {
        return userJobDao.findById(user_id, portal_job_id) != null;
    }
}
