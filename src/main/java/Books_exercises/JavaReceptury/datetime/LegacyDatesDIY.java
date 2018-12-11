package Books_exercises.JavaReceptury.datetime;

import java.text.Format;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

// BEGIN main
public class LegacyDatesDIY {
    public static void main(String[] args) {

        Date legacyDate = new Date();
        System.out.println(legacyDate);

        ZoneOffset zoneOffset1 = ZoneOffset.of("-0400");

        // Zastosowanie metod operujących na liczbach całkowitych typu long.
        long longTime = legacyDate.getTime();
        LocalDateTime convertedDate1 = LocalDateTime.ofEpochSecond(
                longTime / 1000, (int) ((longTime % 1000) * 1000), zoneOffset1);
        System.out.println(convertedDate1);

        // Zastosowanie poszczególnych wartości.
        LocalDateTime convertedDate2 = LocalDateTime.of(
                legacyDate.getYear() + 1900,
                legacyDate.getMonth() + 1, legacyDate.getDate(),
                legacyDate.getHours(), legacyDate.getMinutes(),
                legacyDate.getSeconds());
        System.out.println(convertedDate2);
        
        // Określenie strefy czasowej.
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        ZoneId zoneId = timeZone.toZoneId();
        System.out.println("EST - > " + zoneId);
        
        // Konwersja obiektu nowego typu DateTimeFormatter na stary obiekt
        // java.util.Format; będzie go można używać wyłącznie do konwersji
        // danych implementujących interfejs TemporalAccessor.
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        Format legacyFormat = dateTimeFormatter.toFormat();
        System.out.println("Po sformatowaniu: " +legacyFormat.format(convertedDate2));
    }
}
