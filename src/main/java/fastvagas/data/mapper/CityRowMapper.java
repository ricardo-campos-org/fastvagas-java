package fastvagas.data.mapper;

import fastvagas.data.entity.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {

    @Override
    public City mapRow(ResultSet resultSet, int i) throws SQLException {
        City city = new City();
        city.setCity_id(resultSet.getLong(City.CITY_ID));
        city.setName(resultSet.getString(City.NAME));
        city.setState_id(resultSet.getLong(City.STATE_ID));
        return city;
    }
}
