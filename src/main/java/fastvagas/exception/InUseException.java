package fastvagas.exception;

import org.springframework.http.HttpStatus;

/** This class represents an existing entity. */
public class InUseException extends GeneralException {

  public InUseException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  public InUseException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex, debugMessage);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
