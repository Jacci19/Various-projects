package Books_exercises.JavaReceptury.network;

// BEGIN main
import java.net.Socket;

/* Prosty klient sieciowy bez żadnego kodu obsługi błędów. */
public class ConnectSimple {

    public static void main(String[] argv) throws Exception {

        try (Socket sock = new Socket("localhost", 8080)) {

             /* Skoro dotarliśmy tutaj, to oznacza, że możemy odczytywać
             * i zapisywać dane przy użyciu "gniazda".
             */
            System.out.println(" *** Nawiązano połączenie. ***");

            /* Wykonujemy operacje wejścia-wyjścia... */

        }
    }
}
// END main
