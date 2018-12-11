package Books_exercises.JavaReceptury.datetime;

// BEGIN main
import java.time.LocalDate;
import java.time.Period;

/** DateAdd -- program wylicza różnicę między dwiem datami
 * (np. jaka będzie data za 700 dni).
 */
public class DateAdd {
    public static void main(String[] av) {
        /** Aktualna data. */
        LocalDate now =  LocalDate.now();

        Period p = Period.ofDays(700);
        LocalDate then = now.plus(p);

        System.out.printf("Za 700 dni od %s będzie %s%n", now, then);
    }
}
// END main
