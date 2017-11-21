package codechecker.core.services.exceptions;

/**
 *  Assignment Not Found Exception
 */
public class AssignmentNotFoundException extends RuntimeException {
    // parameterized constructor
    public AssignmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // parameterized constructor
    public AssignmentNotFoundException(String message) {
        super(message);
    }

    // constructor
    public AssignmentNotFoundException() {
    }
}
