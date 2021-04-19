package fastvagas.dal.dao;

import fastvagas.dal.entity.City;
import fastvagas.dal.mapper.CityRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CityDao extends Dao<City> {

    public CityDao(NamedParameterJdbcTemplate template) {
        super(City.class, template, new CityRowMapper());
    }

    public City findById(Long city_id) {
        final String query = "SELECT * "
            + "FROM " + City.TABLE + " "
            + "WHERE " + City.CITY_ID + "=:" + City.CITY_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(City.CITY_ID, city_id);

        return getObjectFromResult(query, params);
    }

    public List<City> findAll() {
        return getListFromResult("SELECT * FROM " + City.TABLE);
    }

    public List<City> findAllByStateId(Long state_id) {
        final String query = "SELECT * "
            + "FROM " + City.TABLE + " "
            + "WHERE " + City.STATE_ID + "=:" + City.STATE_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(City.STATE_ID, state_id);

        return getListFromResult(query, params);
    }

    public City create(City city) {
        String query = "INSERT INTO " + City.TABLE + "("
            + City.NAME
            + "," + City.STATE_ID
            + ") values ("
            + ":" + City.NAME
            + ",:" + City.STATE_ID
            + ")";

        if (executeInsert(query, getParams(city)) == 1) {
            city.setCity_id(getGeneratedId(City.CITY_ID));
            return city;
        }

        return null;
    }

    public City update(City city) {
        String query = "UPDATE " + City.TABLE + " "
            + "SET " + City.NAME + "=:" + City.NAME
            + "," + City.STATE_ID + "=:" + City.STATE_ID
            + "WHERE " + City.CITY_ID + "=:" + City.CITY_ID;

        if (executeUpdateDelete(query, getParams(city)) == 1) {
            return city;
        }

        return null;
    }

    public City deleteById(City city) {
        String query = "DELETE FROM " + City.TABLE
            + " WHERE " + City.CITY_ID + "=:" + City.CITY_ID;

        if (executeUpdateDelete(query, getParams(city)) == 1) {
            return city;
        }

        return null;
    }

    private Map<String, Object> getParams(City city) {
        Map<String, Object> params = new HashMap<>();
        params.put(City.CITY_ID, city.getCity_id());
        params.put(City.NAME, city.getName());
        params.put(City.STATE_ID, city.getState_id());
        return params;
    }
}
