package fastvagas.exception;

import lombok.Getter;
import lombok.Setter;

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
