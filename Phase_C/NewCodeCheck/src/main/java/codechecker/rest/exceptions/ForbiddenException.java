package codechecker.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Forbidden Exception.
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
}
