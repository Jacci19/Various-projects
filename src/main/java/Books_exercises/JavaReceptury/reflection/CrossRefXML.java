package Books_exercises.JavaReceptury.reflection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** Klasa potomna klasy CrossRef wyświetlająca informacje w postaci
 * kodu XML.
 */
// BEGIN main
public class CrossRefXML extends CrossRef {

    public static void main(String[] argv) throws IOException {
        CrossRef xref = new CrossRefXML();
        xref.doArgs(argv);
    }

    /** Wyświetlamy początek klasy. 
     */
    protected void startClass(Class<?> c) {
        println("<class><classname>" + c.getName() + "</classname>");
    }

    protected void putField(Field fld, Class<?> c) {
        println("<field>" + fld + "</field>");
    }

    /** Zapisujemy informacje pobrane z obiektów Method 
     * w standardowym strumieniu wyjściowym. Oznaczamy pola 
     * chronione (protected), aby nie próbowano ich przesłaniać 
     * (podpowiedź).
     */
    protected void putMethod(Method method, Class<?> c) {
        println("<method>" + method + "</method>");
    }

    /** Wyświetlamy znacznik końca klasy.  
     */
    protected void endClass() {
        println("</class>");
    }
}
// END main
