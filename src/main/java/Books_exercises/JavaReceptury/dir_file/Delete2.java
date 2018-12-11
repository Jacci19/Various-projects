package Books_exercises.JavaReceptury.dir_file;

import java.io.File;

/**
 * Usuwanie plików w Javie wraz z obsługą błędów.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Delete2 {

    public static void main(String[] argv) {
        for (String a : argv) {
            delete(a);
        }
    }

    public static void delete(String fileName) {
        try {
            // Tworzymy obiekt File reprezentujący plik, który
            // chcemy usunąć.
            File target = new File(fileName);

            if (!target.exists()) {
               System.err.println("Plik " + fileName + 
                     " nie jest dostępny!");
                return;
            }

            // Teraz usuwamy plik:
            if (target.delete())
                System.err.println("** Usunięto " + fileName + " **");
            else
                System.err.println("Nie udało się usunąć " + fileName);
        } catch (SecurityException e) {    
           System.err.println("Nie udało się usunąć " + fileName +
                 "(" + e.getMessage() + ")");
        }
    }
}
// END main
