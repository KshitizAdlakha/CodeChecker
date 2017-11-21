package codechecker.core.services.exceptions;

public class AssignmentExistsException extends RuntimeException {
    public AssignmentExistsException() {
    }

    public AssignmentExistsException(String message) {
        super(message);
    }

    public AssignmentExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssignmentExistsException(Throwable cause) {
        super(cause);
    }
}
