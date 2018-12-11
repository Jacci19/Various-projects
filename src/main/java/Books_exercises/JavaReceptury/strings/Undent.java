package Books_exercises.JavaReceptury.strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Undent - usuwanie odstępów z początku łańcucha znaków.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Undent {
    /** Maksymalna ilość odstępów, jakie będą usuwane. */
    protected int nSpaces;

    Undent(int n) {
        nSpaces = n;
    }

    public static void main(String[] av) {
        Undent c = new Undent(5);
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

    /** Wyświetlamy plik, dysponując obiektem BufferedReader. */
    public void process(BufferedReader is) {
        try {
            String inputLine;

            // BEGIN main
            while ((inputLine = is.readLine()) != null) {
                int toRemove = 0;
                for (int i=0; i<nSpaces && i < inputLine.length() && 
                Character.isWhitespace(inputLine.charAt(i)); i++)
                        ++toRemove;
                System.out.println(inputLine.substring(toRemove));
            }
            // END main
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
