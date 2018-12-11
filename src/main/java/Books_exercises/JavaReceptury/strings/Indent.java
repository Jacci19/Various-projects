package Books_exercises.JavaReceptury.strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Indent - dodaje odstępu na początku wiersza
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */

public class Indent {
    /** Domyśla liczba znaków odstępu. */
    static int nSpaces = 10;

    public static void main(String[] av) {
        Indent c = new Indent();
        switch(av.length) {
            case 0: c.process(new BufferedReader(
                        new InputStreamReader(System.in))); break;
            default:
        for (int i=0; i<av.length; i++)
            try {
                c.process(new BufferedReader(new FileReader(av[i])));
            } catch (FileNotFoundException e) {
                System.err.println(e);
            }
        }
    }

    /** Metoda wyświetla jeden plik, przekazny w formie obiektu BufferedReader */
    public void process(BufferedReader is) {
        try {
            String inputLine;

            // BEGIN main
            while ((inputLine = is.readLine()) != null) {
                for (int i=0; i<nSpaces; i++) System.out.print(' ');
                System.out.println(inputLine);
            }
            // END main
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
