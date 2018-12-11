package Books_exercises.JavaReceptury.numbers;

import java.util.*;

/** Generuje losowe liczby całkowite, skalując wyniki 
 * zwracane przez metodę random(). Wyświetla 1000 liczb 
 * z zakresu od 1 do 10 (włącznie).
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class RandomInt {
    public static void main(String[] a) {
        Random r = new Random();
        for (int i=0; i<1000; i++)
            // nextInt(10) zwraca wartość z zakresu 0-9; 
            // dodajemy 1, aby uzyskać wartość z zakresu 1-10.
            System.out.println(1+r.nextInt(10));
    }
}
// END main
