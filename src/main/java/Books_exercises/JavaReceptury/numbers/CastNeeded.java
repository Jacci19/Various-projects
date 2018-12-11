package Books_exercises.JavaReceptury.numbers;

/** Przedstawienie rzutowania. */
public class CastNeeded {
    // BEGIN main
    public static void main(String[] argv) {
        int i;
        double j = 2.75;
        i = j;             // TU SIĘ POJAWI BŁĄD KOMPILACJI  
        i = (int)j;        // w przypadku rzutowania i = 2.  
        System.out.println("i =" + i);                      
        byte b;                                             
        b = i;             // TU SIĘ POJAWI BŁĄD KOMPILACJI  
        b = (byte)i;       // w przypadku rzutowania i = 2.     
        System.out.println("b =" + b);
    }
    // END main
}
