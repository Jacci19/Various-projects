package Books_exercises.JavaReceptury.numbers;

import java.util.*;

/** Przedstawienie lepszego sposobu generacji wartości losowych, 
 * przy użyciu metod java.util.Random.nextXxx().
 */
public class Random2 {
    public static void main(String[] argv) {
    // BEGIN main
       // Metoda java.lang.Math.random() jest metodą statyczną, 
       // a zatem, aby jej użyć, nie trzeba tworzyć obiektu klasy Math. 
    Random r = new Random();
    for (int i=0; i<10; i++)
       System.out.println("Wartość double wygenerowana przez java.util.Random to: " 
             + r.nextDouble());
    for (int i=0; i<10; i++)
       System.out.println("Wartość int wygenerowana przez java.util.Random to: " 
             + r.nextInt());
    // END main
    }
}
