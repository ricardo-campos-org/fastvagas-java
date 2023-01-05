package fastvagas.exception;

public class DatabaseException extends GeneralException {

  public DatabaseException(String returnMessage, String debugMessage) {
    this(returnMessage, null, debugMessage);
  }

  public DatabaseException(String returnMessage, Throwable ex, String debugMessage) {
    super(returnMessage, ex, debugMessage);
  }
}
