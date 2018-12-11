package Books_exercises.JavaReceptury.numbers;

/**
 * IntObject -- konwertuje wartości int na obiekty typu Integer (wymaga Javy 1.5)
 */
// BEGIN main
public class IntObject {
    public static void main(String[] args) {
        // int na Integer
        Integer i1 = Integer.valueOf(42);
        System.out.println(i1.toString());        // lub po prostu i1
        
        // Integer na int
        int i2 = i1.intValue();
        System.out.println(i2);
    }
}
// END main
