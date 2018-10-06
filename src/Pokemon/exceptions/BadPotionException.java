package Pokemon.exceptions;

public class BadPotionException extends Exception{
    public BadPotionException() {
        super();
    }

    public BadPotionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadPotionException(Throwable cause) {
        super(cause);
    }

    protected BadPotionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BadPotionException(String message) {
        super(message);
    }
}
