package Books_exercises.JavaReceptury.dir_file;

import java.io.File;
import java.io.IOException;

/**
 * Usuwanie pliku.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Delete {
    public static void main(String[] argv) throws IOException {

       // Tworzymy obiekt File reprezentujący plik kopii zapasowej
       // bieżącego pliku (utworzonej automatycznie podczas edycji
       // tego pliku). Ta kopia zapewne już istnieje.
       // Używany przeze mnie edytor tworzy kopie plików, dodając do
       // ich oryginalnej nazwy znak "~".
       File bkup = new File("Delete.java~");
       // A teraz usuwamy plik.
       bkup.delete();
    }
}
// END main
