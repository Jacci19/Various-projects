package Books_exercises.JavaReceptury.io;

import java.io.*;

/**
 * "Przekierowanie" lub zmiana niektórych standardowych deskryptorów.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Redirect {
    public static void main(String[] argv) throws IOException {
        // BEGIN main
        String LOGFILENAME = "error.log";
        System.setErr(new PrintStream(new FileOutputStream(LOGFILENAME)));
        System.out.println("Informacje o błędach można znaleźć w " + LOGFILENAME);
        // A teraz aby zobaczyć, jaki cudzy kod generuje 
        // informacje w standardowym strumieniu błędów...
        int[] a = new int[5];
        a[10] = 0;    // Ta instrukcja generuje wyjątek. ArrayIndexOutOfBoundsException
        // END main
    }
}
