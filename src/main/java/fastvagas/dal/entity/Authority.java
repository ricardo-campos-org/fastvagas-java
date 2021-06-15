package fastvagas.dal.entity;

import java.util.Objects;

public class Authority {
    public final static String TABLE = "authorities";
    public final static String EMAIL = "email";
    public final static String AUTHORITY = "authority";

    private String email;
    private String authority;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return email.equals(authority1.email) && authority.equals(authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, authority);
    }

    @Override
    public String toString() {
        return "authority={" +
                EMAIL + "='" + email + "'" +
                ", " + AUTHORITY +"='" + authority + "'" +
                "}";
    }
}
