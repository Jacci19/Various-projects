package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;
import java.io.*;

/** Demonstracja interfejsu CharacterIterator: program 
 * wyświetla wszystkie łańcuchy znaków pochodzące z pliku, 
 * które pasują do podanego wzorca.
 */
// BEGIN main
public class ReaderIter {
    public static void main(String[] args) throws IOException {
        // Wzorzec
        Pattern patt = Pattern.compile("[A-Za-z][a-z]+");
        // FileReader (patrz rozdział o operacjach wejścia-wyjścia - I/O)
        BufferedReader r = new BufferedReader(new FileReader(args[0]));
        
        // Dla każdego odnalezionego fragmentu pasującego do wzorca:
        // pobieramy go i wyświetlamy
        String line;
        while ((line = r.readLine()) != null) {
            // Dla każdego dopasowanego wiersza, pobieramy go 
            // i wyświetlamy.
            Matcher m = patt.matcher(line);
            while (m.find()) {
                // Najprostszy sposób:
                // System.out.println(m.group(0));

                // Pobieramy indeks początku tekstu.
                int start = m.start(0);
                // Pobieramy indeks końca tekstu.
                int end = m.end(0);
                // Wyświetlamy dopasowany fragment tekstu
                // używając metody CharacterIterator.substring(offset, end);
                System.out.println(line.substring(start, end));
            }
        }
    }
}
// END main
