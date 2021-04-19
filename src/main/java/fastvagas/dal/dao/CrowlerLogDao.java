package fastvagas.dal.dao;

import fastvagas.dal.entity.CrowlerLog;
import fastvagas.dal.entity.PortalJob;
import fastvagas.dal.entity.User;
import fastvagas.dal.mapper.CrowlerLogRowMapper;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CrowlerLogDao extends Dao<CrowlerLog> {

    public CrowlerLogDao(NamedParameterJdbcTemplate template) {
        super(CrowlerLog.class, template, new CrowlerLogRowMapper());
    }

    public Integer getLastSequenceByDate(Date pDate) {
        List<CrowlerLog> list = findAllByPrimaryKey(pDate, null);

        if (list.isEmpty()) {
            return 0;
        }

        CrowlerLog last = list.get(list.size()-1);
        return last.getSequence();
    }

    public List<CrowlerLog> findAllByPrimaryKey(Date created_at, Integer sequence) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(CrowlerLog.TABLE).append(" WHERE 1 = 1");

        if (created_at != null) {
            sb.append(" AND ").append(CrowlerLog.CREATED_AT).append("=:").append(CrowlerLog.CREATED_AT);
        }
        if (sequence != null) {
            sb.append(" AND ").append(CrowlerLog.SEQUENCE).append("=:").append(CrowlerLog.SEQUENCE);
        }

        sb.append(" ORDER BY ").append(CrowlerLog.CREATED_AT).append(", ").append(CrowlerLog.SEQUENCE);

        Map<String, Object> params = new HashMap<>();
        if (created_at != null) {
            params.put(CrowlerLog.CREATED_AT, DateUtil.getGmtTimestamp(created_at));
        }
        if (sequence != null) {
            params.put(CrowlerLog.SEQUENCE, sequence);
        }

        return getListFromResult(sb.toString(), new MapSqlParameterSource().addValues(params));
    }

    public List<CrowlerLog> createBatch(List<CrowlerLog> list) {
        final String query = "INSERT INTO " + CrowlerLog.TABLE + " ("
                + CrowlerLog.CREATED_AT
                + "," + CrowlerLog.SEQUENCE
                + "," + CrowlerLog.PORTAL_ID
                + "," + CrowlerLog.TEXT
                + ") values ("
                + ":" + CrowlerLog.CREATED_AT
                + ",:" + CrowlerLog.SEQUENCE
                + ",:" + CrowlerLog.PORTAL_ID
                + ",:" + CrowlerLog.TEXT
                + ")";

        if (executeInsertBatch(query, list).length > 0) {
            return list;
        }

        return null;
    }
}
