package fastvagas.exception;

import lombok.Getter;
import lombok.Setter;

/** This class represents each field name and message for exceptions. */
@Getter
@Setter
public class ValidationFieldResponse {

  public ValidationFieldResponse(String field, String message) {
    this.fieldName = field;
    this.fieldMessage = message;
  }

  private String fieldName;
  private String fieldMessage;
}
