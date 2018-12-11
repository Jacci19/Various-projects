package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;

/**
 * ListenInside - program tworzy gniazdo serwera odbierające 
 * połączenia wyłącznie w danym interfejsie sieciowym, który 
 * w tym przypadku nosi nazwę "wewnętrzny".
 * @author Ian F. Darwin
 */
// BEGIN main
public class ListenInside {
    /** Port TCP, na którym działa usługa. */
    public static final short PORT = 9999;
    /** Nazwa interfejsu sieciowego. */
    public static final String INSIDE_HOST = "wewnetrzny";
    /** Maksymalna liczba połączeń od klientów, 
     * które będą umieszczane w kolejce.  */
    public static final int BACKLOG = 10;

    public static void main(String[] argv) throws IOException {
        ServerSocket sock;
        Socket  clientSock;
        try {
            sock = new ServerSocket(PORT, BACKLOG, 
                InetAddress.getByName(INSIDE_HOST));
            while ((clientSock = sock.accept()) != null) {

                // Przetwarzamy żądanie.
                process(clientSock);
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /** Ta metoda w jakiś sposób obsłużyłaby połączenie z klientem. */
    static void process(Socket s) throws IOException {
        System.out.println("Akceptujemy połączenie  " + INSIDE_HOST + 
            ": " + s.getInetAddress(  ));
        // Wymiana danych między serwerem i klientem.
        s.close();
    }
}
// END main
