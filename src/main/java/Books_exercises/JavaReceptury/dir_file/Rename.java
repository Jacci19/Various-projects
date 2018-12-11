package Books_exercises.JavaReceptury.dir_file;

import java.io.*;

/**
 * Zmiana nazwy pliku.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Rename {
    public static void main(String[] argv) throws IOException {

       // Tworzymy obiekt File; czynność ta NIE powoduje utworzenia pliku na dysku!
       File f = new File("Rename.java~"); // Kopia tego pliku źródłowego.

       // Zmieniamy nazwę kopii pliku na "junk.dat".
       // Zmiana nazwy wymaga użycia obiektu File reprezentującego
       // plik o nowej nazwie.
       f.renameTo(new File("junk.dat"));
    }
}
// END main
