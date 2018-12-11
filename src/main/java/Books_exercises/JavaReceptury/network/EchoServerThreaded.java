package Books_exercises.JavaReceptury.network;

import java.net.*;
import java.io.*;

/**
 * Serwer Echo wykorzystujący wątki, działający przy wykorzystaniu
 * sekwencyjnej metody przydzielania.
 * @author Ian F. Darwin.
 */
// BEGIN main
public class EchoServerThreaded {

    public static final int ECHOPORT = 7;

    public static void main(String[] av)
    {
        new EchoServerThreaded().runServer();
    }

    public void runServer()
    {
        ServerSocket sock;
        Socket clientSocket;

        try {
            sock = new ServerSocket(ECHOPORT);
        
            System.out.println("EchoServerThreaded oczekuje na połączenia.");

            /* Czekamy na połączenia. */
            while(true){
                clientSocket = sock.accept();
                /* Tworzymy i uruchamiamy watek obsługujący połączenie. */
                new Handler(clientSocket).start();
            }
        } catch(IOException e) {
            /* Zamykamy serwer, jeśli pojawią się błędy podczas operacji 
             * wejścia-wyjścia. Przydarzyło się coś złego. */
            System.err.println("Nie mogłem odebrać połączenia " + e);
            System.exit(1);
        }
    }

    /** Klasa potomna klasy Thread obsługująca komunikację z jednym klientem. */
    class Handler extends Thread {
        Socket sock;

        Handler(Socket s) {
            sock = s;
        }

        public void run() {
            System.out.println("Otwieram połączenie: " + sock);
            try {
                BufferedReader is =
                    new BufferedReader(
                        new InputStreamReader(sock.getInputStream()));
                PrintStream os = new PrintStream(sock.getOutputStream(), true);
                String line;
                while ((line = is.readLine()) != null) {
                    os.print(line + "\r\n");
                    os.flush();
                }
                sock.close();
            } catch (IOException e) {
                System.out.println(
                       "Błąd wejścia-wyjścia podczas obsługi gniazda " + e);
                return;
            }
            System.out.println("Połączenie zamknięte: " + sock);
        }
    }
}
// END main
