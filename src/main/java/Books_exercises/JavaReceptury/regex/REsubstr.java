package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/**
 * REsubstr -- Przedstawienia działania metody String.substring()
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class REsubstr {
    public static void main(String[] argv) {
        // BEGIN main
        String patt = "Q[^u]\\d+\\.";
        Pattern r = Pattern.compile(patt);
        String line = "Zamów QT300. Już dziś!";
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println(patt + " pasuje do \"" +
                line.substring(m.start(0), m.end(0)) +
                "\" w \"" + line + "\"");
        } else {
            System.out.println("Nie pasuje!!");
        }
        // END main
    }
}
