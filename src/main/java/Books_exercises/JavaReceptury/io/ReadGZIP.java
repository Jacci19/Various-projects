package Books_exercises.JavaReceptury.io;

import java.io.*;
import java.util.zip.*;

/**
 * Odczytuje dane z pliku gzip.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ReadGZIP {
    public static void main(String[] argv) throws IOException {
        String FILENAME = "file.txt.gz";

        // Ponieważ będą tu wykorzystywane aż cztery konstruktory, 
        // zapisałem je w bardziej czytelnej postaci.
        // W normalnych aplikacjach wywołania konstruktorów byłyby 
        // zapewne zagnieżdżone.
        FileInputStream fin = new FileInputStream(FILENAME);
        GZIPInputStream gzis = new GZIPInputStream(fin);
        InputStreamReader xover = new InputStreamReader(gzis);
        BufferedReader is = new BufferedReader(xover);

        String line;
        // Teraz odczytujemy wiersze tekstu: obiekt BufferedReader
        // grupuje je w wiersze, InputStreamReader obsługuje konwersję 
        // znaków na Unicode, GZIPInputStream odczytuje zawartość 
        // skompresowanego pliku ze strumienia FileInputStream.
        while ((line = is.readLine()) != null)
            System.out.println("Odczytano: " + line);
    }
}
// END main
