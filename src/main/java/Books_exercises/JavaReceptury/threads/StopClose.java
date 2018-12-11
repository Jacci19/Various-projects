package Books_exercises.JavaReceptury.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/** Kończymy wątek poprzez zamknięcie gniazda.
 */
// BEGIN main
public class StopClose extends Thread {
    protected Socket io;

    public void run() {
        try {
            io = new Socket("java.sun.com", 80);    // HTTP
            BufferedReader is = new BufferedReader(
                new InputStreamReader(io.getInputStream()));
            System.out.println("StopClose reading");

            // Wykonanie kolejnej instrukcji spowoduje (celowo) zablokowanie
            // wątku, gdyż protokół HTTP wymaga, aby przed odebraniem 
            // odpowiedzi klient przesłał żądanie (na przykład: 
            // "GET / HTTP/1.0") oraz pusty wiersz.

            String line = is.readLine();    // BLOKADA

            // Wykonywanie metody readLine może zostać przerwane wyłącznie 
            // w przypadku zgłoszenia wyjątku lub zamknięcia gniazda.

            // A zatem poniższa instrukcja nigdy nie powinna zostać wykonana.
            System.out.printf("Wątek StopClose ZAKOŃCZONY po odczytaniu " + 
                              "wierszy tekstu %s!?", line);
        } catch (IOException ex) {
            System.out.println("Kończymy działanie wątku StopClose: " + ex);
        }
    }

    public void shutDown() throws IOException {
        if (io != null) {
            // To powinno przerwać oczekiwanie na odczytanie danych z gniazda.
            synchronized(io) {
                io.close();
            }
        }
        System.out.println("Zakończono wywołanie StopClose.shutDown()");
    }

    public static void main(String[] args) 
    throws InterruptedException, IOException {
        StopClose t = new StopClose();
        t.start();
        Thread.sleep(1000*5);
        t.shutDown();
    }
}
// END main
