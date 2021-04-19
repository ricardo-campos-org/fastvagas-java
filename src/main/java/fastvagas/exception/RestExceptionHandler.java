package fastvagas.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidEmailException.class) // OK
    protected ResponseEntity<Object> handleInvalidEmail(InvalidEmailException ex) {
        ApiError apiError = new ApiError(ex.getStatus());
        apiError.setMessage(ex.getReturnMessage());
        apiError.setDebugMessage(ex.getDebugMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidFieldException.class) // OK
    protected ResponseEntity<Object> handleInvalidField(InvalidFieldException ex) {
        ApiError apiError = new ApiError(ex.getStatus());
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getDebugMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class) // OK
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getStatus());
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SendMailException.class) // OK
    protected ResponseEntity<Object> handleSendMail(SendMailException ex) {
        ApiError apiError = new ApiError(ex.getStatus());
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getDebugMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DatabaseException.class) // ok
    protected ResponseEntity<Object> handleDatabase(DatabaseException ex) {
        ApiError apiError = new ApiError(ex.getStatus());
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getDebugMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InUseException.class) // OK
    protected ResponseEntity<Object> handleInUse(InUseException ex) {
        ApiError apiError = new ApiError(ex.getStatus());
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getDebugMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
