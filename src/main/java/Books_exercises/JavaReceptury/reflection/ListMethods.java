package Books_exercises.JavaReceptury.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Program tworzy listę konstruktorów i metod.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ListMethods {
    public static void main(String[] argv) throws ClassNotFoundException {
        if (argv.length == 0) {
            System.err.println("Sposób użycia: ListMethods className");
            return;
        }
        Class<?> c = Class.forName(argv[0]);
        Constructor<?>[] cons = c.getConstructors();
        printList("Konstruktory", cons);
        Method[] meths = c.getMethods();
        printList("Metody", meths);
    }
    static void printList(String s, Object[] o) {
        System.out.println("*** " + s + " ***");
        for (int i=0; i<o.length; i++)
            System.out.println(o[i].toString());
    }
}
// END main
