package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * DaytimeObject - nawiązanie połączenia z usługą Daytime.
 * @author Ian F. Darwin
 */
// BEGIN main
public class DaytimeObject {
    /** Port TCP, na którym działa usługa. */
    public static final short TIME_PORT = 1951;

    public static void main(String[] argv) {
        String hostName;
        if (argv.length == 0)
            hostName = "localhost";
        else
            hostName = argv[0];

        try {
            Socket sock = new Socket(hostName, TIME_PORT);
            ObjectInputStream is = new ObjectInputStream(new 
                BufferedInputStream(sock.getInputStream()));

            // Odczytujemy i sprawdzamy poprawność danej klasy Object.
            Object o = is.readObject();
            if (o == null) {
                System.err.println("Serwer przesłał wartość null!");
            } else if ((o instanceof Date)) {

                // Poprawna, rzutujemy do typu Date i wyświetlamy.
                Date d = (Date) o;
                System.out.println(
                      "Komputer, na którym działa serwer, to: " + hostName);
                System.out.println("Czas na serwerze: " + d.toString());

            } else {
                throw new IllegalArgumentException(
                                    "Oczekiwano typu Date, otrzymano " + o);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Oczekiwano daty, odebrano obiekt " + 
                                        " NIEWŁAŚCIWEJ KLASY (" + e + ")");

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
// END main
