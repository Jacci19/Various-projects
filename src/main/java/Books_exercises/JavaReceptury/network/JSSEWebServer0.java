package Books_exercises.JavaReceptury.network;

import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

// BEGIN main
/**
 * JSSEWebServer - to klasa dziedzicząca po WebServer0, która
 * zapewnia możliwość nawiązywania połączeń SSL. 
 * Swoją drogą, konieczne jest przygotowanie certyfikatu serwera
 * (informacje na ten temat można znaleźć w książce), gdyż 
 * w przeciwnym razie zostanie głoszony przerażający wyjątek
 * javax.net.ssl.SSLHandshakeException: no cipher suites in common
 * (gdyż bez niego JSSE nie jest w stanie korzystać ze swoich 
 * wbudowanych szyfrów).
 */
public class JSSEWebServer0 extends WebServer0 {

    public static final int HTTPS = 8443;
    
    public static void main(String[] args) throws Exception {
        if (System.getProperty("javax.net.ssl.keyStore") == null) {
            System.err.println(
                "Konieczne jest określenie magazynu kluczy przy użyciu " + 
                " opcji -D; więcej informacji znajdziesz w dokumentacji!");
            System.exit(1);
        }
        System.out.println("DarwinSys JSSE Server 0.0 zaczynamy...");
        JSSEWebServer0 w = new JSSEWebServer0();
        w.runServer(HTTPS);        // Działanie nigdy się nie kończy!!
    }

    /** Metoda pobiera szyfrowane gniazdo ServerSocket używając JSSE.
     * @see WebServer0#getServerSocket(int)
     * @throws ClassNotFoundException, jeśli nie można utworzyć obiektu 
     *  SecurityProvider .
     */
    protected ServerSocket getServerSocket(int port) throws Exception {
        
        SSLServerSocketFactory ssf =
            (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        
        return ssf.createServerSocket(port);
    }

}
// END main
