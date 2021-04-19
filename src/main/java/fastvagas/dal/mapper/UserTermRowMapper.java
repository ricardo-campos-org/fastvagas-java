package fastvagas.dal.mapper;

import fastvagas.dal.entity.UserTerm;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTermRowMapper implements RowMapper<UserTerm> {

    @Override
    public UserTerm mapRow(ResultSet resultSet, int i) throws SQLException {
        UserTerm userTerm = new UserTerm();
        userTerm.setUser_id(resultSet.getLong(UserTerm.USER_ID));
        userTerm.setTerms(resultSet.getString(UserTerm.TERMS));
        return userTerm;
    }
}
