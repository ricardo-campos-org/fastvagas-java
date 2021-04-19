package fastvagas.dal.mapper;

import fastvagas.dal.entity.Portal;
import fastvagas.dal.entity.User;
import fastvagas.dal.entity.UserTerm;
import fastvagas.dal.vo.UserTermPortal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTermPortalMapper implements RowMapper<UserTermPortal> {

    @Override
    public UserTermPortal mapRow(ResultSet resultSet, int i) throws SQLException {
        UserTermPortal userTermPortal = new UserTermPortal();
        //userTermPortal.setUser_id(resultSet.getLong(UserTerm.TABLE + "." + UserTerm.USER_ID));
        userTermPortal.setUser_id(resultSet.getLong(UserTerm.USER_ID));
        //userTermPortal.setFirst_name(resultSet.getString(User.TABLE + "." + User.FIRST_NAME));
        userTermPortal.setFirst_name(resultSet.getString(User.FIRST_NAME));
        //userTermPortal.setEmail(resultSet.getString(User.TABLE + "." + User.EMAIL));
        userTermPortal.setEmail(resultSet.getString(User.EMAIL));
        //userTermPortal.setTerms(resultSet.getString(UserTerm.TABLE + "." + UserTerm.TERMS));
        userTermPortal.setTerms(resultSet.getString(UserTerm.TERMS));
        //userTermPortal.setPortal_id(resultSet.getLong(Portal.TABLE + "." + Portal.PORTAL_ID));
        userTermPortal.setPortal_id(resultSet.getLong(Portal.PORTAL_ID));
        return userTermPortal;
    }
}
