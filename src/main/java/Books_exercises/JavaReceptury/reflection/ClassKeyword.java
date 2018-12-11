package Books_exercises.JavaReceptury.reflection;

import java.util.Calendar;
/**
 * Przedstawienie sposbów wykorzystania słowa kluczowego Class 
 * oraz metody getClass().
 * Słowo kluczowe class można zastosować dla dowolnego typu znanego 
 * w czasie kompilacji.
 * 
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class ClassKeyword {
    public static void main(String[] argv) {
        // BEGIN main
        System.out.println("Próba wykorzystania słowa kluczowego ClassName.class: ");
        System.out.println("Klasa Object: " + Object.class);
        System.out.println("Klasa String: " + String.class);
        System.out.println("Klasa String[]: " + String[].class);
        System.out.println("Klasa Calendar: " + Calendar.class);
        System.out.println("Bieżąca klasa: " + ClassKeyword.class);
        System.out.println("Klasa typu int: " + int.class);
        System.out.println();
        System.out.println("Próba metody instance.getClass():");
        System.out.println("Robin Nieustraszony".getClass());
        System.out.println(Calendar.getInstance().getClass());
        // END main
    }
}
