package fastvagas.exception;

public class SendMailException extends GeneralException {

  public SendMailException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  public SendMailException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex, debugMessage);
  }
}
