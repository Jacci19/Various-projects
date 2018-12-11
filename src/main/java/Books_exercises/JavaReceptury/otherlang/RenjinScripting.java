package Books_exercises.JavaReceptury.otherlang;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RenjinScripting {
    /**
     * Przedstawia interakcję z implementacją języka "R" o nazwie "Renjin".
     */
    // BEGIN main
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("Renjin");
        engine.put("a", 42);
        Object ret = engine.eval("b <- 2; a*b");
        System.out.println(ret);
    }
    // END main
}
