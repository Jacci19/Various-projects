package Books_exercises.JavaReceptury.numbers;

/**
 * Konwersje pomiędzy różnymi systemami liczbowymi.
 * 
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class IntegerBinOctHexEtc {
    public static void main(String[] argv) {
        // BEGIN main
        String input = "101010";
        for (int radix : new int[] { 2, 8, 10, 16, 36 }) {
            System.out.println("Liczba " + input 
                  + " w systemie o podstawie " + radix + " ma wartość " 
                  + Integer.valueOf(input, radix) + "; ");
            int i = 42;
            System.out.println("Liczba " + i + " sformatowana w systemie" 
                  + " o podstawie " + radix + " ma wartość "
                  + Integer.toString(i, radix));
        }
        // END main
    }
}
