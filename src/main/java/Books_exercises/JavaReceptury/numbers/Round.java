package Books_exercises.JavaReceptury.numbers;

/**
 * Prezentacja niestandardowej wersji metody round().
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @author Alex Stangl - simplified the algorithm
 */
// BEGIN main
public class Round {
   /** Zaokrąglamy liczbę w górę, jeśli jej część ułamkowa
    * jest większa od podanego ułamka.
    */
    public static final double THRESHOLD = 0.54;

    /* 
     * Zaokrąglanie wartości zmiennoprzecinkowych do całkowitych.
     * @return zwracamy liczbę int najbliższą przekazanemu argumentowi.
     * @param d Liczba dodatnia do zaokrąglenia.
     */
    public static int round(double d) {
        return (int)Math.floor(d + 1.0 - THRESHOLD);
    }
    
    public static void main(String[] argv) {
        for (double d = 0.1; d<=1.0; d+=0.05) {
            System.out.println("Nasz sposób:  " + d + "-> " + round(d));
            System.out.println("Sposób klasy Math:" + d + "-> " + Math.round(d));
        }
    }
}
// END main
