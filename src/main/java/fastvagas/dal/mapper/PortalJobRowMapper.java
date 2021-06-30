package fastvagas.dal.mapper;

import fastvagas.dal.entity.PortalJob;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.unit.DataUnit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PortalJobRowMapper implements RowMapper<PortalJob> {

    @Override
    public PortalJob mapRow(ResultSet resultSet, int i) throws SQLException {
        PortalJob portalJob = new PortalJob();
        portalJob.setPortal_job_id(resultSet.getLong(PortalJob.PORTAL_JOB_ID));
        portalJob.setName(resultSet.getString(PortalJob.NAME));
        portalJob.setCompany_name(resultSet.getString(PortalJob.COMPANY_NAME));
        portalJob.setJob_type(resultSet.getString(PortalJob.JOB_TYPE));
        portalJob.setDescription(resultSet.getString(PortalJob.DESCRIPTION));
        if (resultSet.getString(PortalJob.PUBLISHED_AT) != null) {
            portalJob.setPublished_at(resultSet.getString(PortalJob.PUBLISHED_AT));
        }
        portalJob.setUrl(resultSet.getString(PortalJob.URL));
        if (resultSet.getTimestamp(PortalJob.SEEN) != null) {
            portalJob.setSeen(new Date(resultSet.getTimestamp(PortalJob.SEEN).getTime()));
        }
        portalJob.setPortal_id(resultSet.getLong(PortalJob.PORTAL_ID));
        portalJob.setCity_id(resultSet.getLong(PortalJob.CITY_ID));
        portalJob.setCreatedAt(DateUtil.getLocalDateFromDate(resultSet.getDate(PortalJob.CREATED_AT)));
        return portalJob;
    }
}
