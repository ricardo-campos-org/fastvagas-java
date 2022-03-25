package fastvagas.data.dao;

import fastvagas.data.entity.CrowlerLog;
import fastvagas.data.mapper.CrowlerLogRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CrowlerLogDao extends Dao<CrowlerLog> {

    public CrowlerLogDao(NamedParameterJdbcTemplate template) {
        super(CrowlerLog.class, template, new CrowlerLogRowMapper());
    }

    public Integer getLastSequenceByDate(LocalDate pDate) {
        List<CrowlerLog> list = findAllByPrimaryKey(pDate, null);

        if (list.isEmpty()) {
            return 0;
        }

        CrowlerLog last = list.get(list.size()-1);
        return last.getSequence();
    }

    public List<CrowlerLog> findAllByPrimaryKey(LocalDate created_at, Integer sequence) {
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
            params.put(CrowlerLog.CREATED_AT, created_at);
        }
        if (sequence != null) {
            params.put(CrowlerLog.SEQUENCE, sequence);
        }

        return getListFromResult(sb.toString(), new MapSqlParameterSource().addValues(params));
    }

    public List<CrowlerLog> findAllByGreaterDateTime(LocalDateTime dateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(CrowlerLog.TABLE).append(" WHERE 1 = 1");
        sb.append(" AND ").append(CrowlerLog.CREATED_AT).append(">=:").append(CrowlerLog.CREATED_AT);
        sb.append(" ORDER BY ").append(CrowlerLog.CREATED_AT).append(", ").append(CrowlerLog.SEQUENCE);

        Map<String, Object> params = new HashMap<>();
        params.put(CrowlerLog.CREATED_AT, dateTime);

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
