package Books_exercises.JavaReceptury.oo.shapes;

import java.util.ArrayList;
import java.util.Collection;

// BEGIN main
/** Część programu głównego wykorzystującego obiekty
 * klasy Shape. */
public class ShapeDriver {

    Collection<Shape> allShapes;    // Kolekcja tworzona w konstruktorze,
                          // który nie jest przedstawiony w tym przykładzie.


    /** Odwołuje się kolejno do wszystkich obiektów Shape 
     * w kolekcji i oblicza ich pola. Metoda nie może używać
     * metody forEach wprowadzonej w wersji języka Java 8,
     * gdyż zmienna total musiałaby być sfinalizowana, a to
     * przekreślałoby sens tego przykładu :-) 
     */
    public double totalAreas() {
        double total = 0.0;
        for (Shape s : allShapes) {
            total += s.computeArea();
        }
        return total;
    }
    // END main
    ShapeDriver() {
        allShapes = new ArrayList<>();
        allShapes.add(new Circle());
        allShapes.add(new Rectangle());
    }
    
    public static void main(String[] args) {
        System.out.println(new ShapeDriver().totalAreas());
    }
}
