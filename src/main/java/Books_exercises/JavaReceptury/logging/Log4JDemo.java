package Books_exercises.JavaReceptury.logging;

import org.apache.log4j.Logger;

// BEGIN main
public class Log4JDemo {
    public static void main(String[] args) {

        Logger myLogger = Logger.getLogger("com.darwinsys");

        // PropertyConfigurator.configure("log4j.properties");
        
        Object o = new Object();
        myLogger.info("Utworzyłem obiekt: " + o);

    }
}
// END main
