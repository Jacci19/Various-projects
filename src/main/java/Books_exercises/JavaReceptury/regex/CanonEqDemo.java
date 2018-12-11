package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/**
 * CanonEqDemo - przedstawia wykorzystanie flagi Pattern.CANON_EQ, pokazując 
 * efekty porównania hiszpańskiego odpowiednika słowa "równy" zapisanego 
 * na kilka sposobów i porównywanego przez mechanizm obsługi wyrażeń regularnych.
 */
// BEGIN main
public class CanonEqDemo {
    public static void main(String[] args) {
        String pattStr = "\u00e9gal"; // egal
        String[] input = {
                "\u00e9gal", // egal - najlepsze dopasowanie :-)
                "e\u0301gal", // e + "dołączany ostry akcent"
                "e\u02cagal", // e + "znak modyfikatora ostrego akcentu"
                "e'gal", // e + apostrof
                "e\u00b4gal", // e + "ostry akcent" kodu Latin-1 
        };
        Pattern pattern = Pattern.compile(pattStr, Pattern.CANON_EQ);
        for (int i = 0; i < input.length; i++) {
            if (pattern.matcher(input[i]).matches()) {
                System.out.println(
                    pattStr + " dopasowano do " + input[i]);
            } else {
                System.out.println(
                    pattStr + " nie dopasowano do " + input[i]);
            }
        }
    }
}
// END main
