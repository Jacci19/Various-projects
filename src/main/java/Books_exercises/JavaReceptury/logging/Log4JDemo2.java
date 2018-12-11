package Books_exercises.JavaReceptury.logging;

import org.apache.log4j.Logger;

// BEGIN main
public class Log4JDemo2 {
    public static void main(String[] args) {

        Logger theLogger = Logger.getLogger("com.darwinsys");

        try {
            Object o = new Object();
            theLogger.info("Utworzyłem obiekt: " + o);
            if (o != null) {    // tylko do przedstawienia rejestracji 
                throw new IllegalArgumentException("Testujemy...");
            }
        } catch (Exception ex) {
            theLogger.error("Przechwycono wyjątek: " + ex, ex);
        }
    }
}
// END main
