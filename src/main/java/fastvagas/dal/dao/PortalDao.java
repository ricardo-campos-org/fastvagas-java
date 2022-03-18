package fastvagas.dal.dao;

import fastvagas.dal.entity.Portal;
import fastvagas.dal.entity.User;
import fastvagas.dal.mapper.PortalRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PortalDao extends Dao<Portal> {

    public PortalDao(NamedParameterJdbcTemplate template) {
        super(Portal.class, template, new PortalRowMapper());
    }

    public Portal findById(Long portal_id) {
        final String query = "SELECT * FROM " + Portal.TABLE
            + " WHERE " + Portal.PORTAL_ID + "=:" + Portal.PORTAL_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Portal.PORTAL_ID, portal_id);

        return getObjectFromResult(query, params);
    }

    public List<Portal> findAll(Character active) {
        String query = "SELECT * FROM " + Portal.TABLE +
                " WHERE " + Portal.ACTIVE + "=:" + Portal.ACTIVE;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue(Portal.ACTIVE, active);

        return getListFromResult(query, params);
    }

    public List<Portal> findAllByUsersActive() {
        final String query = "SELECT " + Portal.TABLE + "." + Portal.PORTAL_ID
            + " ," + Portal.TABLE + "." + Portal.NAME
            + " ," + Portal.TABLE + "." + Portal.URL
            + " ," + Portal.TABLE + "." + Portal.CITY_ID
            + " ," + Portal.ACTIVE + "." + Portal.ACTIVE
            + " FROM " + Portal.TABLE
            + " JOIN " + User.TABLE + " ON ("
            + User.TABLE + "." + User.CITY_ID + " = " + Portal.TABLE + "." + Portal.CITY_ID
            + ")"
            + " WHERE " + User.TABLE + "." + User.DISABLED_AT + " IS NULL"
            + " GROUP BY " + Portal.TABLE + "." + Portal.PORTAL_ID
            + " ," + Portal.TABLE + "." + Portal.NAME
            + " ," + Portal.TABLE + "." + Portal.URL
            + " ," + Portal.TABLE + "." + Portal.CITY_ID
            + " ," + Portal.ACTIVE + "." + Portal.ACTIVE;
        return getListFromResult(query);
    }

    public List<Portal> findAllByCityId(Long city_id) {
        final String query = "SELECT * FROM " + Portal.TABLE
            + " WHERE " + Portal.CITY_ID + "=:" + Portal.CITY_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Portal.CITY_ID, city_id);

        return getListFromResult(query, params);
    }

    public Portal create(Portal portal) {
        final String query = "INSERT INTO " + Portal.TABLE + " ("
            + Portal.NAME
            + "," + Portal.URL
            + "," + Portal.CITY_ID
            + "," + Portal.ACTIVE
            + ") values ("
            + ":" + Portal.NAME
            + ",:" + Portal.URL
            + ",:" + Portal.CITY_ID
            + ",:" + Portal.ACTIVE
            + ")";

        if (executeInsert(query, getParams(portal)) == 1) {
            portal.setPortal_id(getGeneratedId(Portal.PORTAL_ID));
            return portal;
        }

        return null;
    }

    public Portal update(Portal portal) {
        final String query = "UPDATE " + Portal.TABLE
            + " SET " + Portal.NAME + "=:" + Portal.NAME
            + "," + Portal.URL + "=:" + Portal.URL
            + "," + Portal.CITY_ID + "=:" + Portal.CITY_ID
            + "," + Portal.ACTIVE + "=:" + Portal.ACTIVE
            + " WHERE " + Portal.PORTAL_ID + "=:" + Portal.PORTAL_ID;

        if (executeUpdateDelete(query, getParams(portal)) == 1) {
            return portal;
        }

        return null;
    }

    public Portal deleteById(Portal portal) {
        final String query = "DELETE FROM " + Portal.TABLE
            + " WHERE " + Portal.PORTAL_ID + "=:" + Portal.PORTAL_ID;

        if (executeUpdateDelete(query, getParams(portal)) == 1) {
            return portal;
        }

        return null;
    }

    private Map<String, Object> getParams(Portal portal) {
        Map<String, Object> params = new HashMap<>();
        params.put(Portal.PORTAL_ID, portal.getPortal_id());
        params.put(Portal.NAME, portal.getName());
        params.put(Portal.URL, portal.getUrl());
        params.put(Portal.CITY_ID, portal.getCity_id());
        params.put(Portal.ACTIVE, portal.getActive());
        return params;
    }
}
