package exceptions;

public class IncorrectAPIOutputException extends RuntimeException {
    public IncorrectAPIOutputException(String message) {
        super(message);
    }
}
