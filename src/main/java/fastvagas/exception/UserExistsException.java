package fastvagas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** Defines an exception when the users already exists. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExistsException extends ResponseStatusException {

  public UserExistsException(String email) {
    super(HttpStatus.BAD_REQUEST, String.format("User already exists for this email: %s", email));
  }
}
