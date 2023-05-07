package fastvagas.exception;

import java.util.List;
import lombok.Getter;
import org.springframework.validation.FieldError;

/** This class contains error message and invalid fields description, in case of errors. */
@Getter
public class ValidationResponse {

  private static final String TEMPLATE = "%d field(s) with validation problems!";

  private final String errorMessage;

  private final List<ValidationFieldResponse> fields;

  /**
   * Create a new ValidationResponse instance.
   *
   * @param errors A {@link List} of {@link FieldError}
   */
  public ValidationResponse(List<FieldError> errors) {
    this.fields =
        errors.stream()
            .map(e -> new ValidationFieldResponse(e.getField(), e.getDefaultMessage()))
            .toList();
    this.errorMessage = String.format(TEMPLATE, this.fields.size());
  }
}
