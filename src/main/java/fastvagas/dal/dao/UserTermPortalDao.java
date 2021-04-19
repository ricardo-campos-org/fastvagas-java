package fastvagas.dal.dao;

import fastvagas.dal.entity.Portal;
import fastvagas.dal.entity.User;
import fastvagas.dal.entity.UserTerm;
import fastvagas.dal.mapper.UserTermPortalMapper;
import fastvagas.dal.vo.UserTermPortal;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserTermPortalDao extends Dao<UserTermPortal> {

    public UserTermPortalDao(NamedParameterJdbcTemplate template) {
        super(UserTermPortal.class, template, new UserTermPortalMapper());
    }

    public List<UserTermPortal> findAllByPortalId(Long portal_id) {
        final String query = "SELECT " + UserTerm.TABLE + "." + UserTerm.USER_ID
            + ", " + User.TABLE + "." + User.FIRST_NAME
            + ", " + User.TABLE + "." + User.EMAIL
            + ", " + UserTerm.TABLE + "." + UserTerm.TERMS
            + ", " + Portal.TABLE + "." + Portal.PORTAL_ID
            + " FROM " + UserTerm.TABLE
            + " JOIN " + User.TABLE + " ON ("
            + User.TABLE + "." + User.USER_ID + "=" + UserTerm.TABLE + "." + UserTerm.USER_ID
            + ")"
            + " JOIN " + Portal.TABLE + " ON ("
            + Portal.TABLE + "." + Portal.CITY_ID + "=" + User.TABLE + "." + User.CITY_ID
            + ")"
            + " WHERE " + Portal.TABLE + "." + Portal.PORTAL_ID + "=:" + Portal.PORTAL_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Portal.PORTAL_ID, portal_id);

        // TODO: analisar a possibilidade de receber um list e fazer where in ()

        return getListFromResult(query, params);
    }
}
