package fastvagas.data.repository;

import fastvagas.data.entity.UserJob;

import java.util.List;

public interface UserJobRepository {

    void createBatch(List<UserJob> userJobs);

    List<UserJob> findAllNotSeen(Long user_id);

    boolean exists(Long user_id, Long portal_job_id);
}
