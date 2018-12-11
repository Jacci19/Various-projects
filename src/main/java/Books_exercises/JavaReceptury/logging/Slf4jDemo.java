package Books_exercises.JavaReceptury.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN main
public class Slf4jDemo {
    
    final static Logger theLogger = LoggerFactory.getLogger(Slf4jDemo.class);
    
    public static void main(String[] args) {

        Object o = new Object();
        theLogger.info("Utworzyłem obiekt: " + o);

    }
}
// END main
