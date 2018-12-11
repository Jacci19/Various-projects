package Books_exercises.JavaReceptury.jaxwsclient;

// BEGIN main
// Nie ma żadnych instrukcji import!

/** Pełny, kompletny kod klienta usługi Calc, który 
 * mieści się w dwóch wierszach. */
public class TinyClientMain {
    public static void main(String[] args) {
        Calc client = new CalcService().getCalcPort();
        System.out.println(client.add(2, 2));
    }
}
// END main
