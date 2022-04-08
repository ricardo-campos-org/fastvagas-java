package fastvagas.data.entity;

import fastvagas.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    public final static String TABLE = "users";
    public final static String USER_ID = "user_id";
    public final static String FIRST_NAME = "first_name";
    public final static String LAST_NAME = "last_name";
    public final static String EMAIL = "email";
    public final static String PASSWORD = "password";
    public final static String CITY_ID = "city_id";
    public final static String ENABLED = "enabled";
    public final static String CREATED_AT = "created_at";
    public final static String DISABLED_AT = "disabled_at";
    public final static String LAST_LOGIN = "last_login";

    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Long city_id;
    private Integer enabled;
    private Date created_at;
    private Date disabled_at;
    private Date last_login;

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
