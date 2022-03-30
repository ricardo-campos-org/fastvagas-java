package fastvagas.data.dao;

import fastvagas.exception.DatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
class Dao<T> {
    private final NamedParameterJdbcTemplate template;
    private final Class<T> entityClass;
    private final RowMapper<T> rowMapper;
    protected KeyHolder keyHolder;
    protected final String LIMIT_PARAM = "LIMIT";
    protected final String OFFSET_PARAM = "OFFSET";

    Dao(Class<T> entityClass, NamedParameterJdbcTemplate template, RowMapper<T> rowMapper) {
        this.entityClass = entityClass;
        this.template = template;
        this.rowMapper = rowMapper;
    }

    T getObjectFromResult(String query, SqlParameterSource params) {
        try {
            log.info(query);

            List<?> list = template.query(query, params, rowMapper);
            if (list.isEmpty()) {
                log.info("0 results.");
                return null;
            }

            log.info("1 row.");

            return (T) list.get(0);
        } catch (DataAccessException ex) {
            throw new DatabaseException(
                "Operação não realizada. Contate o administrador do sistema.",
                ex,
                entityClass.getName() + ": getObjectFromResult DataAccessException: " + ex.getLocalizedMessage()
            );
        }
    }

    List<T> getListFromResult(String query) {
        try {
            log.info(query);

            List<T> list = template.query(query, rowMapper);
            if (list.isEmpty()) {
                log.info("0 results.");
                return new ArrayList<>();
            }

            log.info(list.size() + " row(s).");

            return list;
        } catch (DataAccessException ex) {
            throw new DatabaseException(
                "Operação não realizada. Contate o administrador do sistema.",
                ex,
                entityClass.getName() + ": getListFromResult DataAccessException: " + ex.getLocalizedMessage()
            );
        }
    }

    List<T> getListFromResult(String query, SqlParameterSource params) {
        try {
            log.info("SQL: " + query);

            List<T> list = template.query(query, params, rowMapper);
            if (list.isEmpty()) {
                log.info("0 results.");
                return new ArrayList<>();
            }

            log.info(list.size() + " row(s).");

            return list;
        } catch (DataAccessException ex) {
            throw new DatabaseException(
                "Operação não realizada. Contate o administrador do sistema.",
                ex,
                entityClass.getName() + ": getListFromResult DataAccessException: " + ex.getLocalizedMessage()
            );
        }
    }

    int executeInsert(String query, Map<String, ?> map) {
        try {
            log.info("SQL: " + query);

            this.keyHolder = new GeneratedKeyHolder();

            SqlParameterSource params = new MapSqlParameterSource(map);

            int rows = template.update(query, params, this.keyHolder);
            log.info(rows + " affected row(s).");

            return rows;
        } catch (DataAccessException ex) {
            throw new DatabaseException(
                "Operação não realizada. Contate o administrador do sistema.",
                ex,
                entityClass.getName() + ": executeInsert DataAccessException: " + ex.getLocalizedMessage()
            );
        }
    }

    int[] executeInsertBatch(String query, List<T> list) {
        try {
            log.info("SQL: " + query);

            SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());

            int[] rows = template.batchUpdate(query, batch);

            log.info(rows.length + " affected row(s).");

            return rows;
        } catch (DataAccessException ex) {
            throw new DatabaseException(
                    "Operação não realizada. Contate o administrador do sistema.",
                    ex,
                    entityClass.getName() + ": executeInsert DataAccessException: " + ex.getLocalizedMessage()
            );
        }
    }

    int executeUpdateDelete(String query, Map<String, ?> map) {
        try {
            log.info("SQL: " + query);

            int rows = template.update(query, map);
            log.info(rows + " affected row(s).");

            return rows;
        } catch (DataAccessException ex) {
            throw new DatabaseException(
                "Operação não realizada. Contate o administrador do sistema.",
                ex,
                entityClass.getName() + ": executeUpdateDelete DataAccessException: " + ex.getLocalizedMessage()
            );
        }
    }

    protected long getGeneratedId(String keyName) {
        long id = 0L;

        if (!keyHolder.getKeyList().isEmpty()) {
            Object obj = keyHolder.getKeys().get(keyName);
            return Long.parseLong(obj.toString());
        }

        return id;
    }
}
