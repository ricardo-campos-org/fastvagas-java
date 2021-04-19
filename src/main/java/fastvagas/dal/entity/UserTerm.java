package fastvagas.dal.entity;

import java.util.Objects;

public class UserTerm {
    public static final String TABLE = "user_terms";
    public static final String USER_ID = "user_id";
    public static final String TERMS = "terms";

    private Long user_id;
    private String terms;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTerm userTerm = (UserTerm) o;
        return user_id.equals(userTerm.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

    @Override
    public String toString() {
        return "user_term={"
            + USER_ID + "=" + user_id
            + "," + TERMS + "='" + terms + "'"
            + "}";
    }
}
