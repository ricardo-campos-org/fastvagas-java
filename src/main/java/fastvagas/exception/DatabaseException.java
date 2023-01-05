package fastvagas.exception;

/** This class represents a database error. */
public class DatabaseException extends GeneralException {

  /**
   * Create an instance of DatabaseException.
   *
   * @param returnMessage String message to be returned to the user
   * @param debugMessage String message to be showed in the logs
   */
  public DatabaseException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  /**
   * Create an instance of DatabaseException.
   *
   * @param returnMessage String message to be returned to the user
   * @param ex Throwable instance
   * @param debugMessage String message to be showed in the logs
   */
  public DatabaseException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex, debugMessage);
  }
}
