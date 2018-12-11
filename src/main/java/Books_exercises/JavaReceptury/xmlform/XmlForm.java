package Books_exercises.JavaReceptury.xmlform;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/** Konwersja prostego pliku XML do postaci tekstowej.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class XmlForm {
    protected Reader is;
    protected String fileName;

    protected static PrintStream msg = System.out;

    /** Tworzymy konwerter, dysponując nazwą pliku wejściowego. */
    public XmlForm(String fn) {
        fileName = fn;
    }

    /** Konwertujemy plik. */
    public void convert(boolean verbose) {
        try {
            if (verbose)
                System.err.println(">>>Analizujemy plik " + fileName + "...");
            // Otwieramy dokument jako URL, aby działały pliki DTD 
            // określone w sposób względny.
            //String uri = "file:" + new File(fileName).getAbsolutePath();
            InputStream uri = getClass().getResourceAsStream(fileName);
            DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse( uri );
            if (verbose)
                System.err.println(">>>Przetwarzamy zawartość pliku " + fileName + "...");
            XmlFormWalker c = new GenMIF(doc, msg);
            c.convertAll();

        } catch (Exception ex) {
            System.err.println("+================================+");
            System.err.println("|         *Błąd analizy*         |");
            System.err.println("+================================+");
            System.err.println(ex.getClass());
            System.err.println(ex.getMessage());
            System.err.println("+================================+");
        }
        if (verbose)
            System.err.println(">>>Zakończono przetwarzanie pliku " + fileName + "...");
    }

    public static void main(String[] av) {
        if (av.length == 0) {
            System.err.println("Sposób użycia: XmlForm plik");
            return;
        }
        for (int i=0; i<av.length; i++) {
            String name = av[i];
            new XmlForm(name).convert(true);
        }
        msg.close();
    }
}
// END main
