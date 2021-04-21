package fastvagas.dal.dao;

import fastvagas.exception.DatabaseException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")
class Dao<T> {
    private final NamedParameterJdbcTemplate template;
    private final Class<T> entityClass;
    private final RowMapper<T> rowMapper;
    protected KeyHolder keyHolder;
    protected final String LIMIT_PARAM = "LIMIT";
    protected final String OFFSET_PARAM = "OFFSET";
    protected final Integer PAGE_SIZE = 10;

    Dao(Class<T> entityClass, NamedParameterJdbcTemplate template, RowMapper<T> rowMapper) {
        this.entityClass = entityClass;
        this.template = template;
        this.rowMapper = rowMapper;
    }

    T getObjectFromResult(String query, SqlParameterSource params) {
        try {
            Logger.getLogger(entityClass.getName()).info(query);

            List<?> list = template.query(query, params, rowMapper);
            if (list.isEmpty()) {
                Logger.getLogger(entityClass.getName()).info("0 results.");
                return null;
            }

            Logger.getLogger(entityClass.getName()).info("1 row.");

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
            Logger.getLogger(entityClass.getName()).info(query);

            List<T> list = template.query(query, rowMapper);
            if (list.isEmpty()) {
                Logger.getLogger(entityClass.getName()).info("0 results.");
                return new ArrayList<>();
            }

            Logger.getLogger(entityClass.getName()).info(list.size() + " row(s).");

            return list;
        } catch (DataAccessException ex) {
            throw new DatabaseException(
                "Operação não realizada. Contate o administrador do sistema.",
                ex,
                entityClass.getName() + ": getListFromResult DataAccessException: " + ex.getLocalizedMessage()
            );
        }
    }

    List<T> getListFromResultPage(String query, SqlParameterSource params, Integer page) {
        try {
            Logger.getLogger(entityClass.getName()).info("SQL: " + query);

            List<T> list = template.query(query, params, rowMapper);
            if (list.isEmpty()) {
                Logger.getLogger(entityClass.getName()).info("0 results.");
                return new ArrayList<>();
            }



            Logger.getLogger(entityClass.getName()).info(list.size() + " row(s).");

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
            Logger.getLogger(entityClass.getName()).info("SQL: " + query);

            List<T> list = template.query(query, params, rowMapper);
            if (list.isEmpty()) {
                Logger.getLogger(entityClass.getName()).info("0 results.");
                return new ArrayList<>();
            }

            Logger.getLogger(entityClass.getName()).info(list.size() + " row(s).");

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
            Logger.getLogger(entityClass.getName()).info("SQL: " + query);

            this.keyHolder = new GeneratedKeyHolder();

            SqlParameterSource params = new MapSqlParameterSource(map);

            int rows = template.update(query, params, this.keyHolder);
            Logger.getLogger(entityClass.getName()).info(rows + " affected row(s).");

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
            Logger.getLogger(entityClass.getName()).info("SQL: " + query);

            SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());

            int[] rows = template.batchUpdate(query, batch);

            Logger.getLogger(entityClass.getName()).info(rows.length + " affected row(s).");

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
            Logger.getLogger(entityClass.getName()).info("SQL: " + query);

            int rows = template.update(query, map);
            Logger.getLogger(entityClass.getName()).info(rows + " affected row(s).");

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
        Long id = 0L;

        if (!keyHolder.getKeyList().isEmpty()) {
            Object obj = keyHolder.getKeys().get(keyName);
            return Long.parseLong(obj.toString());
        }

        return id;
    }
}
