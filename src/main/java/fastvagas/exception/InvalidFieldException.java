package fastvagas.exception;

import org.springframework.http.HttpStatus;

public class InvalidFieldException extends GeneralException {

  public InvalidFieldException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  public InvalidFieldException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex, debugMessage);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
