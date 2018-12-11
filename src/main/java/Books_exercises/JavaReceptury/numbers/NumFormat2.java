package Books_exercises.JavaReceptury.numbers;

import java.text.NumberFormat;

/*
 * Formatowanie liczby przy użyciu dwóch sposobów: naszego i domyślnego.
 */
// BEGIN main
public class NumFormat2 {
    /** Formatowane liczby. */
    public static final double data[] = {
        0, 1, 22d/7, 100.2345678
    };

    /** Główna (main) i jedyna metoda tej klasy. */
    public static void main(String[] av) { 
        // Pobieramy interfejs formatujący.
        NumberFormat form = NumberFormat.getInstance();

        // Definiujemy format o postaci 999.99[99].
        form.setMinimumIntegerDigits(3);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(4);

        // Teraz formatujemy liczby w zdefiniowany sposób.
        for (int i=0; i<data.length; i++)
            System.out.println("Liczba " + data[i] + "\tzostaje sformatowana do postaci" +
                form.format(data[i]));
    }
}
// END main
 
