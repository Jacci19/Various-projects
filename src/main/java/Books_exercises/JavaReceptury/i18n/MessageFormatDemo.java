package Books_exercises.JavaReceptury.i18n;

import java.text.*;

// BEGIN main
public class MessageFormatDemo {

    static Object[] data = {
            new java.util.Date(),
            "myfile.txt",
            "plik nie istnieje"
    };

    public static void main(String[] args) {
        String result = MessageFormat.format(
            "O godzinie {0,time} dnia {0,date} {2} {1}.", data);
        System.out.println(result);
    }
}
// END main
