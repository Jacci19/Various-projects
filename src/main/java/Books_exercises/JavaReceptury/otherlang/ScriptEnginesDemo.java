package Books_exercises.JavaReceptury.otherlang;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

// BEGIN main
public class ScriptEnginesDemo {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        
        // Wyświetlamy listę dostępnych języków.
        scriptEngineManager.getEngineFactories().forEach(factory ->
            System.out.println(factory.getLanguageName()));
        
        // Wykonujemy skrypt, używając języka JavaScript 
        String lang = "ECMAScript";
        ScriptEngine engine = 
            scriptEngineManager.getEngineByName(lang);
        if (engine == null) {
            System.err.println("Nie można znaleźć mechanizmu skryptowego.");
            return;
        }
        engine.eval("print(\"Witamy w języku " + lang + "\");");
    }
}
// END main
