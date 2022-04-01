package fastvagas.data.mapper;

import fastvagas.data.entity.UserJob;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJobRowMapper implements RowMapper<UserJob> {

    @Override
    public UserJob mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserJob.builder()
                .user_id(resultSet.getLong(UserJob.USER_ID))
                .portal_job_id(resultSet.getLong(UserJob.PORTAL_JOB_ID))
                .seen(DateUtil.getLocalDateTimeFromTimestamp(resultSet.getTimestamp(UserJob.SEEN)))
                .build();
    }
}
