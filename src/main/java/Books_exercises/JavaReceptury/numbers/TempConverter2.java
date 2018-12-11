package Books_exercises.JavaReceptury.numbers;


/* Wyświetla tabelę temperatur w stopniach Fahrenheita i Celsjusza 
 * sformatowaną w nieco bardziej atrakcyjnej postaci.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class TempConverter2 extends TempConverter {

    public static void main(String[] args) {
        TempConverter t = new TempConverter2();
        t.start();
        t.data();
        t.end();
    }

    protected void print(float f, float c) {
        System.out.printf("%6.2f  %6.2f%n", f, c);
    }

    protected void start() {
        System.out.println("Fahr    Celsjusza");
    }

    protected void end() {
        System.out.println("-------------------");
    }
}
// END main
