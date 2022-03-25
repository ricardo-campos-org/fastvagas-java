package fastvagas.data.mapper;

import fastvagas.data.entity.State;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StateRowMapper implements RowMapper<State> {

    @Override
    public State mapRow(ResultSet resultSet, int i) throws SQLException {
        State state = new State();
        state.setState_id(resultSet.getLong(State.STATE_ID));
        state.setName(resultSet.getString(State.NAME));
        state.setSigla_uf(resultSet.getString(State.SIGLA_UF));
        return state;
    }
}
