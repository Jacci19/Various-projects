package Books_exercises.JavaReceptury.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

// BEGIN main
public class Log14Demo2 {
    public static void main(String[] args) {

        System.setProperty("java.util.logging.config.file", 
            "logging/logging.properties");

        Logger logger = Logger.getLogger("com.darwinsys");

        try {
            Object o = new Object();
            logger.info("Utworzyłem obiekt: " + o);
            if (o != null) {    // Tylko do przedstawienia rejestracji
                throw new IllegalArgumentException("Testujemy...");
            }
        } catch (Exception t) {
            // Przechwutujemy wszystkie wyjątki:
            logger.log(Level.SEVERE, "Przechwycono wyjątek", t);
            // Rozwiązanie alternatywne: dłuższa postać, zapewniająca
            // większą kontrolę. 
            // LogRecord msg = new LogRecord(Level.SEVERE, "Przechwycono wyjątek");
            // msg.setThrown(t);
            // logger.log(msg);
        }
    }
}
// END main
