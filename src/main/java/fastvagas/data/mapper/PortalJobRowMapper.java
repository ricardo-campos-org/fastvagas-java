package fastvagas.data.mapper;

import fastvagas.data.entity.PortalJob;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        portalJob.setPortal_id(resultSet.getLong(PortalJob.PORTAL_ID));
        portalJob.setCity_id(resultSet.getLong(PortalJob.CITY_ID));
        portalJob.setCreated_at(DateUtil.getLocalDateTimeFromTimestamp(resultSet.getTimestamp(PortalJob.CREATED_AT)));
        return portalJob;
    }
}
