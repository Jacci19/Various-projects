package Books_exercises.JavaReceptury.webserver;

import java.net.Socket;
import java.util.concurrent.*;

// BEGIN main
/**
 * HttpdConcurrent - klasa rozszerzająca klasę Httpd i korzystająca
 * z narzędzi dostępnych w pakiecie java.util.concurrent.
 */
public class HttpdConcurrent extends Httpd {

    private final Executor myThreadPool;

    public HttpdConcurrent() throws Exception {
        super();
        myThreadPool = Executors.newFixedThreadPool(5);        
    }

    public static void main(String[] argv) throws Exception {
        System.out.println("DarwinSys JavaWeb Server 0.1 \nUruchamiamy serwer...");
        HttpdConcurrent w = new HttpdConcurrent();
        if (argv.length == 2 && argv[0].equals("-p")) {
            w.startServer(Integer.parseInt(argv[1]));
        } else {
            w.startServer(HTTP);
        }
        w.runServer();
    }
    public void runServer() throws Exception {
        while (true) {
            final Socket clientSocket = sock.accept();
            myThreadPool.execute(new Runnable() {
                public void run() {
                    new Handler(HttpdConcurrent.this).process(clientSocket);
                }
            });
        }
    }
}
// END main
