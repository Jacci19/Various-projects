package Books_exercises.JavaReceptury.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/** Klasa TeePrintStream zapisuje wszystkie operacje wykonywane na strumieniu 
 * PrintStream zarówno w nim, jak i we wskazanym pliku, podobnie do uniksowego
 * polecenia tee(1). Jest ona klasą potomną klasy PrintStream. 
 * Poniżej przedstawiłem sugerowany sposób jej wykorzystania: 
 * <PRE>
 *    ...
 *    TeePrintStream ts = new TeePrintStream(System.err, "err.log");
 *    System.setErr(ts);
 *    // ...dużo kodu, który od czasu do czasu zapisuje coś w strumieniu System.err...
 *    ts.close();
 *    ...
 * <PRE>
 * <P>Przesłaniam jedynie konstruktory oraz metody write(), check() oraz close(),
 * gdyż wszystkie inne wywołania metody print() i println() i tak prowadzą do 
 * wywołania write().
 * Dziękuję Svante Karlssonowi za pomoc w opisaniu działania klasy.
 * <br/>
 * Uwaga: to samo można także zrobić w inny sposób, używając klasy FilterStream;
 * patrz przykład przedstawiony na stronie http://www.javaspecialists.eu/archive/Issue003.html
 * (artykuł został napisany na rok po pierwszej wersji mojej klasy TeePrintStream).
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class TeePrintStream extends PrintStream {
    /** Strumień początkowy/bezpośredni. */
    protected PrintStream parent;

    /** Nazwa pliku, jeśli jest znana;
     * Być może w przyszłości zostanie zastosowana do wyświetlania
     * komunikatów o błędach.
     */
    protected String fileName;

    /** Nazwa używana w sytuacjach, gdy nazwa pliku nie jest znana. */
    private static final String UNKNOWN_NAME = "(opened Stream)";

    /** Tworzymy obiekt TeePrintStream dysponując istniejącym 
     * obiektem PrintStream, otworzonym obiektem OutputStream,
     * oraz wartością logiczną określającą automatyczne opróżnianie
     * bufora.
     * To jest główny konstruktor, wszystkie inne korzystają z niego.
     */
    public TeePrintStream(PrintStream orig, OutputStream os, boolean flush)
    throws IOException {
        super(os, true);
        fileName = UNKNOWN_NAME;
        parent = orig;
    }

    /** Tworzymy obiekt TeePrintStream dysponując istniejącym 
     * obiektem PrintStream oraz otworzonym obiektem OutputStream.
     */
    public TeePrintStream(PrintStream orig, OutputStream os)
    throws IOException {
        this(orig, os, true);
    }

    /** Tworzymy obiekt TeePrintStream dysponując istniejącym 
     * obiektem PrintStream oraz nazwą pliku.
     */
    public TeePrintStream(PrintStream os, String fn) throws IOException {
        this(os, fn, true);
    }

    /** Tworzymy obiekt TeePrintStream, dysponując istniejącym 
     * obiektem Stream, nazwą pliku oraz wartością logiczną określającą 
     * automatyczne opróżnianie bufora.
     */
    public TeePrintStream(PrintStream orig, String fn, boolean flush)
    throws IOException {
        this(orig, new FileOutputStream(fn), flush);
        fileName = fn;
    }

    /** Zwraca true jeśli w którymś ze strumieni 
     * pojawiły się błędy
     */
    public boolean checkError() {
        return parent.checkError() || super.checkError();
    }

    /** Przesłonięta metoda write(). Faktyczna operacja powielania. */
    public void write(int x) {
        parent.write(x);    // "Zapisujemy w jednym miejscu
        super.write(x);        // i w innym."
    }

    /** Przesłonięta metoda write(). Faktyczna operacja powielania. */
    public void write(byte[] x, int o, int l) {
        parent.write(x, o, l);    // "Zapisujemy w jednym miejscu
        super.write(x, o, l);    // i w innym."
    }

    /** Zamykamy oba strumienie. */
    public void close() {
        parent.close();
        super.close();
    }

    /** Opróżniamy oba strumienie. */
    public void flush() {
        parent.flush();
        super.flush();
    }
}
// END main
