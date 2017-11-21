package codechecker.core.services.exceptions;

/**
 *  Account Exists Exception
 */
public class AccountExistsException extends RuntimeException {
    // parameterized constructor
    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    // parameterized constructor
    public AccountExistsException(String message) {
        super(message);
    }

    // constructor
    public AccountExistsException() {
        super();
    }
}
