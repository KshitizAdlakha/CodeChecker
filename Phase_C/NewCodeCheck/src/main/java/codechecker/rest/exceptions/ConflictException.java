package codechecker.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Conflict Exception
 * HATEOAS Exception when conflict is encountered
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    // Constructor
    public ConflictException() {
    }

    // Parameterized Constructor
    public ConflictException(Throwable cause) {
        super(cause);
    }
}
