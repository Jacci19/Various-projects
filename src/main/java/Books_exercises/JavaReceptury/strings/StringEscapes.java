package Books_exercises.JavaReceptury.strings;

/**
 * StringEscapes.java - pokazuje stosowanie symboli.
 * Note that they may not print correctly on all platforms.
 */
// BEGIN main
public class StringEscapes {
    public static void main(String[] argv) {
        System.out.println("Praktyczne wykorzystanie łańcuchów w Javie:");
        // System.out.println("Alarm lub ostrzeżenie: \a");    // niedostępny
        System.out.println("Znak alarmu zapisany ósemkowo: \007");        
        System.out.println("Znak tabulacji: \t(łańcuch po znaku)");       
        System.out.println("Nowy wiersz: \n(łańcuch po znaku)");          
        System.out.println("Znak Unicode: \u0207");                       
        System.out.println("Znak odwrotnego ukośnika: \\");
    }
}
// END main
