package Books_exercises.JavaReceptury.numbers;

/** Porównanie wartości 2/3 liczby 5 */
// BEGIN main
public class FractMult {
    public static void main(String[] u) {

        double d1 = 0.666 * 5;    // Szybkie, lecz nieczytelne i niedokładne: 
                                  // konwersja.
        System.out.println(d1);   // 2/3 na 0.666 wykonana przez programistę.
         
        double d2 = 2/3 * 5;      // Zła odpowiedź - 2/3 == 0, 0*5.0 = 0.0.
        System.out.println(d2);
         
        double d3 = 2d/3d * 5;    // "Normalne" rozwiązanie.
        System.out.println(d3);
         
        double d4 = (2*5)/3d;     // Jedna operacja na liczbach całkowitych, 
                                  // wynik prawie taki sam.
        System.out.println(d4);
         
        int i5 = 2*5/3;           // Rozwiązanie szybkie, uzyskujemy 
                                  // przybliżoną wartość całkowitą.
        System.out.println(i5);
    }
}
// END main
