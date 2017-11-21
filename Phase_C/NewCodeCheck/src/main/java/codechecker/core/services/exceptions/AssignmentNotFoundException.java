package codechecker.core.services.exceptions;

public class AssignmentNotFoundException extends RuntimeException {
    public AssignmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssignmentNotFoundException(String message) {
        super(message);
    }

    public AssignmentNotFoundException() {
    }
}
