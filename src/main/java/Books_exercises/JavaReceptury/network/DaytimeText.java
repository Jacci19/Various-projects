package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;

/**
 * DaytimeText - nawiązanie połączenia z usługą Daytime (ascii).
 * @author Ian F. Darwin
 */
// BEGIN main
public class DaytimeText {
    public static final short TIME_PORT = 13;

    public static void main(String[] argv) {
        String hostName;
        if (argv.length == 0)
            hostName = "localhost";
        else
            hostName = argv[0];

        try {
            Socket sock = new Socket(hostName, TIME_PORT);
            BufferedReader is = new BufferedReader(new 
                InputStreamReader(sock.getInputStream()));
            String remoteTime = is.readLine();
            System.out.println("Czas na komputerze " + hostName + 
                    " to " + remoteTime);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
// END main
