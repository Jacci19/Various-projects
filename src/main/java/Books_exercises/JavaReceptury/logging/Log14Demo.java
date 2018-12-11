package Books_exercises.JavaReceptury.logging;

import java.util.logging.Logger;

// BEGIN main
public class Log14Demo {
    public static void main(String[] args) {

        Logger myLogger = Logger.getLogger("com.darwinsys");

        Object o = new Object();
        myLogger.info("Utworzyłem obiekt: " + o);
    }
}
// END main
