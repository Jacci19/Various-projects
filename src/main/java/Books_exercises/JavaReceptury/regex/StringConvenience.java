package Books_exercises.JavaReceptury.regex;

/**
 * StringConvenience -- prezentacja metody pomocniczej matches 
 * klasy java.lang.String.
 * @author Ian F. Darwin
 */
// BEGIN main
public class StringConvenience {
    public static void main(String[] argv) {

        String pattern = ".*Q[^u]\\d+\\..*";
        String line = "Zamów QT300. Już dziś!";
        if (line.matches(pattern)) {
            System.out.println(line + " pasuje do \"" + pattern + "\"");
        } else {
            System.out.println("Nie pasuje!!");
        }
    }
}
// END main
