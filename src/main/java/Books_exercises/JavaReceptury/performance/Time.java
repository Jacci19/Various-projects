package Books_exercises.JavaReceptury.performance;

import java.lang.reflect.*;

/** Pomiar czasu realizacji metody main() innej klasy. 
 * Do wykorzystania podczas określania efektywności programów.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Time {
    public static void main(String[] argv) throws Exception {
        // Tworzymy klasę docelową na podstawie nazwy zapisanej w args[0].
        Class<?> c = Class.forName(argv[0]);

        // Odnajdujemy statyczną metodę main (użyj własnej zmiennej 
        // argv jako sygnatury).
        Class<?>[] classes = { argv.getClass() };
        Method main = c.getMethod("main", classes);

        // Tworzymy nową tablicę argumentów.
        // Normalnie Java nie pobiera nazwa klasy, jednak w tym 
        // przypadku to użytkownik podaje w wierszu wywołania 
        // zarówno nazwę klasy zawierającą metodę main(), której 
        // czas wykonania chcemy zmierzyć, jak i wszelkie niezbędne 
        // argumenty.
        String nargv[] = new String[argv.length - 1];
        System.arraycopy(argv, 1, nargv, 0, nargv.length);

        Object[] nargs = { nargv };

        System.err.println("Rozpoczynamy pomiary klasy " + c);

        // Zaraz uruchomimy pomiar czasu. Absolutnie nie można
        // wykonywać żadnych innych czynności (nawet metody println),
        // które mogłyby zostać dołączone do czasu realizacji programu,
        // zaczynając od teraz, aż do pobrania czasu zakończenia.

        // Pobieramy bieżący czas (początku).
        long t0 = System.currentTimeMillis();

        // Wywołujemy metodę main testowanej klasy.
        main.invoke(null, nargs);

        // Pobieramy czas zakończenia i obliczamy długość wykonywania.
        long t1 = System.currentTimeMillis();

        long runTime = t1 - t0;

        System.err.println(
             "Czas realizacji ="  + Double.toString(runTime/1000D));
    }
}
// END main
