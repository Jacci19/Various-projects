package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/**
 * REmatch -- demonstracja wykorzystania metody group().
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class REmatch {
    public static void main(String[] argv) {

        String patt = "Q[^u]\\d+\\.";
        Pattern r = Pattern.compile(patt);
        String line = "Zamów QT300. Już dziś!";
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println(patt + " pasuje do \"" +
                m.group(0) +
                "\" w \"" + line + "\"");
        } else {
            System.out.println("Nie pasuje!!");
        }
    }
}
// END main
