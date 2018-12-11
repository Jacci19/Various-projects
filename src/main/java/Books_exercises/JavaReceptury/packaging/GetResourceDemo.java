package Books_exercises.JavaReceptury.packaging;

import java.io.*;
import java.util.*;

/** Przedstawienie wykorzystania metody classLoader.getResource() do 
 * wczytywania plików.
 * Możliwość ta jest konieczna dla działania technologii Java Web Start.
 */
public class GetResourceDemo {
    public static void main(String[] args) {
        new GetResourceDemo().demo();
    }

    /** A oto i przykład */
    public void demo() {
        // BEGIN main
        // Odnajdujemy obiekt ClassLoader użyty do pobrania programu.
        // Należy go uważać za jedyny poprawny obiekt tego typu dla naszej 
        // aplikacji.
        ClassLoader loader = this.getClass().getClassLoader();

        // Wywołanie metody getResource() w celu otworzenia pliku.
        InputStream is = loader.getResourceAsStream("widgets.properties");
        if (is == null) {
            System.err.println("Can't load properties file");
            return;
        }

        // Stworzenie obiektu Properties.
        Properties p = new Properties();

        // Wczytanie pliku właściwości do obiektu Properties.
        try {
            p.load(is);
        } catch (IOException ex) {
            System.err.println("Load failed: " + ex);
            return;
        }

        // Wyświetlenie zawartości w celu potwierdzenia wczytania
        p.list(System.out);
        // END main
    }
}
