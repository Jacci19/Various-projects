package Books_exercises.JavaReceptury.numbers;

import java.util.*;

/** Przedstawienie lepszego sposobu generacji wartości losowych,
 * przy użyciu metody java.util.Random.nextXxx().
 */
public class Random3 {
    public static void main(String[] argv) {
        // Metoda java.lang.Math.random() jest metodą statyczną, 
        // a zatem, aby jej użyć, nie trzeba tworzyć obiektu klasy Math. 
        // BEGIN main
        Random r = new Random();
        for (int i=0; i<10; i++)
           System.out.println("Wartość wybrana przy wykorzystaniu rozkładu Gaussa - " 
                 + r.nextGaussian());
        // END main
    }
}
