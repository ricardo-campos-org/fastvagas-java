package fastvagas.exception;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException {
  private String returnMessage = "Erro inesperado";
  private String debugMessage = "Unexpected exception";
  private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

  protected GeneralException(String message) {
    super(message);
  }

  public GeneralException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  public GeneralException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex);
    this.returnMessage = returnMessage;
    this.debugMessage = debugMessage;
  }

  public String getDebugMessage() {
    return debugMessage;
  }

  public String getReturnMessage() {
    return returnMessage;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
