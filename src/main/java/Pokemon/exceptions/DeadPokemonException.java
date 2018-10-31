package Pokemon.exceptions;

public class DeadPokemonException extends Exception {
    public DeadPokemonException() {
        super();
    }

    public DeadPokemonException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeadPokemonException(Throwable cause) {
        super(cause);
    }

    protected DeadPokemonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DeadPokemonException(String message) {
        super(message);
    }
}
