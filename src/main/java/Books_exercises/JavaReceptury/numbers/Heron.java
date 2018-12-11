package Books_exercises.JavaReceptury.numbers;

/** Obliczenie powierzchni trójkąta przy wykorzystaniu wzoru 
 * Herona. Kod i wartości podane przez Prof. W. Kahana i
 * Josepha D. Darcyego. 
 * Patrz http://www.cs.berkeley.edu/~wkahan/JAVAhurt.pdf.
 * Opracowane na bazie kodu podanego w artykule Java Pro 
 * Ricka Grehana (październik 1999). * Simplified and reformatted by Ian Darwin.
 */
// BEGIN main
public class Heron {
    public static void main(String[] args) {
        // Krawędzie trójkąta (dane typu float).
        float af, bf, cf;
        float sf, areaf;

        // To samo - dane typu double.
        double ad, bd, cd;
        double sd, aread;

        // Powierzchnia trójkąta (wartości typu float).
        af = 12345679.0f;
        bf = 12345678.0f;
        cf = 1.01233995f;

        sf = (af+bf+cf)/2.0f;
        areaf = (float)Math.sqrt(sf * (sf - af) * (sf - bf) * (sf - cf));
        System.out.println("Wartość zmiennoprzecinkowa o pojedynczej precyzji (float): " + areaf);

        // Powierzchnia trójkąta (wartość typu double).
        ad = 12345679.0;
        bd = 12345678.0;
        cd = 1.01233995;

        sd = (ad+bd+cd)/2.0d;
        aread =        Math.sqrt(sd * (sd - ad) * (sd - bd) * (sd - cd));
        System.out.println("Wartość zmiennoprzecinkowa o podwójnej precyzji (double): " + aread);
    }
}
// END main
