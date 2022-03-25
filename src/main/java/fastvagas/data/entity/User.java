package fastvagas.data.entity;

import fastvagas.util.DateUtil;

import java.util.Date;
import java.util.Objects;

public class User {
    public final static String TABLE = "users";
    public final static String USER_ID = "user_id";
    public final static String FIRST_NAME = "first_name";
    public final static String LAST_NAME = "last_name";
    public final static String EMAIL = "email";
    public final static String PASSWORD = "password";
    public final static String CITY_ID = "city_id";
    public final static String CREATED_AT = "created_at";
    public final static String DISABLED_AT = "disabled_at";
    public final static String LAST_LOGIN = "last_login";

    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Long city_id;
    private Date created_at;
    private Date disabled_at;
    private Date last_login;

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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDisabled_at() {
        return disabled_at;
    }

    public void setDisabled_at(Date disabled_at) {
        this.disabled_at = disabled_at;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id.equals(user.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

    @Override
    public String toString() {
        return "user={"
            + USER_ID + "=" + user_id
            + "," + FIRST_NAME + "='" + first_name + "'"
            + "," + LAST_NAME + "='" + last_name + "'"
            + "," + EMAIL + "='" + email + "'"
            + "," + PASSWORD + "='" + password + "'"
            + "," + CITY_ID + "=" + city_id
            + "," + CREATED_AT + "='" + DateUtil.formatDate(created_at, true) + "'"
            + "," + DISABLED_AT + "=" + disabled_at
            + "," + LAST_LOGIN + "='" + DateUtil.formatDate(last_login, true) + "'"
            + "}";
    }
}
