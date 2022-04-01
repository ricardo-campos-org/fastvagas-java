package fastvagas.data.dao;

import fastvagas.data.entity.UserJob;
import fastvagas.data.mapper.UserJobRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserJobDao extends Dao<UserJob> {

    public UserJobDao(NamedParameterJdbcTemplate template) {
        super(UserJob.class, template, new UserJobRowMapper());
    }

    public UserJob findById(Long user_id, Long portal_job_id) {
        final String query = "SELECT * FROM " + UserJob.TABLE
            + " WHERE " + UserJob.USER_ID + "=:" + UserJob.USER_ID
            + " AND " + UserJob.PORTAL_JOB_ID + "=:" + UserJob.PORTAL_JOB_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(UserJob.USER_ID, user_id)
            .addValue(UserJob.PORTAL_JOB_ID, portal_job_id);

        return getObjectFromResult(query, params);
    }

    public List<UserJob> findAllNotSeen(Long user_id) {
        final String query = "SELECT * FROM " + UserJob.TABLE
                + " WHERE " + UserJob.USER_ID + "=:" + UserJob.USER_ID
                + " AND " + UserJob.SEEN + " IS NULL;";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue(UserJob.USER_ID, user_id);

        return getListFromResult(query, params);
    }

    public UserJob create(UserJob userJob) {
        final String query = "INSERT INTO " + UserJob.TABLE + " ("
            + UserJob.USER_ID
            + "," + UserJob.PORTAL_JOB_ID
            + "," + UserJob.SEEN
            + ") values ("
            + ":" + UserJob.USER_ID
            + ",:" + UserJob.PORTAL_JOB_ID
            + ",:" + UserJob.SEEN
            + ")";

        if (executeInsert(query, getParams(userJob)) == 1) {
            return userJob;
        }

        return null;
    }

    public List<UserJob> createBatch(List<UserJob> userJobs) {
        final String query = "INSERT INTO " + UserJob.TABLE + " ("
                + UserJob.USER_ID
                + "," + UserJob.PORTAL_JOB_ID
                + "," + UserJob.SEEN
                + ") values ("
                + ":" + UserJob.USER_ID
                + ",:" + UserJob.PORTAL_JOB_ID
                + ",:" + UserJob.SEEN
                + ")";

        if (executeInsertBatch(query, userJobs).length > 0) {
            return userJobs;
        }

        return null;
    }

    public UserJob deleteById(UserJob userJob) {
        final String query = "DELETE FROM " + UserJob.TABLE
            + " WHERE " + UserJob.USER_ID + "=:" + UserJob.USER_ID
            + " AND " + UserJob.PORTAL_JOB_ID + "=:" + UserJob.PORTAL_JOB_ID;

        if (executeUpdateDelete(query, getParams(userJob)) == 1) {
            return userJob;
        }

        return null;
    }

    private Map<String, Object> getParams(UserJob userJob) {
        Map<String, Object> params = new HashMap<>();
        params.put(UserJob.USER_ID, userJob.getUser_id());
        params.put(UserJob.PORTAL_JOB_ID, userJob.getPortal_job_id());
        params.put(UserJob.SEEN, userJob.getSeen());
        return params;
    }
}
