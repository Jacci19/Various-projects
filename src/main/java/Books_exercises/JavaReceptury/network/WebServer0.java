package Books_exercises.JavaReceptury.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bardzo, bardzo, bardzo prosty serwer WWW.
 *
 * Serwer na wszystkie odbierane żądania przesyła tylko jedną odpowiedź, 
 * a co więcej jest ona określon na stałę. Ta wersja serwera nie korzysta 
 * z wielu wątków i nie robi zbyt wiele. 
 * W zasadzie jest ona jedynie potwierdzeniem możliwości rezliacji 
 * pomysłu. 
 * Niemniej jednak wciąż może się przydać na notebookach, w przypadku gdy
 * ktoś przez przypadek (lub celowo) połączy się z naszym komputerem 
 * na porcie używanym przez serwer WWW.
 * 
 * Nie mogę zagwarantować, że serwer będzie w pełni zgodny ze standardami,
 * jednak przetestowałam go przy użyciu kilku różnych przeglądarek.
 *
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class WebServer0 {
    public static final int HTTP = 80;
    public static final String CRLF = "\r\n";
    ServerSocket s;
    static final String VIEW_SOURCE_URL =
      "https://github.com/IanDarwin/javasrc/tree/master/src/main/java/network";

    /**
     * Metoda main(), jej działanie ogranicza się do utworzenia 
     * serwera i wywołania jego metody runServer().
     */
    public static void main(String[] argv) throws Exception {
        System.out.println("DarwinSys JavaWeb Server 0.0, startuję...");
        WebServer0 w = new WebServer0();
        w.runServer(HTTP);     // Działanie serwera nigdy się nie kończy!!
    }

    /** Metoda zwraca faktyczny obiekt ServerSocket; przy czym jego 
     * pobranie jest odroczone w czasie i następuje dopiero po 
     * wykonaniu konstruktora, dzięki czemu klasy potomne będą mogły
     * modyfikować działanie ServerSocketFactory (na przykład 
     * dodając korzystanie z SSL). 
     * @param port Numer portu na którym serwer będzie nasłuchiwał.
     */
    protected ServerSocket getServerSocket(int port) throws Exception {
        return new ServerSocket(port);
    }

    /** Metoda runServer akceptuje połączenia i każde z nich osobno
     * przekazuje do obiektu obsługującego - Handler. */
    public void runServer(int port) throws Exception {
        s = getServerSocket(port);
        while (true) {
            try {
                Socket us = s.accept();
                Handler(us);
            } catch(IOException e) {
                System.err.println(e);
                return;
            }

        }
    }

    /** Metoda Handler() obsługuje jedno połączenie z klientem.
     * To jedyne miejsce programu, które musi "znać" protokół HTTP.
     */
    public void Handler(Socket s) {
        BufferedReader is;     // Strumień wejściowy od klienta.
        PrintWriter os;        // Strumień wyjściowy do klienta.
        String request;        // Co klient nam przesłał.
        try {
            String from = s.getInetAddress().toString();
            System.out.println("Nawiązano połącznie z " + from);
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            request = is.readLine();
            System.out.println("Żądanie: " + request);
            
            os = new PrintWriter(s.getOutputStream(), true);
            os.print("HTTP/1.0 200 To nadesłane dane:" + CRLF);
            os.print("Content-type: text/html" + CRLF);
            os.print("Server-name: DarwinSys NULL Java WebServer 0" + CRLF);
            String reply1 = "<html><head>" +
                "<title>Żądanie dotarło do złego serwera</title></head>\n" +
                "<h1>Witam, ";
            String reply2 = ", ale...</h1>\n" +
                "<p>Żądanie dotarło na normalny komputer, na którym  " +
                "nie działa serwer WWW z prawdziwego zdarzenia.\n" +
                "<p>Proszę wybrać inny adres!</p>\n" +
                "<p>Albo zajrzyj na stronę <a href=\"" + 
                VIEW_SOURCE_URL + "\"> zawierającą kod źródłowy " +
                "serwera WebServer0 opublikowany na GitHubie</a>.</p>\n" +
                "<hr/><em>Serwer WebServer0 napisany w Javie</em><hr/>\n" +
                "</html>\n";
            os.print("Content-length: " + 
                (reply1.length() + from.length() + reply2.length()) + CRLF);
            os.print(CRLF);
            os.print(reply1 + from + reply2 + CRLF);
            os.flush();
            s.close();
        } catch (IOException e) {
            System.out.println("Błąd wejścia-wyjścia: " + e);
        }
        return;
    }
}
// END main
