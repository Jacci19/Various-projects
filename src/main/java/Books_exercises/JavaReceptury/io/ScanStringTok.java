package Books_exercises.JavaReceptury.io;

import java.io.*;
import java.util.*;

/**
 * ScanStringTok - analiza pliku przy użyciu obiektu StringTokenizer.
 *
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ScanStringTok {
    protected LineNumberReader is;

    public static void main(String[] av) throws IOException {
        if (av.length == 0)
            new ScanStringTok(
                new InputStreamReader(System.in)).process();
        else 
            for (int i=0; i<av.length; i++)
                new ScanStringTok(av[i]).process();
    }

    /** Konstruktor skanera tworzący go na podstawie nazwy.  */
    public ScanStringTok(String fileName) throws IOException {
        is = new LineNumberReader(new FileReader(fileName));
    }

    /** Konstruktor skanera tworzący go na podstawie przekazanego
     * obiektu Reader. */
    public ScanStringTok(Reader rdr) throws IOException {
        // Dodawanie kolejnego poziomu buforowania nie ma sensu, o ile
        // czytelnik już jest buforowany...
        if (rdr instanceof LineNumberReader)
            is = (LineNumberReader)rdr;
        else
            is = new LineNumberReader(rdr);
    }

    protected void process() {
        String s = null;
        try {
            while ((s = is.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(s, "@", true);
                String user = (String)st.nextElement();
                st.nextElement();
                String host = (String)st.nextElement();
                System.out.println("Nazwa użytkownika: " + user +
                    "; host i domena: " + host);

                // Teraz zapewne wykonamy jakieś operacje na nazwach 
                // użytkownika i hosta...  

            }

        } catch (NoSuchElementException ix) {
            System.err.println("Wiersz " + is.getLineNumber() +
                ": Nieprawidłowe dane wejściowe " + s);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
// END main
