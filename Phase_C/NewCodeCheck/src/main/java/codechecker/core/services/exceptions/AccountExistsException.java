package codechecker.core.services.exceptions;

/**
 *  Account Exists Exception
 *  Checks if an account with the same username already exists
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
