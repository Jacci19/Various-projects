package Books_exercises.JavaReceptury.reflection;

import java.lang.reflect.Method;

// BEGIN main
/**
 * Program pobiera określoną metodę i wywołuje ją.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class GetAndInvokeMethod {

    /** Ta klasa jest tutaj tylko po to, aby dać nam możliwość
     * wykonania tego, co chcemy. Wywołanie println() udowodni,
     * że nasze rozwiązanie działa.
     */
    static class X {
        public void work(int i, String s) {
            System.out.printf("Operujemy na łańcuchu: i=%d, s=%s%n", i, s);
        }
        // Główny kod programu nie używa tej metody przeciążonej.
        public void work(int i) {
            System.out.println("Nieoczekiwane wywołanie!");
        }
    }
    public static void main(String[] argv) {
        try {
            Class<?> clX = X.class; // lub Class.forName("X");

            // Aby odszukać metodę, należy stworzyć tablicę obiektów Class
            // odpowiadających typom argumentów wywołania.
            Class<?>[] argTypes = {
                int.class,
                String.class
            };

            // Teraz odnajdujemy i pobieramy obiekt Method poszukiwanej 
            // metody.
            Method worker = clX.getMethod("work", argTypes);

            // W celu wywołania metody musimy utworzyć tablicę jej 
            // argumentów; ma to być tablica typu Object. 
            Object[] theData = {
                42,
                "Czekoladowe chipsy"
            };

            // Ostatni, najbardziej oczywisty krok: wywołanie metody.
            // Pierwszy argument jest obiektem; jeśli wywołujemy metodę
            // statyczną, to pierwszym argumentem musi być wartość null.
            worker.invoke(new X(), theData);

        } catch (Exception e) {
            System.err.println("Wywołanie invoke() nie udało się: " + e);
        }
    }
}
// END main
