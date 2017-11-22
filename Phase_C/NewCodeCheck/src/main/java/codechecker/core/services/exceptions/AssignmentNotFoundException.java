package codechecker.core.services.exceptions;

/**
 *  Assignment Not Found Exception
 *  Thrown when trying to access an assignment that does not exist
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
