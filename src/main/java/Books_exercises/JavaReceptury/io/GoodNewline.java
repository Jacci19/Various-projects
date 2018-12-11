package Books_exercises.JavaReceptury.io;

import java.io.*;

/**
 * Wyświetlanie kilku wierszy kodu w jednym wywołaniu.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class GoodNewline {
    // BEGIN main
    String myName;
    public static void main(String[] argv) {
        GoodNewline jack = new GoodNewline("Hermenegilda Kociubińska");
        jack.print(System.out);
    }

    protected void print(PrintStream out) {
        out.println(toString());  // Nazwa klasy oraz kod mieszający.
        out.println(myName); // Wyświetlamy personalia w następnym wierszu.
    }

    // END main
    
    /* Konstruktor klasy. */
    public GoodNewline(String s) {
        myName = s;
    }
}
