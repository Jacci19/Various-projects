package Books_exercises.JavaReceptury.numbers;

import java.math.*;

/**
 * Prezentacja korzystania z liczb o bardzo dużych wartościach.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class BigNums {
    public static void main(String[] argv) {
        // BEGIN main
        System.out.println("Wartość Long.MAX_VALUE to: " + Long.MAX_VALUE);
        BigInteger bInt = new BigInteger("3419229223372036854775807");
        System.out.println("Oto bardzo duża liczba całkowita: " + bInt);
        System.out.println("Oto bardzo duża liczba zmiennoprzecinkowa: " 
              + bInt.doubleValue());
        // END main
    }
}
