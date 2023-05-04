package fastvagas.resource;

import fastvagas.exception.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResource {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ValidationResponse> validationResponse(MethodArgumentNotValidException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ValidationResponse(ex.getFieldErrors()));
  }
}
