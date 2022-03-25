package fastvagas.data.entity;

import java.util.Objects;

public class Portal {
    public static final String TABLE = "portals";
    public static final String PORTAL_ID = "portal_id";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String CITY_ID = "city_id";
    public static final String ACTIVE = "active";

    private Long portal_id;
    private String name;
    private String url;
    private Long city_id;
    private Character active;

    public Portal() {
        this.active = 'Y';
    }

    public Long getPortal_id() {
        return portal_id;
    }

    public void setPortal_id(Long portal_id) {
        this.portal_id = portal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public Character getActive() {
        return active;
    }

    public void setActive(Character active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portal portal = (Portal) o;
        return portal_id.equals(portal.portal_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portal_id);
    }

    @Override
    public String toString() {
        return "portal={"
            + PORTAL_ID + "=" + portal_id
            + "," + NAME + "='" + name + "'"
            + "," + URL + "='" + url + "'"
            + "," + CITY_ID + "=" + city_id
            + "," + ACTIVE + "='" + active + "'"
            + "}";
    }
}
