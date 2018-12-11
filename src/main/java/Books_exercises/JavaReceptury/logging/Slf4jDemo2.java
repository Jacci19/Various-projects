package Books_exercises.JavaReceptury.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Person;

// BEGIN main
public class Slf4jDemo2 {

    final static Logger theLogger = LoggerFactory.getLogger(Slf4jDemo2.class);

    public static void main(String[] args) {

        try {
            Person p = new Person();
            // Określamy wartości pól obiektu Person
            theLogger.info("Utworzono obiekt {}", p);
            
            if (p != null) {    // Tylko po to, by pokazać rejestrację
                throw new IllegalArgumentException("Testujemy...");
            }
        } catch (Exception ex) {
            theLogger.error("Zgłoszono wyjątek: " + ex, ex);
        }
    }
}
// END main
