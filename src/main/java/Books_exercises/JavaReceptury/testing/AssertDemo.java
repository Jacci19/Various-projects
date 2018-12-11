package Books_exercises.JavaReceptury.testing;

/**
 * Demonstrate the new-in-1.4 "assert" facility.
 * Prezentacja mechanizmu asercji dodanego do Javy 1.4.
 * <p>
 * Asercje nie są domyślnie włączane, trzeba je włączć samemu
 * używając odpowiedniego parametru w wywołaniu kompilatora java.
 * Oto przykład: "java -enableassertions|-ea ...".
 * 
 * @since 1.4
 */
// BEGIN main
public class AssertDemo {
    public static void main(String[] args) {
        int i = 4;
        if (args.length == 1) {
            i = Integer.parseInt(args[0]);
        }
        assert i > 0 : "i nie jest liczbą dodatnią";
        System.out.println("Witamy po asercji.");
    }
}
// END main
