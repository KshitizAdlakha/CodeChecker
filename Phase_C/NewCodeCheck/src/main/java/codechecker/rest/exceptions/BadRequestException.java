package codechecker.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Bad Request Exception
 * HATEOAS Exception when a bad request is received
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    // Constructor
    public BadRequestException() {
    }

    // Parameterized Constructor
    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
