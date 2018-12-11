package Books_exercises.JavaReceptury.webserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import com.darwinsys.util.FileProperties;

// BEGIN main
/**
 * Bardzo prosty serwer WWW.
 * <p>
 * BEZ ZABEZPIECZEŃ. NIEWYMAGAJĄCY PRAWIE ŻADNEJ KONFIGURACJI.
 * BEZ OBSŁUGI cgi. BEZ OBSŁUGI SERWLETÓW.
 *<p>
 * Wersja wielowątkowa. Wszelkie operacje wejścia-wyjścia są wykonywane
 * w klasie Handler.

 */
public class Httpd {
    /** Domyślny numer portu */
    public static final int HTTP = 80;
    /** Gniazdo serwera używane do odbierania żądań od klientów */
    protected ServerSocket sock;
    /** Obiekt Properties służący do pobierania informacji konfiguracyjnych */
    private Properties wsp;
    /** Obiekt Properties służący do pobierania informacji o typach MIME */
    private Properties mimeTypes;
    /** Katalog główny serwera */
    private String rootDir;

    public static void main(String argv[]) throws Exception {
        System.out.println("DarwinSys JavaWeb Server 0.1 \nUruchamiamy serwer...");
        Httpd w = new Httpd();
        if (argv.length == 2 && argv[0].equals("-p")) {
            w.startServer(Integer.parseInt(argv[1]));
        } else {
            w.startServer(HTTP);
        }
        w.runServer();
        // Nigdy tu nie docieramy
    }

    /** Metoda realizuje główną pętlę serwera. Za każdym razem, gdy klient
     * nawiąże połączenie, metoda ServerSocket accept() zwróci nowy obiekt
     * Socket, który będziemy wykorzystywać do obsługi operacji 
     * wejścia-wyjścia. Obiekt Socket przekazujemy do konstruktora klasy 
     * Handler, który utworzy i uruchomi nowy wątek (Thread).
     */
    void runServer() throws Exception  {
        while (true) {
                final Socket clntSock = sock.accept();
                Thread t = new Thread(){
                    public void run() {
                        new Handler(Httpd.this).process(clntSock);
                    }
                };
                t.start();
        }
    }

    /** Tworzymy nowy obiekt serwera działający na domyślnym porcie */
    Httpd() throws Exception {
        wsp=new FileProperties("httpd.properties");
        rootDir = wsp.getProperty("rootDir", ".");
        mimeTypes = 
            new FileProperties(
                wsp.getProperty("mimeProperties",
                    "mime.properties"));
    }

    public void startServer(int portNum) throws Exception {
        String portNumString = null;
        if (portNum == HTTP) {
            portNumString = wsp.getProperty("portNum");
            if (portNumString != null) {
                portNum = Integer.parseInt(portNumString);
            }
        }
        sock = new ServerSocket(portNum);
        System.out.println("Nasłuchuję na porcie " + portNum);
    }

    public String getMimeType(String type) {
        return mimeTypes.getProperty(type);
    }
    public String getMimeType(String type, String dflt) {
        return mimeTypes.getProperty(type, dflt);
    }
    public String getServerProperty(String name) {
        return wsp.getProperty(name);
    }

    public String getRootDir() {
        return rootDir;
    }
}
// END main
