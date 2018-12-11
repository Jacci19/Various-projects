package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;

/**
 * DaytimeServer - wysyła datę i czas w formie binarnej.
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class DaytimeServer {
    /** Numer gniazda serwera. */
    ServerSocket sock;
    /** Domyślnie używany numer portu. */
    public final static int PORT = 37;

    /** Stworzenie obiektu i jego uruchomienie. */
    public static void main(String[] argv) {
        new DaytimeServer(PORT).runService();
    }

    /** Konstruktor klasy DaytimeServer operujący na porcie o podanym numerze. */
    public DaytimeServer(int port) {
        try {
            sock = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Błąd wejścia-wyjścia podczas inicjalizacji\n" + e);
            System.exit(1);
        }
    }

    /** Metoda obsługuje połączenia. */
    protected void runService() {
        Socket ios = null;
        DataOutputStream os = null;
        while (true) {
            try {
                System.out.println("Oczekujemy na połączenia na porcie " + PORT);
                ios = sock.accept();
                System.err.println("Odebrano połączenie z " +
                    ios.getInetAddress().getHostName());
                os = new DataOutputStream(ios.getOutputStream());
                long time = System.currentTimeMillis();

                time /= RDateClient.MSEC;    // Protokół Daytime przesyła 
                                             // ilość sekund.

                // Konwertujemy na czas używany w Javie.
                time += RDateClient.BASE_DIFF;

                // Zapisujemy, ograniczając zakres przy rzutowaniu do 
                // typu int, co jest konieczne, gdyż internetowy protokół 
                // Daytime wymaga użycia czterech bajtów. Rozwiązanie to 
                // przestanie działać poprawnie w roku 2038 wraz z wszelkimi 
                // systemami operacyjnymi, w których czas przechowywany jest 
                // jako zmienna całkowita (int) wyrażająca liczbę sekund, 
                // jakie upłynęły od początku roku 1970.
                // Przypomnij sobie we właściwym czasie, że to właśnie w tej 
                // książce po raz pierwszy przeczytałeś o problemie 
                // roku 2038!
                os.writeInt((int)time);
                os.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
// END main
