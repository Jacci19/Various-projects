package Books_exercises.JavaReceptury.otherlang;

import com.darwinsys.lang.ExecAndPrint;

/**
 * Program tworzy pliki, wyświetla ich listę, a następnie je usuwa.
 */
public class ExecDemoFiles {
    public static void main(String av[]) throws Exception {
        
        // BEGIN main
        // Pobieramy i zapamiętujemy obiekt Runtime.
        Runtime rt = Runtime.getRuntime();

        // Tworzymy trzy pliki tymczasowe.
        rt.exec("mktemp file1");
        rt.exec("mktemp file2");
        rt.exec("mktemp file3");

        // Wykonujemy polecenie "ls" (wydruk zawartości katalogu), 
        // a generowane przez niego wyniki zapisujemy w pliku.
        String[] args = { "ls", "-l", "file1", "file2", "file3" };
        ExecAndPrint.run(args);

        rt.exec("rm file1 file2 file3");
        // END main
    }
}
