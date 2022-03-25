package fastvagas.data.mapper;

import fastvagas.data.entity.Portal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PortalRowMapper implements RowMapper<Portal> {

    @Override
    public Portal mapRow(ResultSet resultSet, int i) throws SQLException {
        Portal portal = new Portal();
        portal.setPortal_id(resultSet.getLong(Portal.PORTAL_ID));
        portal.setName(resultSet.getString(Portal.NAME));
        portal.setUrl(resultSet.getString(Portal.URL));
        portal.setCity_id(resultSet.getLong(Portal.CITY_ID));
        portal.setActive(resultSet.getString(Portal.ACTIVE).charAt(0));
        return portal;
    }
}
