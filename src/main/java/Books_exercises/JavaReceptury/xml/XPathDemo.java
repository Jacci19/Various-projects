package Books_exercises.JavaReceptury.xml;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

// BEGIN main
/**
 * Prosty program demonstrujący zastosowanie XPath (w bibliotece 
 * JAXP w standardowej wersji języka Java – Java SE - możliwości te są 
 * dostępne w pakiecie javax.xml.xpath). 
 */
public class XPathDemo {

    public static void main(String[] args) throws Exception {

        DocumentBuilder parser = 
            DocumentBuilderFactory.newInstance().newDocumentBuilder(); // <1>

        String doc = "<?xml version='1.0'?>" +                         // <2>
        "<section><sectiontitle>Rozmowa o liczbach</sectiontitle>" +
        "<sectionnumber>1.2</sectionnumber>" +
        "<SC>Wprowadzenie</SC><p></p></section>";

        Document document = 
            parser.parse(new ByteArrayInputStream(doc.getBytes()));    // <3>

        // Przetwarzamy wyrażenie XPath, operując na obiekcie Document.
        XPath xpath = XPathFactory.newInstance().newXPath();           // <4>
        String expression = "/section/sectionnumber";                  // <5>
        Number secNum = (Number) xpath.evaluate(                       // <6>
            expression, document, XPathConstants.NUMBER);
        System.out.printf("Sumer sekcji = %s (a %s)",
            secNum, secNum.getClass().getName());                      // <7>
    }
}
// END main
