package exeptions;

public class OperationAttemptException extends RuntimeException {

    public OperationAttemptException() {
        super();
    }

    public OperationAttemptException(String message) {
        super(message);
    }

}