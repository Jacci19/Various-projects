package Books_exercises.JavaReceptury.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/** Klasa analizuje plik XML korzystając z DOM i JAXP. Próbuje zweryfikować 
 * poprawność dokumentu używając zarówno DTD jak i Schema. 
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class XParse {

    /**
     * Metoda analizuje jeden lub kilka dokumentów XML, opcjonalnie sprawdzając 
     * także ich poprawność.
     * <b>Note:</b> Pakiet javax.xml.validation ma to niemiłe ograniczenie, 
     * że choć w przypadku weryfikacji w oparciu o DTD, zarówno nazwa pliku DTD
     * jak i jego lokalizacja może być pobrana z treści dokumentu XML, to 
     * w przypadku użycia schematów (Schema), użytkownik musi to zrobić ręcznie 
     * przed wywołaniem analizatora.
     * @param av Argumenty wywołania, mogą zawierać: -v - weryfikacja oraz -a schema.xsd; 
     * są one umieszczane przed nazwą pliku (lub plików);
     * wszystkie dokumenty przetwarzane w ramach danego wywołania programu muszą
     * używać tego samego pliku schematu.
     * @throws SAXException
     */
    // BEGIN main
    public static void main(String[] av) throws SAXException {
        if (av.length == 0) {
            System.err.println("Sposób użycia: XParse plik");
            return;
        }
        boolean validate = false;
        Schema schema = null;
        try {
            for (int i=0; i<av.length; i++) {
                if (av[i].equals("-v"))
                    validate = true;
                else if (av[i].equals("-a")) {
                    // Tworzymy obiekt "SchemaFactory potrafiący rozumieć 
                    // schematy W3C" - cytat z dokumentacji.
                    SchemaFactory schemaFactory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

                    // Wczytujemy schemat reprezentowany przez obiekt Schema.
                    String schemaLocation = av[++i];
                    File schemaFile = new File(schemaLocation);
                    if (!schemaFile.exists()) {
                        throw new IOException(
                        "Plik schematu - " + schemaLocation + " - nie istnieje");
                    }
                    schema = schemaFactory.newSchema(schemaFile);
                    
                } else {
                    File xmlFile = new File(av[i]);
                    System.err.println(
                        "Analizuję plik " + xmlFile.getAbsolutePath() + "...");
                    
                    DocumentBuilderFactory dbFactory = 
                        DocumentBuilderFactory.newInstance();
                    if (validate) {
                        if (schema != null) {
                            dbFactory.setSchema(schema);
                        } else {
                            dbFactory.setValidating(true);
                            dbFactory.setNamespaceAware(true);
                            dbFactory.setAttribute(
                            "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
                        }            
                    }
                    DocumentBuilder parser = dbFactory.newDocumentBuilder();
                    // Jeśli nie używamy schematu, pobieramy lokalną 
                    // kopię DTD... 
                    if (schema == null) {
                        parser.setEntityResolver(new MyDTDResolver());
                    }
                    parser.parse(xmlFile);
                    System.out.println("Przeanalizowany/sprawdzony, OK");
                }
            }
        // Poniżej jedynie +przechwytujemy+ wyjątki...
        // END main
        } catch (SAXParseException ex) {
            System.err.println("+================================+");
            System.err.println("|  * SAX - błąd przetwarzania*   |");
            System.err.println("+================================+");
            System.err.println(ex.toString());
            System.err.println("W wierszu " + ex.getLineNumber());
        } catch (Exception ex) {
            System.err.println("+================================+");
            System.err.println("|          * Błąd XML *          |");
            System.err.println("+================================+");
            ex.printStackTrace();
            
        }
    }
}
