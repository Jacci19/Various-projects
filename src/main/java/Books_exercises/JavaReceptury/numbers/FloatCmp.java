package Books_exercises.JavaReceptury.numbers;

/**
 * Porównywanie liczb zmiennoprzecinkowych.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class FloatCmp {
    
    final static double EPSILON = 0.0000001;
    
    public static void main(String[] argv) {
        double da = 3 * .3333333333;
        double db = 0.99999992857;

        // Porównanie dwóch liczb, które są prawie równe.
        if (da == db) {
            System.out.println("Interpreter Javy uważa, że " + da + "==" + db);
        // Teraz porównujemy je przy użyciu naszej metody.
        } else if (equals(da, db, 0.0000001)) {
            System.out.println("Równe z tolerancją do " + EPSILON);
        } else {
            System.out.println(da + " != " + db);
        }
        
        System.out.println("Wartość NaN jest prezentowana jako " + Double.NaN);

        // Poniższy przykład pokazuje, że porównywanie dwóch wartości NaN
        // nie jest dobrym pomysłem:
        double nan1 = Double.NaN;
        double nan2 = Double.NaN;
        if (nan1 == nan2)
            System.out.println("Porównanie dwóch wartości NaN nieprawidłowo zwraca wartość true.");
        else
            System.out.println("Porównanie dwóch wartości NaN prawidłowo zwraca wartość false.");

        if (new Double(nan1).equals(new Double(nan2)))
            System.out.println("Double(NaN).equals(NaN) prawidłowo zwraca true.");
        else
            System.out.println("Double(NaN).equals(NaN) nieprawidłowo zwraca false.");
    }

    /** Porównanie dwóch liczb typu double z zadaną tolerancją. */
    public static boolean equals(double a, double b, double eps) {
        if (a==b) return true;
        // Jeśli różnica liczb jest mniejsza od tolerancji, 
        // liczby uważamy za równe.
        return Math.abs(a - b) < eps;
    }

    /** Porównanie dwóch liczb typu double z domyślną tolerancją. */
    public static boolean equals(double a, double b) {
        return equals(a, b, EPSILON);
    }
}
// END main
