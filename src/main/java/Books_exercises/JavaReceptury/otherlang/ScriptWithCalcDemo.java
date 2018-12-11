package Books_exercises.JavaReceptury.otherlang;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/** Prosty program pokazujący że program CalcScriptEngine działa. */
public class ScriptWithCalcDemo {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = 
            scriptEngineManager.getEngineByName("SimpleCalc");
        if (engine == null) {
            System.err.println("Nie można znaleźć silnika skryptowego.");
            return;
        }
        engine.put("i", 99);
        engine.put("j", 1);
        Object retVal = engine.eval("i j +");
        System.out.println("Skrypt zwrócił wynik " + retVal);
    }
}