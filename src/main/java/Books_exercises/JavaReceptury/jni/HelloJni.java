package Books_exercises.JavaReceptury.jni;

// BEGIN main
/**
 * Prosta klasa demonstrująca wykorzystanie Java Native Interface 1.1
 */
public class HelloJni {
  int myNumber = 42;      // Zmienna używana od pokazania sposobu 
                          // przekazywania argumentów.

  // Deklaracja klasy rodzimej.
  public native void displayHelloJni();

  // Metoda main aplikacji; wywołuje metodę display klasy rodzimej.
  public static void main(String[] args) {
    System.out.println("Uruchamiamy program HelloJni; args.length="+
        args.length+"...");
    for (int i=0; i<args.length; i++)
                       System.out.println("args["+i+"]="+args[i]);
    HelloJni hw = new HelloJni();
    hw.displayHelloJni();    // Wywołujemy funkcję rodzimą.
    System.out.println("Z powrotem w Javie, \"myNumber\" ma teraz wartość " 
          + hw.myNumber);
  }

  // Statyczne bloki kodu są wykonywane tylko raz, podczas ładowania 
  // pliku klasowego.
  static {
    System.loadLibrary("hello");
  }
}
// END main
