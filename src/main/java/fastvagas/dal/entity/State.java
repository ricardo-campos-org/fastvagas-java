package fastvagas.dal.entity;

import java.util.Objects;

public class State {
    public static final String TABLE = "states";
    public static final String STATE_ID = "state_id";
    public static final String NAME = "name";
    public static final String SIGLA_UF = "sigla_uf";

    private Long state_id;
    private String name;
    private String sigla_uf;

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigla_uf() {
        return sigla_uf;
    }

    public void setSigla_uf(String sigla_uf) {
        this.sigla_uf = sigla_uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return state_id.equals(state.state_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state_id);
    }

    @Override
    public String toString() {
        return "state={"
            + STATE_ID + "=" + state_id
            + "," + NAME + "='" + name + "'"
            + "," + SIGLA_UF + "='" + sigla_uf + "'"
            + "}";
    }
}
