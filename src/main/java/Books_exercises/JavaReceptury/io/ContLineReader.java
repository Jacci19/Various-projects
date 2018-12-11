package Books_exercises.JavaReceptury.io;

import java.io.*;

// BEGIN main
/** Klasa potomna klasy LineNumberReader, pozwalająca na odczytywanie
 * dzielonych wierszy tekstu przy użyciu metody readLine(). Inne metody,
 * takie jak readInt(), nie mogą być używane. Trzeba stworzyć 
 * klasy potomne, które podałyby konkretną definicję metody readLine().
 */
public abstract class ContLineReader extends LineNumberReader {
    /** Numer wiersza, pierwszego wiersza tekstu tworzącego bieżący wiersz 
     * (który może być kontynuowany w kolejnych wierszach).
     */
    protected int firstLineNumber = 0;
    /** True, jeśli obsługujemy wiersze dzielone, 
     * false, jeśli wierszy dzielonych nie obsługujemy; 
     * false == tryb "PRE" mode 
     */
    protected boolean doContinue = true;

    /** Określa tryb kontynuacji */
    public void setContinuationMode(boolean b) {
        doContinue = b;
    }

    /** Pobiera bieżący tryb pracy (kontynuacji) */
    public boolean getContinuationMode() {
        return doContinue;
    }

    /** Odczytuje jeden (być może podzielony) wiersz, usuwając znaki \
     * określające końce wszystkich (za wyjątkiem ostatniego) wierszy 
     * sekwencji.
     */
    public abstract String readLine() throws IOException;

    /** Odczytuje jeden prawdziwy wiersz tekstu. Metoda pomocnicza
     * przeznaczona do wykorzystania w klasach potomnych,
     * dzięki której nie będą musiały przejmować się wywoływaniem 
     * "super.readLine()", które nie jest szczególnie praktyczne...
     */
    public String readPhysicalLine() throws IOException {
        return super.readLine();
    }

    // Nie można przesłaniać metody getLineNumber, aby zwracała 
    // numer pierwszego wiersza, w jakim rozpoczynał się 
    // wiersz podzielony, gdyż wszystkie klasy potomne wywołują
    // metodę super.getLineNumber.
    
    /** Tworzymy obiekt ContLineReader o domyślnej wielkości
     * bufora wejściowego.
     */
    public ContLineReader(Reader in)  {
        super(in);
    }

    /** Tworzymy obiekt ContLineReader, podając wielkość
     * bufora wejściowego.
     */
    public ContLineReader(Reader in, int sz)  {
        super(in, sz);
    }

    // Metody które NIE działają - przekierowania do metod 
    // klasy bazowej

    /** Odczytuje jeden znak i zwraca go w formie liczby całkowitej (int). */
    public int read() throws IOException {
        return super.read();
    }

    /** Odczytuje znaki i zapisuje je we fragmencie bufora. */
    public int read(char[] cbuf, int off, int len) throws IOException {
        return super.read(cbuf, off, len);
    }

    public boolean markSupported() {
        return false;
    }
}
// END main
