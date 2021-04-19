package fastvagas.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends GeneralException {

    public InvalidEmailException(String returnMessage, String debugMessage) {
        this(returnMessage, null, debugMessage);
    }

    public InvalidEmailException(String returnMessage, Throwable ex, String debugMessage) {
        super(returnMessage, ex, debugMessage);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
