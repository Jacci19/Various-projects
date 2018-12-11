package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/*
 * Odwraca kolejność dwóch pól.
 * Dane wejściowe:
 * Adamski, Andrzej Alojzy
 * Wynik:
 * Andrzej Alojzy Adamski 
 */
// BEGIN main
public class REmatchTwoFields {
    public static void main(String[] args) {
        String inputLine = "Adamski, Andrzej Alojzy";
        // Tworzymy wyrażenie regularne z dwoma parami nawiasów, które 
        // "pobierze" obie części łańcucha - field1 i field2.
        Pattern r = Pattern.compile("(.*), (.*)");
        Matcher m = r.matcher(inputLine);
        if (!m.matches())
            throw new IllegalArgumentException("Nieprawidłowe dane wejściowe.");
        System.out.println(m.group(2) + ' ' + m.group(1));
    }
}
// END main
