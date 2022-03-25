package fastvagas.data.vo;

import fastvagas.data.entity.Portal;
import fastvagas.data.entity.User;
import fastvagas.data.entity.UserTerm;

import java.util.Objects;

public class UserTermPortal {

    private Long user_id;
    private String first_name;
    private String email;
    private String terms;
    private Long portal_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Long getPortal_id() {
        return portal_id;
    }

    public void setPortal_id(Long portal_id) {
        this.portal_id = portal_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTermPortal that = (UserTermPortal) o;
        return user_id.equals(that.user_id) &&
                first_name.equals(that.first_name) &&
                email.equals(that.email) &&
                terms.equals(that.terms) &&
                portal_id.equals(that.portal_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, first_name, email, terms, portal_id);
    }

    @Override
    public String toString() {
        return "UserTermPortal={"
            + UserTerm.TABLE + "." + UserTerm.USER_ID + "=" + user_id
            + "," + User.TABLE + "." + User.FIRST_NAME + "='" + first_name + "'"
            + "," + User.TABLE + "." + User.EMAIL + "='" + email + "'"
            + "," + UserTerm.TABLE + "." + UserTerm.TERMS + "='" + terms + "'"
            + "," + Portal.TABLE + "." + Portal.PORTAL_ID + "=" + portal_id
            + "}";
    }
}
