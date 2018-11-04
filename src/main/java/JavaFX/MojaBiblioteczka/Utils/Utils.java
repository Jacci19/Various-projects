package JavaFX.MojaBiblioteczka.Utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static Date convertToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());                       //konwersja localDate do Date
    }

    public static LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
