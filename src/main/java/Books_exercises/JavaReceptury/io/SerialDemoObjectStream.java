package Books_exercises.JavaReceptury.io;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** Program przedstawia zastosowanie standardowego mechanizmu 
 * serializacji obiektów. */
// BEGIN main
public class SerialDemoObjectStream extends SerialDemoAbstractBase {
    protected static final String FILENAME = "serial.dat";

    public static void main(String[] s) throws Exception {
        new SerialDemoObjectStream().save(); // w klasie bazowej, 
                                             // wywołuje metodę write 
        new SerialDemoObjectStream().dump(); // tej klasy.
    }

    /** Ta metoda przeprowadza właściwą serializację. */
    public void write(Object theGraph) throws IOException {
        // Zapisujemy dane na dysku.
        ObjectOutputStream os = new ObjectOutputStream(
            new BufferedOutputStream(
                new FileOutputStream(FILENAME)));
        os.writeObject(theGraph);
        os.close();
    }

    public void dump() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(
            new FileInputStream(FILENAME));
        System.out.println(is.readObject());
        is.close();
    }
}
// END main
