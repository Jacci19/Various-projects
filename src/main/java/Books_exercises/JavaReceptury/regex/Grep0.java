package Books_exercises.JavaReceptury.regex;

import java.io.*;
import java.util.regex.*;

/** Grep0 - Dopasowuje wiersze ze standardowego strumienia wejściowego
 * ze wzorcem podanym w wierszu wywołania programu.
 */
// BEGIN main
public class Grep0 {
    public static void main(String[] args) throws IOException {
        BufferedReader is =
            new BufferedReader(new InputStreamReader(System.in));
        if (args.length != 1) {
            System.err.println("Sposób użycia: MatchLines wzorzec");
            System.exit(1);
        }
        Pattern patt = Pattern.compile(args[0]);
        Matcher matcher = patt.matcher("");
        String line = null;
        while ((line = is.readLine()) != null) {
            matcher.reset(line);
            if (matcher.find()) {
                System.out.println("DOPASOWANO: " + line);
            }
        }
    }
}
// END main
