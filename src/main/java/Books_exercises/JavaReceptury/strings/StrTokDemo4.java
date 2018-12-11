package Books_exercises.JavaReceptury.strings;

import java.util.StringTokenizer;

/** Prezentacja wykorzystania klasy StringTokenizer wraz z mechanizmem 
 *  odrzucania separatorów.
 */
// BEGIN main
public class StrTokDemo4 {
    public final static int MAXFIELDS = 5;
    public final static String DELIM = "|";

    /** Przetwarza łańcuch znaków, zwracając go w formie tablicy łańcuchów. */
    public static String[] process(String line) {
        String[] results = new String[MAXFIELDS];

        // Jeśli klasa StringTokenizer będzie pomijać wszelkie leksemy,
        // to powtarzające się puste leksemy będą ignorowane.
        StringTokenizer st = new StringTokenizer(line, DELIM, true);

        int i = 0;
     // Wstawiamy każdy leksem do bieżącej komórki tablicy.
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s.equals(DELIM)) {
                if (i++>=MAXFIELDS)
                    // To nie jest najprostsze rozwiązanie - patrz przykład
                    // StrTokDemo4b wykorzystujący klasę List.
                    throw new IllegalArgumentException("W wierszu wywołania " +
                        line + " podano zbyt wiele argumentów.");
                continue;
            }
            results[i] = s;
        }
        return results;
    }

    public static void printResults(String input, String[] outputs) {
       System.out.println("Dane wejściowe: " + input);
       for (int i=0; i<outputs.length; i++)
           System.out.println("Wynik " + i + " to: " + outputs[i]);
    }

    // Tu powinien się znaleźć test JUnit, ale przedstawię go dopiero
    // w dalszej części książki, zatem dopiero później będę mógł
    // przenieść ten kod do katalogu "tests".
    public static void main(String[] a) {
        printResults("A|B|C|D", process("A|B|C|D"));
        printResults("A||C|D", process("A||C|D"));
        printResults("A|||D|E", process("A|||D|E"));
    }
}
// END main
