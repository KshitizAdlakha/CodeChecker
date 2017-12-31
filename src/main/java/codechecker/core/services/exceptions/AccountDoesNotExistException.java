package codechecker.core.services.exceptions;

/**
 *  Account Does Not Exist Exception
 *  Thrown while logging in
 */
public class AccountDoesNotExistException extends RuntimeException {
    // parameterized constructor
    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }

    // parameterized constructor
    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    // parameterized constructor
    public AccountDoesNotExistException(String message) {
        super(message);
    }

    // constructor
    public AccountDoesNotExistException() {
    }
}
