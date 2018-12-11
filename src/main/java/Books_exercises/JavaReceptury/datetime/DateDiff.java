package Books_exercises.JavaReceptury.datetime;

// BEGIN main
import java.time.LocalDate;
import java.time.Period;

public class DateDiff {

    public static void main(String[] args) {
        /** Data ostatniego dnia ubiegłego wieku. */
        LocalDate endofCentury = LocalDate.of(2000, 12, 31);
        LocalDate now = LocalDate.now();
        
        Period diff = Period.between(endofCentury, now);
        
        System.out.printf("XXI wiek (do dziś, czyli %s) liczy już sobie %s lat.%n", now, diff);
        System.out.printf(
                "XXI wiek ma już %d lat, %d miesięcy i %d dni.",
                diff.getYears(), diff.getMonths(), diff.getDays());
    }
}
// END main
