package codechecker.core.services.exceptions;

public class AssignmentDoesNotExistException extends RuntimeException {
    public AssignmentDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssignmentDoesNotExistException(String message) {
        super(message);
    }

    public AssignmentDoesNotExistException() {
    }
}
