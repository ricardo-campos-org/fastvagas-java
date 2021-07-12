package fastvagas.dal.dao;

import fastvagas.dal.entity.User;
import fastvagas.dal.mapper.UserRowMapper;
import fastvagas.util.DateUtil;
import fastvagas.util.ObjectUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao extends Dao<User> {

    public UserDao(NamedParameterJdbcTemplate template) {
        super(User.class, template, new UserRowMapper());
    }

    public User findById(Long user_id) {
        final String query = "SELECT * FROM " + User.TABLE
            + " WHERE " + User.USER_ID + "=:" + User.USER_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(User.USER_ID, user_id);

        return getObjectFromResult(query, params);
    }

    public User findByEmail(String email) {
        final String query = "SELECT * FROM " + User.TABLE
            + " WHERE " + User.EMAIL + "=:" + User.EMAIL;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(User.EMAIL, email);

        return getObjectFromResult(query, params);
    }

    public List<User> findAll(boolean includeDisabled) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(User.TABLE);

        if (!includeDisabled) {
            sb.append(" WHERE ").append(User.DISABLED_AT).append(" is null");
        }

        return getListFromResult(sb.toString());
    }

    public List<User> findAllByCityId(Long city_id) {
        final String query = "SELECT * FROM " + User.TABLE
            + " WHERE " + User.CITY_ID + "=:" + User.CITY_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(User.CITY_ID, city_id);

        return getListFromResult(query, params);
    }

    public User create(User user) {
        final String query = "INSERT INTO " + User.TABLE + "("
            + User.FIRST_NAME
            + "," + User.LAST_NAME
            + "," + User.EMAIL
            + "," + User.PASSWORD
            + "," + User.CITY_ID
            + "," + User.CREATED_AT
            + ") values ("
            + ":" + User.FIRST_NAME
            +",:" + User.LAST_NAME
            +",:" + User.EMAIL
            +",:" + User.PASSWORD
            +",:" + User.CITY_ID
            +",:" + User.CREATED_AT
            +")";

        if (executeInsert(query, getParams(user)) == 1) {
            user.setUser_id(getGeneratedId(User.USER_ID));
            return user;
        }

        return null;
    }

    public User update(User user) {
        final StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(User.TABLE).append(" ");
        sb.append("SET ").append(User.FIRST_NAME).append("=:").append(User.FIRST_NAME);
        sb.append(",").append(User.LAST_NAME).append("=:").append(User.LAST_NAME);
        sb.append(",").append(User.EMAIL).append("=:").append(User.EMAIL);
        if (ObjectUtil.hasValue(user.getPassword())) {
            sb.append(",").append(User.PASSWORD).append("=:").append(User.PASSWORD);
        }
        sb.append(",").append(User.CITY_ID).append("=:").append(User.CITY_ID);
        if (user.getDisabled_at() != null) {
            sb.append(",").append(User.DISABLED_AT).append("=:").append(User.DISABLED_AT);
        }
        if (user.getLast_login() != null) {
            sb.append(",").append(User.LAST_LOGIN).append("=:").append(User.LAST_LOGIN);
        }
        sb.append(" WHERE ").append(User.USER_ID).append("=:").append(User.USER_ID);

        if (executeUpdateDelete(sb.toString(), getParams(user)) == 1) {
            return user;
        }

        return null;
    }

    public User disable(User user) {
        user.setDisabled_at(new Date());

        final String query = "UPDATE " + User.TABLE
            + " SET " + User.DISABLED_AT + "=:" + User.DISABLED_AT
            + " WHERE " + User.USER_ID + "=:" + User.USER_ID;

        if (executeUpdateDelete(query, getParams(user)) == 1) {
            return user;
        }

        return null;
    }

    private Map<String, Object> getParams(User user) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(User.USER_ID, user.getUser_id());
        params.put(User.FIRST_NAME, user.getFirst_name());
        params.put(User.LAST_NAME, user.getLast_name());
        params.put(User.EMAIL, user.getEmail());
        params.put(User.PASSWORD, user.getPassword());
        params.put(User.CITY_ID, user.getCity_id());
        params.put(User.CREATED_AT, DateUtil.getGmtTimestamp(user.getCreated_at()));
        params.put(User.DISABLED_AT, DateUtil.getGmtTimestamp(user.getDisabled_at()));
        params.put(User.LAST_LOGIN, DateUtil.getGmtTimestamp(user.getLast_login()));
        return params;
    }
}
