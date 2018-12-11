package Books_exercises.JavaReceptury.io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/** 
 * Ten przykład prezentuje metody do serializacji obiektów w postaci kodu 
 * XML, dosępne w klasach "java.beans.*.
 * Należy zauważyć, że serializowane są wyłącznie te właściwości, które 
 * udostępniają publiczne metody get i set. Przeważająca część infrastruktury
 * do serializacji została zaimplementowana w klasie "SerialDemoAbstratBase".
 */
// BEGIN main
public class SerialDemoXML extends SerialDemoAbstractBase {

    public static final String FILENAME = "serial.xml";

    public static void main(String[] args) throws IOException {
        new SerialDemoXML().save();
        new SerialDemoXML().dump();
    }

    /** Zapisujemy dane na dysku. */
    public void write(Object theGraph) throws IOException {
        XMLEncoder os = new XMLEncoder(
                new FileOutputStream(FILENAME));
        os.writeObject(theGraph);
        os.close();
    }

    /** Wyświetlamy dane. */
    public void dump() throws IOException {
        XMLDecoder inp = new XMLDecoder(
                new FileInputStream(FILENAME));
        System.out.println(inp.readObject());
        inp.close();
    }
}
// END main
