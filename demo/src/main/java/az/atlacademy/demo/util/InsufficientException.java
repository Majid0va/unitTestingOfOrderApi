package az.atlacademy.demo.util;

public class InsufficientException extends RuntimeException {
    public InsufficientException() {
    }

    public InsufficientException(String message) {
        super(message);
    }
}
