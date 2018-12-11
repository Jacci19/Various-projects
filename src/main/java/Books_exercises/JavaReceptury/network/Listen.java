package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;

/**
 * Listen -- program tworzy obiekt ServerSocket i czeka na połączenie.
 * @author Ian F. Darwin
 */
// BEGIN main
public class Listen {
    /** Port TCP używany przez usługę. */
    public static final short PORT = 9999;

    public static void main(String[] argv) throws IOException {
        ServerSocket sock;
        Socket  clientSock;
        try {
            sock = new ServerSocket(PORT);
            while ((clientSock = sock.accept()) != null) {

                // Przetwarzamy żądanie...
                process(clientSock);
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /** Ta metoda w jakiś sposób obsłużyłaby połączenie z klientem. */
    static void process(Socket s) throws IOException {
        System.out.println("Nawiązano połączenie z klientem " + 
                s.getInetAddress());
        // Wymiana danych między serwerem i klientem.
        s.close();
    }
}
// END main
