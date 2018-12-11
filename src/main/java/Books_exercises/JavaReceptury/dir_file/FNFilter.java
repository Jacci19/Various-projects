package Books_exercises.JavaReceptury.dir_file;

import java.io.*;
import java.util.Arrays;

/**
 * FNFilter - klasa Ls z dodanymi możliwościami wykorzystania 
 * obiektu klasy FilenameFilter.
 * @author Ian Darwin
 */
// BEGIN main
public class FNFilter {
    public static void main(String argh_my_aching_fingers[]) {

        // Tworzymy listę plików, wykorzystując przy tym obiekt File
        // przeznaczony do jednokrotnego użycia.
        String[] dirs = new java.io.File(".").list(new OnlyJava());
        Arrays.sort(dirs);        // Sortowanie (patrz rozdział 7.)
        for (String d : dirs) {
            System.out.println(d);    // Wyświetlamy listę.
        }
    }

    /** Ta klasa implementuje interfejs FilenameFilter.
     * Metoda accept zwraca wartość true wyłącznie dla plików .java i .class.
     */
    private static class OnlyJava implements FilenameFilter {
        public boolean accept(File dir, String s) {
            if (s.endsWith(".java") ||
                s.endsWith(".class") ||
                s.endsWith(".jar")) {

                return true;
            }
            // Inne pliki: projekty... ?
            return false;
        }
    }
}
// END main
