package fastvagas.exception;

import org.springframework.http.HttpStatus;

/** This class represents an error when there's an invalid input field. */
public class InvalidFieldException extends GeneralException {

  /**
   * Create an instance of InvalidFieldException.
   *
   * @param returnMessage String message to be returned to the user
   * @param debugMessage String message to be showed in the logs
   */
  public InvalidFieldException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  /**
   * Create an instance of InvalidFieldException.
   *
   * @param returnMessage String message to be returned to the user
   * @param ex Throwable instance
   * @param debugMessage String message to be showed in the logs
   */
  public InvalidFieldException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex, debugMessage);
  }

  /**
   * Get the HTTP status that should be returned.
   *
   * @return HttpStatus of the request.
   */
  @Override
  public HttpStatus getStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
