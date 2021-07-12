package fastvagas.dal.mapper;

import fastvagas.dal.entity.User;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUser_id(resultSet.getLong(User.USER_ID));
        user.setFirst_name(resultSet.getString(User.FIRST_NAME));
        user.setLast_name(resultSet.getString(User.LAST_NAME));
        user.setEmail(resultSet.getString(User.EMAIL));
        user.setPassword(resultSet.getString(User.PASSWORD));
        user.setCity_id(resultSet.getLong(User.CITY_ID));
        user.setCreated_at(DateUtil.getDateFromTimestamp(resultSet.getTimestamp(User.CREATED_AT)));
        user.setDisabled_at(DateUtil.getDateFromTimestamp(resultSet.getTimestamp(User.DISABLED_AT)));
        user.setLast_login(DateUtil.getDateFromTimestamp(resultSet.getTimestamp(User.LAST_LOGIN)));
        return user;
    }
}
