package fastvagas.exception;

/** This class represents an exception when sending an email. */
public class SendMailException extends GeneralException {

  /**
   * Create an instance of SendMailException.
   *
   * @param returnMessage String message to be returned to the user
   * @param debugMessage String message to be showed in the logs
   */
  public SendMailException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  /**
   * Create an instance of SendMailException.
   *
   * @param returnMessage String message to be returned to the user
   * @param ex Throwable instance
   * @param debugMessage String message to be showed in the logs
   */
  public SendMailException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex, debugMessage);
  }
}
