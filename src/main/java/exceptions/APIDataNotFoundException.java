package exceptions;

public class APIDataNotFoundException extends RuntimeException {
    public APIDataNotFoundException(String message) {
        super(message);
    }
}
