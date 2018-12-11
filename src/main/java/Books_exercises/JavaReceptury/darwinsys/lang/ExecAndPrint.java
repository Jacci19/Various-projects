package Books_exercises.JavaReceptury.darwinsys.lang;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.darwinsys.io.FileIO;

/**
 * Klasa ExecAndPrint wykonuje podany program używając metody Runtime.exec(),
 * odczytuje wygenerowane przez niego wyniki i zwraca jego kod zakończenia.
 */
public class ExecAndPrint {

    // BEGIN main
    /** Do wykonania każdej z tych metod potrzebny jest obiekt Runtime. */
    protected final static Runtime r = Runtime.getRuntime();

    /** Metoda wykonuje polecenie podane w formie łańcucha znaków (String)
     * i wyświetla jego wyniki w System.out (standardowym strumieniu wyjściowym).
     */
    public static int run(String cmd) throws IOException { 
        return run(cmd, new OutputStreamWriter(System.out));
    }

    /** Metoda wykonuje polecenie podane w formie łańcucha znaków (String)
     * i wyświetla jego wyniki w strumieniu "out". */
    public static int run(String cmd, Writer out) throws IOException {
        
        Process p = r.exec(cmd);

        FileIO.copyFile(new InputStreamReader(p.getInputStream()), out, true);
        try {
            p.waitFor();    // Czekamy na zakończenie procesu.
        } catch (InterruptedException e) {
            return -1;
        }
        return p.exitValue();
    }
    // END main

    /** Metoda wykonuje polecenie przekazane w formie tablicy łańcuchów
     * znaków (String[]) i wyświetla jego wyniki w standardowym
     * strumieniu wyjściowym (System.out). */
    public static int run(String[] cmd) throws IOException { 
        return run(cmd, new OutputStreamWriter(System.out));
    }

    /**  Metoda wykonuje polecenie przekazane w formie tablicy łańcuchów
     * znaków (String[]) i wyświetla jego wyniki w strumieniu "out". */
    public static int run(String[] cmd, Writer out) throws IOException {
        
        Process p = r.exec(cmd);

        FileIO.copyFile(new InputStreamReader(p.getInputStream()), out, true);

        try {
            p.waitFor();    // Czekamy na zakończenie procesu.
        } catch (InterruptedException e) {
            return -1;
        }
        return p.exitValue();
    }
}
