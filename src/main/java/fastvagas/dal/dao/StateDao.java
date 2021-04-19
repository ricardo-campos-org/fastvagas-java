package fastvagas.dal.dao;

import fastvagas.dal.entity.City;
import fastvagas.dal.entity.State;
import fastvagas.dal.mapper.CityRowMapper;
import fastvagas.dal.mapper.StateRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StateDao extends Dao<State> {

    public StateDao(NamedParameterJdbcTemplate template) {
        super(State.class, template, new StateRowMapper());
    }

    public State findById(Long state_id) {
        final String query = "SELECT * FROM " + State.TABLE
            + " WHERE " + State.STATE_ID + "=:" + State.STATE_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(State.STATE_ID, state_id);

        return getObjectFromResult(query, params);
    }

    public State findBySiglaUf(String sigla_uf) {
        final String query = "SELECT * FROM " + State.TABLE
            + " WHERE " + State.SIGLA_UF + "=:" + State.SIGLA_UF;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(State.SIGLA_UF, sigla_uf);

        return getObjectFromResult(query, params);
    }

    public List<State> findAll() {
        return getListFromResult("SELECT * FROM " + State.TABLE);
    }

    public State create(State state) {
        final String query = "INSERT INTO " + State.TABLE + "("
            + State.NAME
            + "," + State.SIGLA_UF
            + ") values ("
            + ":" + State.NAME
            + ",:" + State.SIGLA_UF
            + ")";

        if (executeInsert(query, getParams(state)) == 1) {
            state.setState_id(getGeneratedId(State.STATE_ID));
            return state;
        }

        return null;
    }

    public State update(State state) {
        final String query = "UPDATE " + State.TABLE
            + " SET " + State.NAME + "=:" + State.NAME
            + "," + State.SIGLA_UF + "=:" + State.SIGLA_UF
            + " WHERE " + State.STATE_ID + "=:" + State.STATE_ID;

        if (executeUpdateDelete(query, getParams(state)) == 1) {
            return state;
        }

        return null;
    }

    public State deleteById(State state) {
        final String query = "DELETE FROM " + State.TABLE
            + " WHERE " + State.STATE_ID + "=:" + State.STATE_ID;

        if (executeUpdateDelete(query, getParams(state)) == 1) {
            return state;
        }

        return null;
    }

    private Map<String, Object> getParams(State state) {
        Map<String, Object> params = new HashMap<>();
        params.put(State.STATE_ID, state.getState_id());
        params.put(State.NAME, state.getName());
        params.put(State.SIGLA_UF, state.getSigla_uf());
        return params;
    }
}
