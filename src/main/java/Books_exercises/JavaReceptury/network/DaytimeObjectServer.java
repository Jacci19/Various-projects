package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * DaytimeObjectServer - serwer niestandardowej (obiektowej) wersji 
 * usług Daytime.
 * @author Ian F. Darwin
 */
// BEGIN main
public class DaytimeObjectServer {
    /** Port TCP używany przez serwer. */
    public static final short TIME_PORT = 1951;

    public static void main(String[] argv) {
        ServerSocket sock;
        Socket  clientSock;
        try {
            sock = new ServerSocket(TIME_PORT);
            while ((clientSock = sock.accept()) != null) {
                System.out.println("Odebrano połączenie z " +  
                    clientSock.getInetAddress());
                ObjectOutputStream os = new ObjectOutputStream(
                    clientSock.getOutputStream());

                // Tworzymy i zapisujemy obiekt.
                os.writeObject(new Date());

                os.close();
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
// END main
