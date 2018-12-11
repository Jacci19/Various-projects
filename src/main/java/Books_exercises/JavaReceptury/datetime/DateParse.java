package Books_exercises.JavaReceptury.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// BEGIN part1
/** Program przedstawia kilka przykładów tworzenia dat na podstawie
 * przekazanego łańcucha znaków. */
public class DateParse {
    public static void main(String[] args) {

        String armisticeDate = "1914-11-11";
        LocalDate aLD = LocalDate.parse(armisticeDate);
        System.out.println("Data: " + aLD);
        
        String armisticeDateTime = "1914-11-11T11:11";
        LocalDateTime aLDT = LocalDateTime.parse(armisticeDateTime);
        System.out.println("Data/godzina: " + aLDT);
        // END part1
        
        // BEGIN part2
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM uuuu");
        String anotherDate = "27 sty 2011";
        LocalDate random = LocalDate.parse(anotherDate, df);
        System.out.println("Łańcuch \"" + anotherDate + 
                "\" został przekształcony na datę " + random);
        // END part2
        
        System.out.println(aLD + " po sformatowaniu ma postać: " + 
                df.format(aLD));
    }
}
