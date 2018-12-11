package Books_exercises.JavaReceptury.numbers;


/** Przedstawienie prostego sposobu generowania liczb losowych, przy
 * użyciu metody java.lang.Math.Random().
 */
public class Random1 {
    public static void main(String[] argv) {
        // BEGIN main
        // Metoda java.lang.Math.random() jest metodą statyczną, 
        // a zatem, aby jej użyć, nie trzeba tworzyć obiektu klasy Math. 
        System.out.println("Liczba wygenerowana przez metodę java.lang.Math to " 
             + Math.random());
        // END main
    }
}
