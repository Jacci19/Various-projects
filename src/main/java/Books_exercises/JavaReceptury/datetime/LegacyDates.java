package Books_exercises.JavaReceptury.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

// BEGIN main
public class LegacyDates {
    public static void main(String[] args) {

        // Konwersja w obu kierunkach przy użyciu obiektu Date.
        Date legacyDate = new Date();
        System.out.println(legacyDate);
        
        LocalDateTime newDate = 
            LocalDateTime.ofInstant(legacyDate.toInstant(), 
            ZoneId.systemDefault());
        System.out.println(newDate);
        
        // A teraz przy użyciu obiektu Calendar.
        Calendar c = Calendar.getInstance();
        System.out.println(c);
        LocalDateTime newCal = 
            LocalDateTime.ofInstant(c.toInstant(),
            ZoneId.systemDefault());
        System.out.println(newCal);
    }
}
// END main
