package Books_exercises.JavaReceptury.otherlang.calcscriptengine;

import java.io.Reader;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

// BEGIN main
public class CalcScriptEngine extends AbstractScriptEngine {

    private ScriptEngineFactory factory;
    
    CalcScriptEngine(ScriptEngineFactory factory) {
        super();
        this.factory = factory;
    }

    @Override
    public Object eval(String script, ScriptContext context)
            throws ScriptException {
        System.out.println("CalcScriptEngine.eval(): Uruchamiam skrypt: " + script);
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(script);
        while (st.hasMoreElements()) {
            String tok = st.nextToken();
            if (tok.equals("+")) {
                return stack.pop() + stack.pop();
            }
            if (tok.equals("-")) {
                final Integer tos = stack.pop();
                return stack.pop() - tos;
            }
            if (tok.equals("*")) {
                return stack.pop() * stack.pop();
            }
            if (tok.equals("/")) {
                final Integer tos = stack.pop();
                return stack.pop() / tos;
            }
            // else ... sprawdzać inne operatory.
            // Jeśli to nie operator, to mamy do czynienia z nazwą.
            // Pobieramy wartość i zapisujemy ją na stosie.
            stack.push((Integer) context.getAttribute(tok));
        }
        return 0;
    }

    @Override
    public Object eval(Reader reader, ScriptContext context)
            throws ScriptException {
        System.out.println("CalcScriptEngine.eval()");
        // Metoda powinna odczytać zawartość pliku w formie łańcucha 
        // znaków (String), a następnie zwrócić wartość 
        // eval(scriptString, context);
        throw new IllegalStateException("Metoda eval(Reader) jeszcze nie istnieje.");
    }

    @Override
    public Bindings createBindings() {
        Bindings ret = new SimpleBindings();
        return ret;
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return factory;
    }
}
// END main
