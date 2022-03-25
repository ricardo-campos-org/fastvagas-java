package fastvagas.data.mapper;

import fastvagas.data.entity.UserJob;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJobRowMapper implements RowMapper<UserJob> {

    @Override
    public UserJob mapRow(ResultSet resultSet, int i) throws SQLException {
        UserJob userJob = new UserJob();
        userJob.setUser_id(resultSet.getLong(UserJob.USER_ID));
        userJob.setPortal_job_id(resultSet.getLong(UserJob.PORTAL_JOB_ID));
        return userJob;
    }
}
