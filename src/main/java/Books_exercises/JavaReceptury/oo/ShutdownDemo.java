package Books_exercises.JavaReceptury.oo;

/** Przedstawienie sposobu interakcji metody finalize() oraz 
 * setShutdownHooks() z wywołaniami metody System.exit().
 */
// BEGIN main
public class ShutdownDemo {
    public static void main(String[] args) throws Exception {

        // Tworzymy obiekt definiujący metodę finalize() - kiepski 
        // pomysł.
        Object f = new Object() {
            public void finalize() throws Throwable {
                System.out.println("Wykonuję metodę finalize().");
                super.finalize();
            }
        };

        // Określamy kod, który ma zostać wykonany podczas zamykania 
        // aplikacji.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Wykonuję kod określony w " + 
                      "metodzie addShutdownHook().");
            }
        });

        // Jeśli użytkownik nie użył opcji -f (co w przypadku tego 
        // programu jest argumentem nakazującym zwolnienie pamięci) 
        // w wierszu wywołania, to program wywoła metodę System.exit()
        // dysponując odwołaniem do obiektu f, co oznacza, że jego metoda
        // finalize() nie będzie mogła zostać wywołana.

        if (args.length == 1 && args[0].equals("-f")) {
            f = null;
            System.gc();
        }

        System.out.println("Wywołuję metodę System.exit(), obiekt f = " + f);
        System.exit(0);
    }
}
// END main
