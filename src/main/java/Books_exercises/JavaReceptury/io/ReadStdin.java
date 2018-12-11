package Books_exercises.JavaReceptury.io;

/**
 * Odczytuje jeden bajt ze standardowego strumienia wejściowego.
 * Sam w sobie program jest raczej mało użyteczny.
 * @author    Ian F. Darwin, http://www.darwinsys.com/
 */
public class ReadStdin {
    /** Prosty test działania. */
    public static void main(String[] ap) {
        // BEGIN main
        int b = 0;
        try {
            b = System.in.read();
            System.out.println("Odczytane dane: " + (char)b);
        } catch (Exception e) {
            System.out.println("Przechwycono wyjątek " + e);
        }
        // END main
    }
}
