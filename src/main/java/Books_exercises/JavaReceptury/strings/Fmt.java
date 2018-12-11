package Books_exercises.JavaReceptury.strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Fmt - formatowanie tekstu (działa podobnie do programu
 * Berkeley UNIX fmt).
 */
// BEGIN main
public class Fmt {
    /** Maksymalna szerokość wiersza. */
    public static final int COLWIDTH=72;
    /** Plik, którego zawartość odczytujemy i formatujemy. */
    final BufferedReader in;
    /** Strumień danych wynikowych. */
    PrintWriter out;

    /** Jeśli pliki istnieją, to formatujemy każdy z nich,
     * w przeciwnym przypadku formatujemy dane odczytywane 
     * ze standardowego strumienia wejściowego. 
     */
    public static void main(String[] av) throws IOException {
        if (av.length == 0)
            new Fmt(System.in).format();
        else for (String name : av) {
            new Fmt(name).format();
        }
    }
    
    public Fmt(BufferedReader inFile, PrintWriter outFile) {
        this.in = inFile;
        this.out = outFile;
    }
    
    public Fmt(PrintWriter out) {
        this(new BufferedReader(new InputStreamReader(System.in)), out);
    }

    /** Tworzymy obiekt Formatter używając obiektu Reader. */
    public Fmt(BufferedReader file) throws IOException {
        this(file, new PrintWriter(System.out));
    }
    
    /** Tworzymy obiekt Formatter, dysponując nazwą pliku. */
    public Fmt(String fname) throws IOException {
        this(new BufferedReader(new FileReader(fname)));
    }

    /** Tworzymy obiekt Formatter, dysponując strumieniem Stream. */
    public Fmt(InputStream file) throws IOException {
        this(new BufferedReader(new InputStreamReader(file)));
    }

    /** Formatujemy plik (File) wskazany w utworzonym obiekcie Fmt. */
    public void format() throws IOException {
        String line;
        StringBuilder outBuf = new StringBuilder();
        while ((line = in.readLine()) != null) {
            if (line.length() == 0) {    // Pusty wiersz.
                out.println(outBuf);    // Koniec bieżącego wiersza.
                out.println();    // Wyświetlamy pusty wiersz.
                outBuf.setLength(0);
            } else {
                // W przeciwnym przypadku mamy zwyczajny tekst,
                // który formatujemy.
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens()) {
                    String word = st.nextToken();

                    // Jeśli słowo przekroczyłoby margines,
                    // to najpierw wyświetlamy zgromadzony tekst.
                    if (outBuf.length() + word.length() > COLWIDTH) {
                        out.println(outBuf);
                        outBuf.setLength(0);
                    }
                    outBuf.append(word).append(' ');
                }
            }
        }
        if (outBuf.length() > 0) {
            out.println(outBuf);
        } else {
            out.println();
        }
    }
}
// END main
