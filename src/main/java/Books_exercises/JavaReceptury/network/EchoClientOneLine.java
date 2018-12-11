package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;
/**
 * EchoClientOneLine - tworzy gniazdo klienckie, wysyła jeden 
 * wiersz tekstu i odczytuje go. Przejrzyj także program 
 * EchoClient.java, który jest nieco bardziej wyszukany.
 */
// BEGIN main
public class EchoClientOneLine {
    /** Wysyłany tekst. */
    String mesg = "Witamy inny komputer w sieci";

    public static void main(String[] argv) {
        if (argv.length == 0)
            new EchoClientOneLine().converse("localhost");
        else
            new EchoClientOneLine().converse(argv[0]);
    }

    /** Wymiana informacji za pośrednictwem sieci. */
    protected void converse(String hostName) {
        try {
            Socket sock = new Socket(hostName, 7); // Serwer Echo.
            BufferedReader is = new BufferedReader(new 
                InputStreamReader(sock.getInputStream()));
            PrintWriter os = new PrintWriter(sock.getOutputStream(), true);
            // Sami obsługujemy CRLF, gdyż metoda println dodaje \r tylko 
            // w systemach, gdzie to zakończenie wierszy jest stosowane.
            os.print(mesg + "\r\n"); os.flush();
            String reply = is.readLine();
            System.out.println("Wysłano \"" + mesg  + "\"");
            System.out.println("Odebrano \"" + reply + "\"");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
// END main
