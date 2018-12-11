package Books_exercises.JavaReceptury.network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DaytimeBinary - nawiązanie połączenia ze standardową usługą Daytime.
 * N.B. This is an 'rdate' client by another name.
 * @author Ian F. Darwin
 */
// BEGIN main
public class RDateClient {
    /** Port TCP używany przez usługę zwracającą czas w postaci binarnej. */
    public static final short TIME_PORT = 37;
    /** Liczba sekund pomiędzy 1970, punktem odniesienia przy wyznaczaniu
     * dat Date(long) oraz czasu Time w języku Java.
     * Uwzględnia lata przestępne (aż do 2100).
     * Odejmuje 1 dzień z roku 1900, dodaje pół dnia dla lat 1969 - 1970.
     */
    protected static final long BASE_DAYS = 
        (long)((1970-1900)*365 + (1970-1900-1)/4);

    /* Liczba sekund od początku roku 1970. */
    public static final long BASE_DIFF = (BASE_DAYS * 24 * 60 * 60);

    /** Stała używana przy konwersji sekund na milisekundy. */
    public static final int MSEC = 1000;

    public static void main(String[] argv) {
        String hostName;
        if (argv.length == 0)
            hostName = "localhost";
        else
            hostName = argv[0];

        try {
            Socket sock = new Socket(hostName, TIME_PORT);
            DataInputStream is = new DataInputStream(new 
                BufferedInputStream(sock.getInputStream()));
            // Musimy odczytać 4 bajty z sieci jako liczby bez znaku.
            // Musimy wykonać to sami, gdyż nie ma metody readUnsignedInt().
            // Typ long w Javie ma wielkość 8 bajtów, jednak 
            // my używamy istniejącego protokołu daytime korzystającego 
            // z liczb całkowitych o długości 4 bajtów.
            long remoteTime = (
                ((long)(is.readUnsignedByte()) << 24) |
                ((long)(is.readUnsignedByte()) << 16) |
                ((long)(is.readUnsignedByte()) <<  8) |
                ((long)(is.readUnsignedByte()) <<  0));
            System.out.println("Czas na komputerze zdalnym: " + remoteTime);
            System.out.println("BASE_DIFF is " + BASE_DIFF);
            System.out.println("Różnica czasów == " + (remoteTime - BASE_DIFF));
            Date d = new Date((remoteTime - BASE_DIFF) * MSEC);
            System.out.println("Czas na komputerze " + hostName + " to " + d.toString());
            System.out.println("Lokalna data/godzina = " + LocalDateTime.now());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
// END main
