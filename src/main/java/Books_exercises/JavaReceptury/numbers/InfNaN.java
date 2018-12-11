package Books_exercises.JavaReceptury.numbers;

/**
 * Prezentacja INFINITY oraz NaN
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class InfNaN {
    // BEGIN main
    public static void main(String[] argv) {
        double d = 123;
        double e = 0;
        if (d/e == Double.POSITIVE_INFINITY)
            System.out.println("Sprawdzamy, czy działa POSITIVE_INFINITY "); 
        double s = Math.sqrt(-1);
        if (s == Double.NaN)
            System.out.println("Porównanie z wartością NaN błędnie zwraca true"); 
        if (Double.isNaN(s))
            System.out.println("Double.isNaN() poprawnie zwraca true");
    }
    // END main
}
