package codechecker.core.services.exceptions;

/**
 *  Assignment Exists Exception
 *  Thrown when trying to create an assignment that already exists
 */
public class AssignmentExistsException extends RuntimeException {
    // constructor
    public AssignmentExistsException() {
    }

    // parameterized constructor
    public AssignmentExistsException(String message) {
        super(message);
    }

    // parameterized constructor
    public AssignmentExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    // parameterized constructor
    public AssignmentExistsException(Throwable cause) {
        super(cause);
    }
}
