package Books_exercises.JavaReceptury.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/** Program tworzy i zapisuje dokument XML, korzystając z DOM.
 * Dostosowany do JAXP; wykorzystauje obiekt Transformer.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class DocWriteDOM {

    public static void main(String[] av) throws Exception {
        DocWriteDOM dw = new DocWriteDOM();
        Document doc = dw.makeDoc();

        System.out.println("Zapisuję drzewo dokumentu...");
        Transformer tx = TransformerFactory.newInstance().newTransformer();
        tx.setOutputProperty(OutputKeys.INDENT, "yes");
        tx.transform(new DOMSource(doc), new StreamResult(System.out));
    }

    /** Metoda generuje dokument XML. */
    protected Document makeDoc() {
        try {
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = fact.newDocumentBuilder();
            Document doc = parser.newDocument();

            Node root = doc.createElement("Wiersz");
            doc.appendChild(root);

            Node stanza = doc.createElement("Strofa");
            root.appendChild(stanza);
            
            Node line = doc.createElement("Linijka");
            stanza.appendChild(line);
            line.appendChild(doc.createTextNode("O szyby deszcz dzwoni, deszcz dzwoni jesienny"));
            line = doc.createElement("Linijka");
            stanza.appendChild(line);
            line.appendChild(doc.createTextNode("I pluszcze jednaki, miarowy, niezmienny,"));

            return doc;

        } catch (Exception ex) {
            System.err.println("+============================+");
            System.err.println("|         Błąd XML           |");
            System.err.println("+============================+");
            System.err.println(ex.getClass());
            System.err.println(ex.getMessage());
            System.err.println("+============================+");
            return null;
        }
    }
}
// END main
