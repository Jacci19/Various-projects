package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/**
 * Prezentacja odnajdywania znaków końca wiersza przy użyciu 
 * klasy RE.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class NLMatch {
    public static void main(String[] argv) {

        String input = "Marzę o nowych silnikach\nwięcej silników dla każdego";
        System.out.println("DANE WEJŚCIOWE: " + input);
        System.out.println();

        String[] patt = {
            "silnikach\nwięcej silników",
            "silnikach$"
        };

        for (int i = 0; i < patt.length; i++) {
            System.out.println("WZORZEC " + patt[i]);

            boolean found;
            Pattern p1l = Pattern.compile(patt[i]);
            found = p1l.matcher(input).find();
            System.out.println("Flaga DEFAULT - dopasowano: " + found);

            Pattern pml = Pattern.compile(patt[i], 
                Pattern.DOTALL|Pattern.MULTILINE);
            found = pml.matcher(input).find();
            System.out.println("Flaga MULTILINE - dopasowano: " + found);
            System.out.println();
        }
    }
}
// END main
