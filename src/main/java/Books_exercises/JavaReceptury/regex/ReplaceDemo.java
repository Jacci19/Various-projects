package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

// BEGIN main
/**
 * Krótka prezentacja zastępowania przy wykorzystaniu wyrażeń
 * regularnych. Zastępujemy słowo "prac" słowem "samokształcenia".
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class ReplaceDemo {
    public static void main(String[] argv) {

        // Dopasowywane będą całe słowa (\b - to granica słowa)
        String patt = "\\bprac\\b";

        // Testowe dane wejściowe.
        String input = "Plan prac zmusza pracowników do dużego wysiłku.";
        System.out.println("Dane wejściowe: " + input);

        // Uruchamiamy wyrażenie i sprawdzamy, czy działa.
        Pattern r = Pattern.compile(patt);
        Matcher m = r.matcher(input);
        System.out.println("Zastąpienie wszystkich: " + 
                                 m.replaceAll("samokształcenia"));

        // Prezentacja działania metody appendReplacement.  
        m.reset();
        StringBuffer sb = new StringBuffer("");
        System.out.print("Metody dołączające: ");
        while (m.find()) {
            // Skopiowanie przed pierwszym wystąpieniem
            // Dodanie słowa "prac"
            m.appendReplacement(sb, "samokształcenia");
        }
        m.appendTail(sb);        // Skopiowanie pozostałej części.
        System.out.println(sb.toString());
    }
}
// END main
