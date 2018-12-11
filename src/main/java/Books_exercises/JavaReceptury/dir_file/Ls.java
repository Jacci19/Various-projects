package Books_exercises.JavaReceptury.dir_file;

import java.util.Arrays;

/** Proste wyświetlanie zawartości katalogu. 
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Ls {
    public static void main(String args[]) {
        String[] dirs = new java.io.File(".").list(); // Pobranie listy nazw.
        Arrays.sort(dirs);        // Sortowanie (patrz rozdział 7.)
        for (String dir : dirs) {
            System.out.println(dir);        // Wyświetlenie nazw z listy.
        }
    }
}
// END main
