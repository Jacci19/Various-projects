package Books_exercises.JavaReceptury.numbers;

public class StringToDouble {

    // BEGIN main
    public static void main(String[] argv) {
        String aNumber = argv[0];    // A nie argv[1]
        double result;
        try {
            result = Double.parseDouble(aNumber); 
            System.out.println("Nieprawidłowa liczba: " + result);
        } catch(NumberFormatException exc) {
            System.out.println("Wartość liczby to: " + aNumber);
            return;
        }
    }
    // END main
}
