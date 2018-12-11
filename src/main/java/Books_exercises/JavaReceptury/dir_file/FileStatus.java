package Books_exercises.JavaReceptury.dir_file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Zwraca informacje o pliku.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class FileStatus {
    public static void main(String[] argv) throws IOException {

        // Zapewniamy, że nazwa pliku (lub innego elementu systemu plików) 
        // została podana jako argv[0].
        if (argv.length == 0) {
            System.err.println("Sposób użycia: status nazwa_pliku");
            System.exit(1);
        }
        for (String a : argv) {
            status(a);
        }
    }

    public static void status(String fileName) throws IOException {
        System.out.println("---" + fileName + "---");

        // Tworzymy obiekt File reprezentujący dany plik.
        File f = new File(fileName);

        // Sprawdzamy, czy plik faktycznie istnieje.
        if (!f.exists()) {
            System.out.println("Nie znaleziono pliku.");
            System.out.println();    // Pusty wiersz.
            return;
        }
        // Wyświetlamy pełną nazwę.
        System.out.println("Nazwa kanoniczna " + f.getCanonicalPath());
        // Jeśli to możliwe, to wyświetlany katalog nadrzędny.
        String p = f.getParent();
        if (p != null) {
            System.out.println("Katalog nadrzędny: " + p);
        }
        // Czy zawartość pliku można odczytać?
        if (f.canRead()) {
            System.out.println("Zawartość pliku można odczytać.");
        }
        // Czy można zapisywać w pliku?
        if (f.canWrite()) {
            System.out.println("W pliku można zapisywać.");
        }
        // Informacje na temat czasu modyfikacji.
        Date d = new Date(f.lastModified());
        System.out.println("Data ostatniej modyfikacji to " + d);

        // Sprawdzamy, czy to katalog bądź inny element systemu plików. 
        // Jeśli jest to plik, wyświetlamy jego wielkość.
        if (f.isFile()) {
            // Zwracamy informacje o wielkości pliku.
            System.out.println("Plik ma wielkość " + f.length() + " bajtów.");
        } else if (f.isDirectory()) {
            System.out.println("To katalog");
        } else {
            System.out.println("Nie potrafię określić typu! Nie jest to ani plik, ani katalog!");

        }

        System.out.println();    // Pusty wiersz między informacjami o kolejnych 
                                 // elementach systemu plików.
    }
}
// END main
