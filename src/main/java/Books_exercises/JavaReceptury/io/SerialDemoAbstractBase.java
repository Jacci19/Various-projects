// BEGIN main
package Books_exercises.JavaReceptury.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Przedstawienie użycia interfejsu Serialization. Typowe klasy potomne
 * tej klasy będą miały metodę main() o poniższej strukturze: 
 *    public static void main(String[] s) throws Exception {
 *        new SerialDemoXXX().save();  // w klasie bazowej; wywołuje write
 *        new SerialDemoXXX().dump();
 *    }
 */
public abstract class SerialDemoAbstractBase {

    /** Metoda save aplikacji. */
    public void save() throws IOException {
        List<MyData> l = new ArrayList<>();
        // Gromadzimy dane
        l.add(new MyData("Ian Darwin", "secret_java_cook"));
        l.add(new MyData("Abby Brant", "dujordian"));
        write(l);
    }

    /** Ta metoda wykonuje faktyczną serializację. */
    public abstract void write(Object theGraph) throws IOException;

    /** Odczytujemy plik i wyświetlamy go. */
    public abstract void dump() throws IOException, ClassNotFoundException;
}
// END main
