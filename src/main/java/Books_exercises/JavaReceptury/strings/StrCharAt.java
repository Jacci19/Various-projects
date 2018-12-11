package Books_exercises.JavaReceptury.strings;

/** StrCharAt - prezentacja działania metody String.charAt()
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class StrCharAt {
    public static void main(String[] av) {
        String a = "Zwinny brązowy list przeskoczył nad leniwym cielakiem.";
        for (int i=0; i < a.length(); i++) // Nie używamy nowej pętli for
            System.out.println("Znak " + i + " to " + a.charAt(i));     
    }
}
// END main
