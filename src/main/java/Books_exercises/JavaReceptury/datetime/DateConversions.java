package Books_exercises.JavaReceptury.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConversions {
    public static void main(String[] args) {
        
        // BEGIN main
        // Konwersja liczby sekund, jakie upłynęły od początku epoki, na 
        // lokalną datę i godzinę.
        Instant epochSec = Instant.ofEpochSecond(1000000000L);
        ZoneId zId = ZoneId.systemDefault();
        ZonedDateTime then = ZonedDateTime.ofInstant(epochSec, zId);
        System.out.println("Miliard sekund od początku epoki to " + then);
        
        // Konwersja daty i godziny na liczę sekund.
        long epochSecond = ZonedDateTime.now().toInstant().getEpochSecond();
        System.out.println("Aktualizacja liczba sekund od początku epoki: " + epochSecond);
        
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime there = now.atZone(ZoneId.of("Canada/Pacific"));
        System.out.printf("Jeśli u nas jest %s, to w Vancouver jest %s", 
            now, there);
        // END main
    }
}
