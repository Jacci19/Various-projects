package Books_exercises.JavaReceptury.dir_file;

import java.io.*;

/**
 * Wykorzystanie plików tymczasowych w Javie.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class TempFiles {
    public static void main(String[] argv) throws IOException {

       // 1. Określamy, że istniejący plik będzie plikiem tymczasowym.

       // Tworzymy obiekt File dla kopii zapasowej utworzonej poprzez
       // edycję tego pliku źródłowego. Ta kopia najprawdopodobniej już 
       // istnieje. Używany przeze mnie edytor tworzy takie kopie, 
       // dodając znak "~" na końcu nazwy oryginalnego pliku.
       File bkup = new File("Rename.java~");
       // Nakazujemy usunięcie tego pliku pod koniec działania programu.
       bkup.deleteOnExit();

       // 2. Tworzymy nowy plik tymczasowy.

       // Tworzymy obiekt File dla pliku foo.tmp, umieszczonego
       // w domyślnym katalogu tymczasowym (tmp).
       File tmp = File.createTempFile("foo", "tmp");
       // Wyświetlamy nazwę, jaka została nadana plikowi tymczasowemu.
       System.out.println("Używamy pliku tymczasowego " + tmp.getCanonicalPath());
       // Nakazujemy usunięcie pliku podczas kończenia programu.
       tmp.deleteOnExit();
       // Teraz można w dowolny sposób wykorzystać plik tymczasowy,
       // bez konieczności pamiętania o tym, że należy go usunąć.
       writeDataInTemp(tmp.getCanonicalPath());
    }

    public static void writeDataInTemp(String tempnam) {
       // Ta metoda jest pusta... można ją wykorzystać 
       // wedle własnego uznania.
    }
}
// END main
