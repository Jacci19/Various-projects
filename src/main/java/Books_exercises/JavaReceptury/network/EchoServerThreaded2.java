package Books_exercises.JavaReceptury.network;

import java.net.*;
import java.io.*;

/**
 * Wielowątkowy serwer Echo działający na zasadzie wstępnego
 * tworzenia wątków.
 * Każdy wątek oczekuje na zakończenie wywołania metody accept()  
 * i zwrócenie połączenia; działanie wątków jest synchronizowane 
 * w oparciu o serversocket podczas wywoływania jego metody accept().
 * @author Ian F. Darwin.
 */
// BEGIN main
public class EchoServerThreaded2 {

    public static final int ECHOPORT = 7;

    public static final int NUM_THREADS = 4;

    /** Metoda main — uruchamia serwery. */
    public static void main(String[] av) {
        new EchoServerThreaded2(ECHOPORT, NUM_THREADS);
    }

    /** Konstruktor. */
    public EchoServerThreaded2(int port, int numThreads) {
        ServerSocket servSock;

        try {
            servSock = new ServerSocket(port);

        } catch(IOException e) {
            /* Jeśli pojawią się błędy w operacjach wejścia-wyjścia, 
             * zamykamy serwer. Stało się coś złego. */
            throw new RuntimeException("Nie można utworzyć obiektu ServerSocket " + e);
        }

        // Tworzymy grupę wątków i uruchamiamy je.
        for (int i=0; i<numThreads; i++) {
            new Handler(servSock, i).start();
        }
    }

    /** Klasa potomna klasy Thread obsługująca połączenia z serwerem. */
    class Handler extends Thread {
        ServerSocket servSock;
        int threadNumber;

        /** Tworzymy obiekt Handler. */
        Handler(ServerSocket s, int i) {
            servSock = s;
            threadNumber = i;
            setName("Wątek " + threadNumber);
        }

        public void run() {
            /* Czekamy na połączenie. Synchronizacja działania odbywa się 
             * w oparciu o obiekt ServerSocket podczas wywoływania jego 
             * metody accept(). */
            while (true) {
                try {
                    System.out.println(getName() + " oczekuje na połączenie");

                    Socket clientSocket;
                    // Czekamy na następne połączenie.
                    synchronized(servSock) {
                        clientSocket = servSock.accept();
                    }
                    System.out.println(getName() + 
                          ", połączenie nawiązane, IP = " + 
                          clientSocket.getInetAddress());
                    BufferedReader is = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                    PrintStream os = new PrintStream(
                        clientSocket.getOutputStream(), true);
                    String line;
                    while ((line = is.readLine()) != null) {
                        os.print(line + "\r\n");
                        os.flush();
                    }
                    System.out.println(getName() + " połączenie zakończone ");
                    clientSocket.close();
                } catch (IOException ex) {
                    System.out.println(getName() + 
                      ": Błąd wejścia-wyjścia podczas obsługi gniazda " + ex);
                    return;
                }
            }
        }
    }
}
// END main
