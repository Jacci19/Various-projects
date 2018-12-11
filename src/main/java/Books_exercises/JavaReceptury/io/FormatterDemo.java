package Books_exercises.JavaReceptury.io;

import java.util.Formatter;

/** Przedstawia wzorce wykorzystania i przykłady kodów formatujących
 * stosowanych przez klasę Formatter oraz przez metody printf()
 * klas PrintStream i PrintWritter wprowadzone ponownie w Java 5. 
 */
// BEGIN main
public class FormatterDemo {
    public static void main(String[] args) {

        // Argumenty wszystkich trzech wywołań metody format()
        // składają się z następujących elementów:
        // formatującego łańcucha znaków oraz jednego lub większej 
        // liczby argumentów.
        // Każdy kod formatujący składa się z:
        // % - znaku rozpoczynającego kod,
        // N$ - numeru parametru (liczonego od 1) - OPCJONALNIE,
        // N - długości pola,
        // L - litery określającej format (d: liczba całkowita (int); 
        //         f: liczba zmiennoprzecinkowa; s: format ogólny; 
        //         i wiele innych),
        // Pełen opis kodów formatujących można znaleźć w dokumentacji
        // klasy java.util.Formatter.

        // Najbardziej ogólna (i skomplikowana) postać formatowania.
        Formatter fmtr = new Formatter();
        Object result = fmtr.format("%1$04d - jest rokiem %2$f", 1956, Math.PI);
        System.out.println(result);

        // Krótszy sposób, korzystający ze statycznej metody String.format()
        // oraz domyślnej kolejności parametrów.
        Object stringResult = String.format("%04d - jest rokiem %f", 1956, Math.PI);
        System.out.println(stringResult);

        // Uproszczony sposób wywoływania metody format klas PrintStream
        // oraz PrintWriter, bardziej zbliżony do analogicznych metod 
        // dostępnych w innych językach programowania. Wymusza jednak 
        // stosowanie znaku nowego wiersza (%n - nie należy używać 
        // sekwencji \n, gdyż jest ona zależna od używanej platformy 
        // systemowej!).
        System.out.printf("%04d - jest rokiem %f%n", 1956, Math.PI);

        // Bardziej precyzyjne formatowanie wartości zmiennoprzecinkowych.
        System.out.printf("Przybliżona wartość PI wynosi %4.2f%n", Math.PI);
    }
}
// END main
