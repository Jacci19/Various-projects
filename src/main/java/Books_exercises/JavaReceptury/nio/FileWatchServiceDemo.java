package Books_exercises.JavaReceptury.nio;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/** Prezentacja użycia klasy NIO WatchService dostępnej w wersji JavaSE 7+ języka.
 * 
 * P R O S Z Ę   P R Z E C Z Y T A Ć   P R Z E D   P R Ó B Ą   K O M P I L A C J I!
 * Ta klasa bezwzględnie wymaga użycia języka Java w wersji Java SE 7 lub 
 * nowszej; jeśli zatem używasz wcześniejszej wersji Javy, to dodaj do 
 * ustawień projektu regułę, która pominie to przy kompilacji kodów źródłowych
 * (Build Path -> Exclude).
 */
// BEGIN main
public class FileWatchServiceDemo {

    final static String tempDirPath = "/tmp";
    static Thread mainRunner;
    static volatile boolean done = false;

    public static void main(String[] args) throws Throwable {
        String tempDirPath = "/tmp";
        System.out.println("Rozpoczynanie obserwacji katalogu " + tempDirPath);
        Path p = Paths.get(tempDirPath);
        WatchService watcher = 
            FileSystems.getDefault().newWatchService();
        Kind<?>[] watchKinds = { ENTRY_CREATE, ENTRY_MODIFY };
        p.register(watcher, watchKinds);
        mainRunner = Thread.currentThread();
        new Thread(new DemoService()).start();
        while (!done) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> e : key.pollEvents()) {
                System.out.println(
                    "Zdarzenie " + e.kind() + " dotyczące " + 
                    e.context());
                if (e.context().toString().equals("MyFileSema.for")) {
                    System.out.println("Znaleziono semafor, zamykanie obserwatora");
                    done = true;
                }
            }
            if (!key.reset()) {
                System.err.println("Nie udało się zresetować klucza!");
            }
        }
    }

    static class DemoService implements Runnable {
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("Tworzenie pliku");
                new File(tempDirPath + "/MyFileSema.for").createNewFile();
                Thread.sleep(1000);
                System.out.println("Zatrzymywanie WatchServiceDemo");
                done = true;
                Thread.sleep(1500);
                mainRunner.interrupt();
            } catch (Exception e) {
                System.out.println("Przechwycono NIEOCZEKIWANY wyjątek " + e);
            }
        }
    }
}
// END main
