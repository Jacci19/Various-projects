package Books_exercises.JavaReceptury.dir_file;

import java.io.File;
import java.util.Arrays;

/**
 * FNFilter - Wyświetlanie zawartości katalogu przy użyciu wyrażenia 
 * lambda FilenameFilter.
 * @author Ian Darwin
 */
public class FNFilterL {
    public static void main(String args[]) {

        String dirName = args.length > 0 ? args[0] : ".";
        if (!new File(dirName).exists()) {
            System.err.printf("Plik %s Nie istnieje", dirName);
            System.exit(1);
        }

        // BEGIN main
        // Generujemy wybiórczą listę, używając wyrażenia lambda.
        String[] dirs = new java.io.File(dirName).list(
            (dir, s) -> {
                return s.endsWith(".java") ||
                    s.endsWith(".class") ||
                    s.endsWith(".jar");
            }
        );
        Arrays.sort(dirs);        // Sortowanie (patrz rozdział 7.)
        for (String d : dirs) {
            System.out.println(d);    // Wyświetlamy listę.
        }
        // END main
    }
}
