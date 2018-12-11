package Books_exercises.JavaReceptury.numbers;

import java.text.NumberFormat;
import java.text.ParseException;

/*
 * Konwersja łańcucha na liczbę przy użyciu klasy NumberFormat.
 */
// BEGIN main 
public class NumFormatParse {
    /** Konwertowana liczba zapisana jako łańcuch znaków. */
    public static final String input = "4096.251";

    public static void main(String[] av) { 

        NumberFormat defForm = NumberFormat.getInstance();

        try {
            Number d = defForm.parse(input);
            System.out.println(input + 
                " przeanalizowano jako " + d +
                " i sformatowano do postaci " + defForm.format(d));
        } catch (ParseException pe) {
            System.err.println(input + "Nie można przeanalizować!");
        }
    }
// END main
}
