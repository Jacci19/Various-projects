package Books_exercises.JavaReceptury.dir_file;

import java.io.*;

// BEGIN main
public class ListRoots {
    public static void main(String argh_my_aching_fingers[]) {
        File[] drives = File.listRoots(); // Pobieramy listę nazw.
        for (File dr : drives) {
            System.out.println(dr);        // Wyświetlany nazwy z listy.
        }
    }
}
// END main
