package JavaFX.MojaBiblioteczka.Utils.exceptions;

//obsługa wyjątku powstającego, gdy próbujemy dodać do BD już istniejącą tam kategorię

public class ApplicationException extends Exception {

    public ApplicationException(String message) {
        super(message);

    }
}
