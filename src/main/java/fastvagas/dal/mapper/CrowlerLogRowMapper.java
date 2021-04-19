package fastvagas.dal.mapper;

import fastvagas.dal.entity.CrowlerLog;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrowlerLogRowMapper implements RowMapper<CrowlerLog> {

    @Override
    public CrowlerLog mapRow(ResultSet resultSet, int i) throws SQLException {
        CrowlerLog crowlerLog = new CrowlerLog();
        crowlerLog.setCreated_at(DateUtil.getDateFromTimestamp(resultSet.getTimestamp(CrowlerLog.CREATED_AT)));
        crowlerLog.setSequence(resultSet.getInt(CrowlerLog.SEQUENCE));
        crowlerLog.setPortal_id(resultSet.getLong(CrowlerLog.PORTAL_ID));
        crowlerLog.setText(resultSet.getString(CrowlerLog.TEXT).trim());
        return crowlerLog;
    }
}
