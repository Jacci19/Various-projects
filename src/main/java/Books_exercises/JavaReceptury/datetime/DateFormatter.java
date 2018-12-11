package Books_exercises.JavaReceptury.datetime;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// BEGIN main
public class DateFormatter {
    public static void main(String[] args) {
        
        // Formatowanie daty do postaci przypominającej ISO-8601, jednak
        // z użyciem zaków ukośnika a nie nie minusów.
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/LL/dd");
        System.out.println(df.format(LocalDate.now()));
        
        // Zmiana łańcucha znaków na datę przy użyciu tego samego 
        // formatu zapisu co wcześniej.
        System.out.println(LocalDate.parse("2014/04/01", df));
        
        // Data i czas sformatowane bez informacji o strefie czasowej.
        DateTimeFormatter nTZ =
            DateTimeFormatter.ofPattern("d MM, yyyy h:mm a");
        System.out.println(ZonedDateTime.now().format(nTZ));
    }
}
// END main
