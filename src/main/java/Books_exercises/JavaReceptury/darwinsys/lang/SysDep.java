// BEGIN main
package Books_exercises.JavaReceptury.darwinsys.lang;

import java.io.File;

/** Niektóre rozwiązania zależne od systemu operacyjnego.
 * Wszystkie metody są statyczne.
 * @author Ian Darwin
 */
public class SysDep {

    final static String UNIX_NULL_DEV = "/dev/null";
    final static String WINDOWS_NULL_DEV = "NUL:";
    final static String FAKE_NULL_DEV = "tmpplk";
    
    /** Zwraca nazwę pseudourządzenia w systemach które je obsługują
     *  lub łańcuch znaków "tmpplk", jeśli używany system operacyjny 
     *  nie udostępnia pseudourządzeń.
     */
    
    public static String getDevNull() {

        if (new File(UNIX_NULL_DEV).exists()) {     // <1>
            return UNIX_NULL_DEV;
        }

        String sys = System.getProperty("os.name"); // <2>
        if (sys==null) {                            // <3>
            return FAKE_NULL_DEV;
        }
        if (sys.startsWith("Windows")) {            // <4>
            return WINDOWS_NULL_DEV;
        }
        return FAKE_NULL_DEV;                       // <5>
    }
}
// END main
