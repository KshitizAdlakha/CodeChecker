package codechecker.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Not Found Exception
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    // Constructor
    public NotFoundException() {
    }

    // Parameterized Constructor
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
