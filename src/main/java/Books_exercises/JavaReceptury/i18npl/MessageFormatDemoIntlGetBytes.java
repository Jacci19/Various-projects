package Books_exercises.JavaReceptury.i18npl;

import java.io.UnsupportedEncodingException;
import java.text.*;
import java.util.*;

/**
 * To rozwiązanie zostało opracowane na podsatwie informacji dostępnych 
 * na serwisie Stackoverflow.com na stronie: 
 * http://stackoverflow.com/questions/4659929/how-to-use-utf-8-in-resource-properties-with-resourcebundle
 * 
 */

// BEGIN main
public class MessageFormatDemoIntlGetBytes {

    private static Date date = new Date();
    private static String fileName = "myfile.txt";

    public static void main(String[] args) throws UnsupportedEncodingException {
        ResourceBundle rb = ResourceBundle.getBundle("Widgets");
        /**
         * W standardowy sposób pobieramy łańcuch znaków z pliku właściwości,
         * przy czym plik ma być zapisany przy użyciu kodowania UTF-8.
         */
        String format = rb.getString("filedialogs.cantopen.format");
        
        /**
         * Pobieramy zawartość łańcucha znaków  jako tablicę bajtów
         * a następnie towrzymy nowy łańcuch konwertując te bajty przy 
         * użyciu odpowiedniego kodowania UTF-8.
         */
        String formatPL = new String(format.getBytes("ISO-8859-1"), "UTF-8");
        
        String result = MessageFormat.format(formatPL, date, fileName);
        System.out.println(result);
    }
}
// END main
