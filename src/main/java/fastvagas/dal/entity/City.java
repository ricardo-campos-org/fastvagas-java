package fastvagas.dal.entity;

import java.util.Objects;

public class City {
    public static final String TABLE = "cities";
    public static final String CITY_ID = "city_id";
    public static final String NAME = "name";
    public static final String STATE_ID = "state_id";

    private Long city_id;
    private String name;
    private Long state_id;

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return city_id.equals(city.city_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_id);
    }

    @Override
    public String toString() {
        return "city={"
            + CITY_ID + "=" + city_id
            + "," + NAME + "='" + name + "'"
            + "," + STATE_ID + "=" + state_id
            + "}";
    }
}
