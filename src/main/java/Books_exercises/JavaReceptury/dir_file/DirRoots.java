package Books_exercises.JavaReceptury.dir_file;

import java.io.File;

public class DirRoots {
    public static void main(String argh_my_aching_fingers[]) {
        File[] drives = File.listRoots();    // Pobieramy listę nazw.
        for (File dr : drives) {
            System.out.println(dr);          // Wyświetlamy nazwy z listy.
        }
    }
}
