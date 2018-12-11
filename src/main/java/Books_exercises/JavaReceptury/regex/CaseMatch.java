package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/**
 * Uwzględnianie, bądź ignorowanie wielkości liter w wyrażeniach regularnych.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class CaseMatch {
    public static void main(String[] argv) {
        String pattern = "^q[^u]\\d+\\.";
        String input = "QA777. To będzie następny lot.";

        Pattern reCaseInsens = Pattern.compile(pattern, 
                        Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
        Pattern reCaseSens = Pattern.compile(pattern);

        boolean found;
        Matcher m;
        m = reCaseInsens.matcher(input); // Dopasowanie bez uwzględniania wielkości liter. 
        found = m.lookingAt();              // Wielkość liter nieważna.
        System.out.println("Wynik bez użycia flagi IGNORE_CASE: " + found);

        m = reCaseSens.matcher(input);  // Tu nie używamy falgi.
        found = m.lookingAt();      // Wielkość liter będzie uwzględniana.
        System.out.println("Wynik z użyciem flagi MATCH_NORMAL: " + found);

    }
}