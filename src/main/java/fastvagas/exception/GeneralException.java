package fastvagas.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/** This class represent a general exception, an unexpected error. */
@Getter
public class GeneralException extends RuntimeException {
  private String returnMessage = "Erro inesperado";
  private String debugMessage = "Unexpected exception";
  private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

  protected GeneralException(String message) {
    super(message);
  }

  /**
   * Create an instance of GeneralException.
   *
   * @param returnMessage String message to be returned to the user
   * @param debugMessage String message to be showed in the logs
   */
  public GeneralException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  /**
   * Create an instance of GeneralException.
   *
   * @param returnMessage String message to be returned to the user
   * @param ex Throwable instance
   * @param debugMessage String message to be showed in the logs
   */
  public GeneralException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex);
    this.returnMessage = returnMessage;
    this.debugMessage = debugMessage;
  }

}
