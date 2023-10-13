package fastvagas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** This class represents a user object when updating. */
@Getter
@Setter
@ToString
public class UserUpdateDto {

  @NotNull
  @Size(max = 15)
  private String firstName;

  @NotNull
  @Size(max = 30)
  private String lastName;

  @Email
  @NotNull
  @Size(max = 100)
  private String email;

  @NotNull
  @Size(max = 200)
  private String terms;
}
