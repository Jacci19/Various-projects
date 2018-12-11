package Books_exercises.JavaReceptury.functional;

// BEGIN main
/** "Chodź, nie biegaj" */
public class ReferencesDemo {

    // Zakładamy, że to jest istniejąca metoda, której nazwy nie 
    // chcemy zmieniać.
    public void walk() {
        System.out.println("ReferencesDemo.walk(): Zastępuje wywołanie metody run.");
    }
    
    // To jest nasza główna metoda, która wykonuje metodę walk w nowym
    // wątku.
    public void doIt() {
        Runnable r = this::walk;
        new Thread(r).start();
    }
    
    // Zwyczajna, bardzo prosta metoda main, która wszystko uruchomi.
    public static void main(String[] args) {
        new ReferencesDemo().doIt();
    }
}
// END main
