package Books_exercises.JavaReceptury.dir_file;

import java.io.File;
import java.io.IOException;

/**
 * Tworzymy jeden lub kilka plików o podanych nazwach.
 * Ostatnie "e" zostało pominięte w hołdzie dla wywołań systemowych Uniksa.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Creat {
    public static void main(String[] argv) throws IOException {

        // Upewniamy się, że w argv[0] została podana nazwa pliku 
        // lub innego elementu systemu plików.
        if (argv.length == 0) {
            System.err.println("Sposób użycia: Creat nazwa_pliku");
            System.exit(1);
        }

        for (String a : argv) {
            // Stworzenie obiektu File nie ma wpływu na system plików,
            // natomiast wywołanie metody createNewFile() ma wpływ.
            new File(a).createNewFile();
        }
    }
}
// END main
