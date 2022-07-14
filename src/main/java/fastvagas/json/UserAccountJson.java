package fastvagas.json;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserAccountJson {

    private Long personId;
    private String firstName;
    private String lastName;
    private String email;
    private Long cityId;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private String cityName;
    private String stateName;
    private String password;
    private String terms;

}
