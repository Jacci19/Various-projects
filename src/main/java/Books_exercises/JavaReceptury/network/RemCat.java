package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;

/**
 * <p>
 * RemCat - zdalne pobieranie plików (DOS-owyc) przy użyciu protokołu 
 * TFTP. Wzorowane na ćwiczeniu "rcat" z Learning Tree Course 363, 
 * <I>UNIX Network Programming</I>, opracowanym przez Dr. Chris Brown.
 * 
 * Należy pamiętać, że serwer TFTP NIE jest programem wielojęzycznym; 
 * nazwa oraz tryb używane w protokole są zapisywane w kodzie ASCII, 
 * a nie UniCode.</p>
 *
 * @author    Chris R. Brown, original C version
 * @author    Java version by Ian Darwin, http://www.darwinsys.com/.
 */
// BEGIN main
public class RemCat {
    /** Numer portu UDP. */
    public final static int TFTP_PORT = 69;
    /** Używany tryb pracy - zawsze "octet". */
    protected final String MODE = "octet";

    /** Przesunięcie kodu/odpowiedzi w bajtach */
    protected final int OFFSET_REQUEST = 1;
    /** Przesunięcie numeru pakietu w bajtach */
    protected final int OFFSET_PACKETNUM = 3;

    /** Flaga testowania */
    protected static boolean debug = false;

    /** Kody operacji protokołu TFTP dla żądania odczytu. */
    public final int OP_RRQ = 1;
    /** Kody operacji protokołu TFTP dla żądania odczytu. */
    public final int OP_WRQ = 2;
    /** Kody operacji protokołu TFTP dla żądania odczytu. */
    public final int OP_DATA = 3;
    /** Kody operacji protokołu TFTP dla żądania odczytu. */
    public final int OP_ACK    = 4;
    /** Kody operacji protokołu TFTP dla żądania odczytu. */
    public final int OP_ERROR = 5;

    protected final static int PACKET_SIZE = 516;    // == 2 + 2 + 512
    protected String host;
    protected InetAddress servAddr;
    protected DatagramSocket sock;
    protected byte buffer[];
    protected DatagramPacket inp, outp;

    /** Program główny obsługujący klienta sieciowego.
     * param argv[0] nazwa komputera na którym działa serwer TFTP
     * param argv[1..n] nazwy plików (musi być przynajmniej jedna)
     */
    public static void main(String[] argv) throws IOException {
        if (argv.length < 2) {
            System.err.println("Sposób użycia: rcat komputer nazaPliku[...]");
            System.exit(1);
        }
        if (debug)
            System.err.println("Uruchamiamy program RemCat.");
        RemCat rc = new RemCat(argv[0]);
        for (int i = 1; i<argv.length; i++) {
            if (debug)
                System.err.println("-- Rozpoczynamy pobieranie pliku " + 
                    argv[0] + ":" + argv[i] + "---");
            rc.readFile(argv[i]);
        }
    }

    RemCat(String host) throws IOException {
        super();
        this.host = host;
        servAddr = InetAddress.getByName(host);
        sock = new DatagramSocket();
        buffer = new byte[PACKET_SIZE];
        outp = new DatagramPacket(buffer, PACKET_SIZE, servAddr, TFTP_PORT);
        inp = new DatagramPacket(buffer, PACKET_SIZE);
    }

    /* Tworzymy pakiet żądania odczytu protokołu TFTP. To nieco 
     * złożone, gdyż długości pól są zmienne. Poszczególne bajty 
     * liczb muszą być zapisywane w kolejności "sieciowej";
     * na szczęście Java wydaje się dość "inteligentna", 
     * gdyż sama używa właśnie tej kolejności zapisu.
     */
    void readFile(String path) throws IOException {
        buffer[0] = 0;
        buffer[OFFSET_REQUEST] = OP_RRQ;        // Żądanie odczytu
        int p = 2;            // Ilość znaków w buforze

        // Konwersja łańcucha znaków zawierającego nazwę pliku na 
        // bajty (zapisane w buforze). Zmienna "p" służy jako 
        // wskaźnik przesunięcia precyzyjnie umieszczający wszystkie
        // bajty żądania w odpowiednim położeniu.
        byte[] bTemp = path.getBytes();    // Na przykład ASCII
        System.arraycopy(bTemp, 0, buffer, p, path.length());
        p += path.length();
        buffer[p++] = 0;        // Bajt o wartości 0 kończy łańcuch znaków

        // Podobnie konwertujemy tryb (MODE - "octet"), zapisując go
        // w buforze w postaci bajtów.
        bTemp = MODE.getBytes();    // Na przykład ASCII
        System.arraycopy(bTemp, 0, buffer, p, MODE.length());
        p += MODE.length();
        buffer[p++] = 0;            // Bajt o kodzie 0 kończy łańcuch

        /* Wysyłamy żądanie odczytu do serwera tftp */
        outp.setLength(p);
        sock.send(outp);

        /* Pętla odczytująca pakiety danych z serwera aż do 
         * momentu odnalezienia pakietu krótszego niż standardowy;
         * taki pakiet oznacza koniec pliku.
         */
        int len = 0;
        do {
            sock.receive(inp);
            if (debug)
                System.err.println(
                    "Pakiet # " + Byte.toString(buffer[OFFSET_PACKETNUM])+
                    "KOD ODPOWIEDZI " + Byte.toString(buffer[OFFSET_REQUEST]));
            if (buffer[OFFSET_REQUEST] == OP_ERROR) {
                System.err.println("BŁĄD: " +
                    new String(buffer, 4, inp.getLength()-4));
                return;
            }
            if (debug)
                System.err.println("Odebrano pakiet wielkości " +
                    inp.getLength());

            /* Wyświetlenie danych odczytanych z pakietu */
            System.out.write(buffer, 4, inp.getLength()-4);

            /* Potwierdzenie odbioru pakietu. 
             * Numer pakietu, który chcemy potwierdzić, jest już 
             * w buforze, a zatem wystarczy zmienić kod.
             * Potwierdzenie jest wysyłane na port, z którego 
             * serwer wysłał dane, a nie na port TFTP_PORT.
             */
            buffer[OFFSET_REQUEST] = OP_ACK;
            outp.setLength(4);
            outp.setPort(inp.getPort());
            sock.send(outp);
        } while (inp.getLength() == PACKET_SIZE);

        if (debug)
            System.err.println("** GOTOWE ** Koniec pętli, wielkość ostatniego pakietu " +
                inp.getLength());
    }
}
// END main
