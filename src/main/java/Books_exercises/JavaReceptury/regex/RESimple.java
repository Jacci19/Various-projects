package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/**
 * Prosty przykład wykorzystania wyrażeń regularnych.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class RESimple {
    public static void main(String[] argv) {
        String pattern = "^K[^w]\\d+\\.";
        String[] input = {
            "KA777. Jesteście o czasie. Możrzecie lądować.",
            "Kwa, kwa, kwa!"
        };

        Pattern p = Pattern.compile(pattern);

        for (String in : input) {
            boolean found = p.matcher(in).lookingAt();

            System.out.println("'" + pattern + "'" +
                  (found ? " pasuje " : " nie pasuje ") + input);
        }
    }
}
// END main
