package Books_exercises.JavaReceptury.starting;

// BEGIN main
import java.util.Date;

/** Demonstracja komunikatów o odrzuconych metodach. */
public class Deprec {

    public static void main(String[] av) {

        // Tworzymy obiekt Date odpowiadający dacie 5 maja 1986 r.
        // Należy się spodziewać komunikatów o odrzuconych metodach.
        Date d = new Date(86, 04, 05);    // TA INSTRUKCJA SPOWODUJE 
                                          // WYŚWIETLENIE KOMUNIKATU!
        System.out.println("Data: " + d);
    }
}
// END main
