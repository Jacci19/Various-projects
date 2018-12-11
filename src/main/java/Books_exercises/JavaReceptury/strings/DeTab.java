package Books_exercises.JavaReceptury.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/** detab- zastępuje tabulacje odstępami.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class DeTab {
    Tabs ts;
    
    public static void main(String[] argv) throws IOException {
        DeTab dt = new DeTab(8);
        dt.detab(new BufferedReader(new InputStreamReader(System.in)),
                new PrintWriter(System.out));
    }

    public DeTab(int n) {
        ts = new Tabs(n);
    }
    public DeTab() {
        ts = new Tabs();
    }

    /** przetwarzamy plik (zastępując tabulacje znakami odstępu)
     * @param is - plik do przetworzenia
     * @param out - zaktualizowany plik
     */
    public void detab(BufferedReader is, PrintWriter out) throws IOException {
        String line;
        while ((line = is.readLine()) != null) {
            out.println(detabLine(line));
        }
    }
    
    /** przetwarzamy jeden wiersz (zastępując tabulacje znakami odstępu)
     * @param line - wiersz do przetworzenia
     * @return zaktualizowany wiersz tekstu
     */
    public String detabLine(String line) {
        char c;
        int col;
        StringBuffer sb = new StringBuffer();
        col = 0;
        for (int i = 0; i < line.length(); i++) {
            // Zwyczajny znak lub znak tabulacji.
            if ((c = line.charAt(i)) != '\t') {
                sb.append(c); // Zwyczajny znak.
                ++col;
                continue;
            }
            do { // Tabulacja, rozwijamy ją, generujemy >= odstęp
                sb.append(' ');
            } while (!ts.isTabStop(++col));
        }
        return sb.toString();
    }
}

// END main
