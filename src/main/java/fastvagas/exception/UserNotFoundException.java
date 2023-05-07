package fastvagas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** Defines an exception when the user was not found in the database. */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ResponseStatusException {

  private final String message;

  public UserNotFoundException(Long id) {
    super(HttpStatus.NOT_FOUND, String.format("User not found for id: %d", id));
    this.message = String.format("User not found for id: %d", id);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
