package fastvagas.dal.dao;

import fastvagas.dal.entity.Portal;
import fastvagas.dal.entity.PortalJob;
import fastvagas.dal.mapper.PortalJobRowMapper;
import fastvagas.util.DateUtil;
import fastvagas.util.PaginationUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PortalJobDao extends Dao<PortalJob> {

    public PortalJobDao(NamedParameterJdbcTemplate template) {
        super(PortalJob.class, template, new PortalJobRowMapper());
    }

    public PortalJob findById(Long portal_job_id) {
        final String query = "SELECT * FROM " + PortalJob.TABLE
            + " WHERE " + PortalJob.PORTAL_JOB_ID + "=:" + PortalJob.PORTAL_JOB_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(PortalJob.PORTAL_JOB_ID, portal_job_id);

        return getObjectFromResult(query, params);
    }

    public List<PortalJob> findAll() {
        return getListFromResult("SELECT * FROM " + PortalJob.TABLE);
    }

    public List<PortalJob> findAllByPortalIdPublishedRange(Long portal_id, Date published_at_start) {
        final String query = "SELECT * "
                + " FROM " + PortalJob.TABLE
                + " WHERE " + PortalJob.PORTAL_ID + "=:" + PortalJob.PORTAL_ID
                + " AND " + PortalJob.PUBLISHED_AT + " > :" + PortalJob.PUBLISHED_AT
                + " ORDER BY " + PortalJob.PUBLISHED_AT + " DESC"
                + " , " + PortalJob.NAME + " ASC";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue(PortalJob.PORTAL_ID, portal_id)
                .addValue(PortalJob.PUBLISHED_AT, published_at_start);

        return getListFromResult(query, params);
    }

    public List<PortalJob> findAllByPortalIdPublishedRangePage(Long portal_id, Date published_at_start,
                                                               Integer page) {
        final String query = "SELECT * "
                + " FROM " + PortalJob.TABLE
                + " WHERE " + PortalJob.PORTAL_ID + "= :" + PortalJob.PORTAL_ID
                + " AND " + PortalJob.PUBLISHED_AT + " > :" + PortalJob.PUBLISHED_AT
                + " ORDER BY " + PortalJob.PUBLISHED_AT + " DESC"
                + " , " + PortalJob.NAME + " ASC"
                + " LIMIT :" + LIMIT_PARAM + " OFFSET :" + OFFSET_PARAM;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue(PortalJob.PORTAL_ID, portal_id)
                .addValue(PortalJob.PUBLISHED_AT, published_at_start)
                .addValue(LIMIT_PARAM, PAGE_SIZE)
                .addValue(OFFSET_PARAM, PaginationUtil.getOffset(PAGE_SIZE, page));

        return getListFromResultPage(query, params, page);
    }

    public PortalJob create(PortalJob portalJob) {
        final String query = "INSERT INTO " + PortalJob.TABLE + " ("
            + PortalJob.NAME
            + "," + PortalJob.COMPANY_NAME
            + "," + PortalJob.JOB_TYPE
            + "," + PortalJob.DESCRIPTION
            + "," + PortalJob.PUBLISHED_AT
            + "," + PortalJob.URL
            + "," + PortalJob.SEEN
            + "," + PortalJob.PORTAL_ID
            + ") values ("
            + ":" + PortalJob.NAME
            + ",:" + PortalJob.COMPANY_NAME
            + ",:" + PortalJob.JOB_TYPE
            + ",:" + PortalJob.DESCRIPTION
            + ",:" + PortalJob.PUBLISHED_AT
            + ",:" + PortalJob.URL
            + ",:" + PortalJob.SEEN
            + ",:" + PortalJob.PORTAL_ID
            + ")";

        if (executeInsert(query, getParams(portalJob)) == 1) {
            portalJob.setPortal_job_id(getGeneratedId(PortalJob.PORTAL_JOB_ID));
            return portalJob;
        }

        return null;
    }

    public List<PortalJob> createBatch(List<PortalJob> list) {
        final String query = "INSERT INTO " + PortalJob.TABLE + " ("
                + PortalJob.NAME
                + "," + PortalJob.COMPANY_NAME
                + "," + PortalJob.JOB_TYPE
                + "," + PortalJob.DESCRIPTION
                + "," + PortalJob.PUBLISHED_AT
                + "," + PortalJob.URL
                + "," + PortalJob.SEEN
                + "," + PortalJob.PORTAL_ID
                + ") values ("
                + ":" + PortalJob.NAME
                + ",:" + PortalJob.COMPANY_NAME
                + ",:" + PortalJob.JOB_TYPE
                + ",:" + PortalJob.DESCRIPTION
                + ",:" + PortalJob.PUBLISHED_AT
                + ",:" + PortalJob.URL
                + ",:" + PortalJob.SEEN
                + ",:" + PortalJob.PORTAL_ID
                + ")";

        if (executeInsertBatch(query, list).length > 0) {
            return list;
        }

        return null;
    }

    public PortalJob update(PortalJob portalJob) {
        final String query = "UPDATE " + PortalJob.TABLE
            + " SET " + PortalJob.NAME + "=:" + PortalJob.NAME
            + "," + PortalJob.COMPANY_NAME + "=:" + PortalJob.COMPANY_NAME
            + "," + PortalJob.JOB_TYPE + "=:" + PortalJob.JOB_TYPE
            + "," + PortalJob.DESCRIPTION + "=:" + PortalJob.DESCRIPTION
            + "," + PortalJob.PUBLISHED_AT + "=:" + PortalJob.PUBLISHED_AT
            + "," + PortalJob.URL + "=:" + PortalJob.URL
            + "," + PortalJob.SEEN + "=:" + PortalJob.SEEN
            + "," + PortalJob.PORTAL_ID + "=:" + PortalJob.PORTAL_ID
            + " WHERE " + PortalJob.PORTAL_JOB_ID + "=:" + PortalJob.PORTAL_JOB_ID;

        if (executeUpdateDelete(query, getParams(portalJob)) == 1) {
            return portalJob;
        }

        return null;
    }

    public PortalJob deleteById(PortalJob portalJob) {
        final String query = "DELETE FROM " + PortalJob.TABLE
            + " WHERE " + PortalJob.PORTAL_JOB_ID + "=:" + PortalJob.PORTAL_JOB_ID;

        if (executeUpdateDelete(query, getParams(portalJob)) == 1) {
            return portalJob;
        }

        return null;
    }

    private Map<String, Object> getParams(PortalJob portalJob) {
        Map<String, Object> params = new HashMap<>();
        params.put(PortalJob.PORTAL_JOB_ID, portalJob.getPortal_job_id());
        params.put(PortalJob.NAME, portalJob.getName());
        params.put(PortalJob.COMPANY_NAME, portalJob.getCompany_name());
        params.put(PortalJob.JOB_TYPE, portalJob.getJob_type());
        params.put(PortalJob.DESCRIPTION, portalJob.getDescription());
        params.put(PortalJob.PUBLISHED_AT, DateUtil.getGmtTimestamp(portalJob.getPublished_at()));
        params.put(PortalJob.URL, portalJob.getUrl());
        params.put(PortalJob.SEEN, DateUtil.getGmtTimestamp(portalJob.getSeen()));
        params.put(PortalJob.PORTAL_ID, portalJob.getPortal_id());
        return params;
    }
}
