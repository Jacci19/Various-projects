package Books_exercises.JavaReceptury.strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import com.darwinsys.util.Debug;

/**
 * EnTab: zastępuje odstępy tabulacjami i odstępami.
 * Przerobione na podstawie książki K&R Software Tools na język C,
 * a po latach ponownie przerobione na język Java. Ponownie 
 * przerobiona w celu analizy kolejnych wierszy, a nie znak po znaku.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class EnTab {
    /** Obiekt Tabs (obsługa tabulacji) */
    protected Tabs tabs;

    /**
     * Przekazanie informacji o odstępach.
     */
    public int getTabSpacing() {
        return tabs.getTabSpacing();
    }

    /**
     * Program główny: tworzymy obiekt EnTab i przekazujemy do 
     * niego dane ze standardowego wejścia lub podanego pliku (bądź
     * kilku plików).
     */
    public static void main(String[] argv) throws IOException {
        EnTab et = new EnTab(8);
        if (argv.length == 0) // Obsługa standardowego wejścia.
            et.entab(
                new BufferedReader(new InputStreamReader(System.in)),
                System.out);
        else
            for (String fileName : argv) { // Obsługa każdego pliku.
                et.entab(
                    new BufferedReader(new FileReader(fileName)),
                    System.out);
            }
    }

    /**
     * Konstruktor: zapisujemy wartości tabulacji.
     * 
     * @param n
     *            Ilość odstępów zamienianych na jeden znak tabulacji.
     */
    public EnTab(int n) {
        tabs = new Tabs(n);
    }

    public EnTab() {
        tabs = new Tabs();
    }

    /**
     * entab: przetwarza cały jeden plik, zamieniając odstępy na 
     *  znaki tabulacji.
     * 
     * @param is Obiekt BufferedReader służący do odczytywania 
     *           zawartości pliku.
     * @param out Obiekt PrintWriter, do którego są wysyłane dane wynikowe.
     */
    public void entab(BufferedReader is, PrintWriter out) throws IOException {
        String line;

        // pętla główna: przetwarzamy cały plik, znak po znaku.
        while ((line = is.readLine()) != null) {
            out.println(entabLine(line));
        }
    }
    /**
     * entab: przetwarza cały jeden plik, zamieniając odstępy na 
     *  znaki tabulacji.
     * 
     * @param is Obiekt BufferedReader operujący na pliku do odczytu.
     * @param out Obiekt PrintWriter do którego są wysyłane dane wynikowe.
     */
    public void entab(BufferedReader is, PrintStream out) throws IOException {
        entab(is, new PrintWriter(out));
    }

    /**
     * entabLine: przetwarza jeden wiersz tekstu, zamieniając odstępy 
     * na znaki tabulacji.
     * 
     * @param line -
     *            przetwarzany łańcuch znaków.
     */
    public String entabLine(String line) {
        int N = line.length(), outCol = 0;
        StringBuffer sb = new StringBuffer();
        char ch;
        int consumedSpaces = 0;
        
        for (int inCol = 0; inCol < N; inCol++) {
            ch = line.charAt(inCol);
            // Jeśli odczytaliśmy znak odstępu, to go uwzględniamy,
            // ale nie wyświetlamy.
            // Jeśli dotarliśmy do punktu tabulacji, wyświetlamy 
            // znak tabulacji.
            if (ch == ' ') {
                Debug.println("space", "Odszukany odstęp w położeniu " + inCol);
                if (!tabs.isTabStop(inCol)) {
                    consumedSpaces++;
                } else {
                    Debug.println("tab", "Położenie tabulacji " + inCol);
                    sb.append('\t');
                    outCol += consumedSpaces;
                    consumedSpaces = 0;
                }
                continue;
            }

            // Jeśli właśnie przekroczyliśmy położenie tabulacji, 
            // musimy pozostawić jeden znak odstępu, gdyż pobraliśmy
            // go wcześniej.
            while (inCol-1 > outCol) {
                Debug.println("pad", "Pozostawiony odstęp w położeniu " + inCol);
                sb.append(' ');
                outCol++;
            }

            // Teraz mamy do wyświetlenia zwyczajny znak.
            sb.append(ch);
            outCol++;

        }
        // Jeśli wiersz zakończył się znakiem odstępu (lub zawierał wyłącznie 
        // odstępy!) to je zachowujemy.
        for (int i = 0; i < consumedSpaces; i++) {
            Debug.println("trail", "Wypełniamy koniec odstępami, nr " + i);
            sb.append(' ');
        }
        return sb.toString();
    }
}
// END main
