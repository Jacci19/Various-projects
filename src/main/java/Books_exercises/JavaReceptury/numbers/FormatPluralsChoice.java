package Books_exercises.JavaReceptury.numbers;

import java.text.*;

/**
 * Poprawne formatowanie rzeczowników w liczbie mnogiej,
 * przy wykorzystaniu klasy ChoiceFormat (przykład bardzo uproszczony).
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class FormatPluralsChoice extends FormatPlurals {
    
    // Obiekt ChoiceFormat zawierający różne formy słów
    static double[] limits = { 0, 1, 2, 5 };
    static String[] formats = { "elementów", "element", "elementy", "elementów"};
    static ChoiceFormat pluralizedFormat = new ChoiceFormat(limits, formats);

    // Obiekt ChoiceFormat zawierający różne formy słów
    static ChoiceFormat quantizedFormat = new ChoiceFormat(
        "0#0 elementów|1#jeden element|1<wiele elementów");

    // Dane testowe
    static int[] data = { -1, 0, 1, 2, 3 };

    public static void main(String[] argv) {
        for (int i : data) {
            int t = i % 10; 
            t = (t > 1 && t < 5) ? t : i;
            if (i >= 12 && i <= 14) t = 0;
            System.out.println("Znaleziono " + i + " " + pluralizedFormat.format(t));
        }
        
        System.out.println("Wersja druga:");
        for (int i : data) {
            System.out.println("Znaleziono " + quantizedFormat.format(i));
        }
        //*/
    }
    
}
// END main
