package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;

/**
 * Prosty klient UDP - nawiązanie "połączenia" z usługą czasu.
 * @author Ian Darwin, http://www.darwinsys.com/.
 */
// BEGIN main
public class DaytimeUDP {
    /** Numer portu UDP. */
    public final static int DAYTIME_PORT = 13;

    /** Bufor wystarczająco duży, aby pomieścić w nim łańcuch znaków. */
    protected final static int PACKET_SIZE = 100;

    /** Program główny obsługujący klienta sieciowego.
     * param argv[0] hostname, running daytime/udp server
     */
    public static void main(String[] argv) throws IOException {
        if (argv.length < 1) {
            System.err.println("Sposób użycia: java DayTimeUDP komputer");
            System.exit(1);
        }
        String host = argv[0];
        InetAddress servAddr = InetAddress.getByName(host);
        DatagramSocket sock = new DatagramSocket();
        //sock.connect(servAddr, DAYTIME_PORT);
        byte[] buffer = new byte[PACKET_SIZE];

        // Pakiet UDP, który wyślemy i odbierzemy.
        DatagramPacket packet = new DatagramPacket(
            buffer, PACKET_SIZE, servAddr, DAYTIME_PORT);

        /* Wysyłamy do serwera pusty pakiet o długości max-length 
         * (-1 na bajt null). */
        packet.setLength(PACKET_SIZE-1);
        sock.send(packet);
        System.out.println("Wysłano żądanie.");

        // Odbieramy pakiet i wyświetlamy dane.
        sock.receive(packet);
        System.out.println("Odebrano pakiet o długości " + packet.getLength());
        System.out.print("Data na komputerze " + host + " to " + 
            new String(buffer, 0, packet.getLength()));
        
        sock.close();
    }
}
// END main
