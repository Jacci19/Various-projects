package Books_exercises.JavaReceptury.network;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

/* Klient wyposażony w obsługę błędów. */
// BEGIN main
public class ConnectFriendly {
    public static void main(String[] argv) {
        String server_name = argv.length == 1 ? argv[0] : "localhost";
        int tcp_port = 80;
        try (Socket sock = new Socket(server_name, tcp_port)) {

            /* Teraz można odczytywać i zapisywać dane za pośrednictwem 
            gniazda. */
            System.out.println(" *** Nawiązano połączenie z " + server_name  
                    + " ***");

            /* Jakieś operacje wejścia-wyjścia... */

        } catch (UnknownHostException e) {
            System.err.println("Komputer " + server_name + " nie jest znany");
            return;
        } catch (NoRouteToHostException e) {
            System.err.println("Komputer " + server_name + " jest niedostępny" );
            return;
        } catch (ConnectException e) {
            System.err.println("Komputer " + server_name + " odrzucił połączenie");
            return;
        } catch (java.io.IOException e) {
            System.err.println(server_name + ' ' + e.getMessage());
            return;
        }
    }
}
// END main
