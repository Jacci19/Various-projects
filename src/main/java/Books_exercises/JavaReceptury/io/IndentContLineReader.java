package Books_exercises.JavaReceptury.io;

import java.io.*;

/** Klasa potomna klasy ContLineReader odczytująca wiersze łączone 
 * dzięki zastosowaniu odpowiednich wcięć na początku kolejnych 
 * wierszy tekstu (jak w poczcie elektronicznej (RFC822), wiadomościach 
 * Usenet News, i tak dalej).
 * Zazwyczaj nagłówki i treść wiadomości będą odczytywane przy 
 * zastosowaniu poniższego fragmentu kodu:

 * <pre>
 * while ((headerLine = clr.readLine()) != null && headerLine.length() > 0) {
 *    processHeaderLine(headerLine);
 * }
 * clr.setContinuationMode(false);
 * while ((bodyLine = clr.readLine()) != null) {
 *    processBodyLine(bodyLine);
 * }
 * </pre>
 */
// BEGIN main
public class IndentContLineReader extends ContLineReader {
    /** Numer pierwszego wiersza w bieżącym (być może
     * podzielonym) wierszu tekstu
     */
    public int getLineNumber() {
        return firstLineNumber;
    }

    protected String prevLine;

    /** Odczytujemy jeden wiersz tekstu (który być może będzie
     * dalej kontynuowany). Usuwamy '\' umieszczany na końcu 
     * wszystkich łączonych wierszy za wyjątkiem ostatniego.
     */
    public String readLine() throws IOException {
        String s;

        // Jeśli poprzedni wiersz został zapisany to zaczynamy od niego.
        // W przeciwnym przypadku odczytujemy pierwszy wiersz, który 
        // być może będzie następnie kontynuowany.
        // Jeśli wiersz nie jest pusty, zapisujemy go w StringBuffer
        // a jego numer w zmiennej firstLineNumber.

        if (prevLine != null) {
            s = prevLine;
            prevLine = null;
        }
        else  {
            s = readPhysicalLine();
        }

        // Zapisujemy numer pierwszego wiersza.
        firstLineNumber = super.getLineNumber();

        // Teraz mamy już jeden wiersz. Jeśli nie pracujemy w trybie 
        // kontynuacji (łączenia) lub jeśli metoda readPhysicalLine()
        // zwróciła wartość null, to kończymy, czyli zwracamy wiersz.

        if (!doContinue || s == null)
            return s;

        // W przeciwnym przypadku tworzymy StringBuffer
        StringBuffer sb = new StringBuffer(s);

        // Odczytujemy wszystkie dostępne kontynuowane wiersze,
        // jeśli takie w ogóle są.
        while (true) {
            String nextPart = readPhysicalLine();
            if (nextPart == null) {
                // Ups!! EOF w kontynuowanym wierszu.
                // Zwracamy to co do tej pory udało się nam odczytać.
                return sb.toString();
            }
            // Jeśli kolejny wiersz zaczyna się od odstępu, to znaczy
            // że stanowi kontynuację.
            if (nextPart.length() > 0 &&
                Character.isWhitespace(nextPart.charAt(0))) {
                sb.append(nextPart);    // Dodajemy wiersz.
            } else {
                // Odczytaliśmy zbyt dużo, odtwarzamy
                prevLine = nextPart;
                break;
            }
        }

        return sb.toString();        // Zwracamy to, co pozostało.
    }
// END main

    /** Tworzy IndentContLineReader używając domyślnej wielkości bufora. */
    public IndentContLineReader(Reader in)  {
        super(in);
    }

    /** Tworzy IndentContLineReader używając bufora o zadanej wielkości. */
    public IndentContLineReader(Reader in, int sz)  {
        super(in, sz);
    }
}
