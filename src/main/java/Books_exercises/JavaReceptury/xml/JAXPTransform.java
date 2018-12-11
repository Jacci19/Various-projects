package Books_exercises.JavaReceptury.xml;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/** Przedstawia najprostszy przykład zastosowania JAXP do wykonywania
 * przekształceń przy użyciu XSL.
 */
// BEGIN main
public class JAXPTransform {
    
    /**
     * param argumentami są nazwy trzech plików: XML, XSL
     * oraz pliku wynikowego (kolejność poszczególnych plików ma
     * uwarunkowania historyczne).
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        // Wymagamy wszystkich trzech argumentów wiersza poleceń.
        if (args.length != 3) {
            System.out.println(
            "Sposób użycia: java JAXPTransform plik.xml plik.xsl plikWynikowy");
            System.exit(1);
        }
        
        // Tworzymy obiekt Transformer.
        Transformer tx = TransformerFactory.newInstance().newTransformer(
                new StreamSource(new File(args[1]))); // nie 0
        
        // Use its transform() method to perform the transformation
        // Wywołujemy metodę transform(), by wykonać przekształcenie
        tx.transform(new StreamSource(new File(args[0])), // a nie 1
                new StreamResult(new File(args[2])));
    }
}
// END main
