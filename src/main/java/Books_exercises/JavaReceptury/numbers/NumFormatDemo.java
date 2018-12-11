package Books_exercises.JavaReceptury.numbers;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
 * Formatowanie liczby przy użyciu dwóch sposobów: naszego i domyślnego.
 */
public class NumFormatDemo {
    // BEGIN part1
    /** Formatowana liczba. */
    public static final double intlNumber = 1024.25;
    /** Druga formatowana liczba. */
    public static final double ourNumber = 100.2345678;
    // END part1

    /** Główna (main) i jedyna metoda tej klasy. */
    public static void main(String[] av) { 

        // BEGIN part2
        NumberFormat defForm = NumberFormat.getInstance();
        NumberFormat ourForm = new DecimalFormat("##0.##");
        // Metoda toPattern() zwraca kombinację znaków specjalnych 
        // #0. i tak dalej, wykorzystywaną przez ustawienia 
        // lokalne jako wzorzec do formatowania liczb 
        System.out.println("Wzorzec domyślny " +
            ((DecimalFormat)defForm).toPattern());
        System.out.println("Liczba " + intlNumber + " zostaje sformatowana do  postaci " +
            defForm.format(intlNumber));
        System.out.println("Liczba " + ourNumber + " zostaje sformatowana do  postaci " +
            ourForm.format(ourNumber));
        System.out.println("Liczba " + ourNumber + " zostaje sformatowana do  postaci " +
            defForm.format(ourNumber) + " using the default format");
        // END part2
    }
}
