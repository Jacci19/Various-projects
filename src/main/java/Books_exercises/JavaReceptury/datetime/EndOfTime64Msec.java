package Books_exercises.JavaReceptury.datetime;

import java.util.Date;

/** Kiedy nastąpi przepełnienie 64bitowego licznika czasu mierzącego 
 * liczbę milisekund, które upłynęły do 1970?
 * Odpowiedź: w niedzielę 17 sierpnia 292278994 o godzinie 08:12:55
 * czasu środkowoeuropejskiego.
 * @author Ian Darwin
 */
public class EndOfTime64Msec {
    public static void main(String[] args) {
        // BEGIN ofTime
        Date endOfTime = new Date(Long.MAX_VALUE);
        System.out.println("Przepełnienie czasu w języku Java8 nastąpi:" + 
                endOfTime);
        // END ofTime
    }
}
