package Books_exercises.JavaReceptury.regex;

import java.util.regex.*;

/**
 * Przetwarzanie plików dziennika serwera Apache przy użyciu wyrażeń 
 * regularnych.
 */
// BEGIN main
public class LogRegExp  {

    public static void main(String argv[]) {

        String logEntryPattern = 
            "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] " +
            "\"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

        System.out.println("Wyrażenie regularne:");
        System.out.println(logEntryPattern);

        System.out.println("Wiersz wejściowy:");
        String logEntryLine = LogExample.logEntryLine;
        System.out.println(logEntryLine);

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(logEntryLine);
        if (!matcher.matches() || 
            LogExample.NUM_FIELDS != matcher.groupCount()) {
            System.err.println("Nieprawidłowy wpis w dzienniku " 
                          + "(lub problem z wyrażeniem regularnym):");
            System.err.println(logEntryLine);
            return;
        }
        System.out.println("Adres IP: " + matcher.group(1));
        System.out.println("Użytkownik: " + matcher.group(3));
        System.out.println("Data/godzina: " + matcher.group(4));
        System.out.println("Żądanie: " + matcher.group(5));
        System.out.println("Odpowiedź: " + matcher.group(6));
        System.out.println("Liczba bajtów: " + matcher.group(7));
        if (!matcher.group(8).equals("-"))
            System.out.println("Adres strony: " + matcher.group(8));
        System.out.println("Przeglądarka: " + matcher.group(9));
    }
}
// END main
