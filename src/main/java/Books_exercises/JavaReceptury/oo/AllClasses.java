package Books_exercises.JavaReceptury.oo;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

// BEGIN main
public class AllClasses {
    public class Data {    // <1>
        int x;
        int y;
    }
    public void getResults() {
        JButton b = new JButton("Naciśnij mnie");
        b.addActionListener(new ActionListener() { // <2>
            public void actionPerformed(ActionEvent evt) {
                Data loc = new Data();
                loc.x = ((Component)evt.getSource()).getX();
                loc.x = ((Component)evt.getSource()).getY();
                System.out.println("Dziękuję za naciśnięcie");
            }
        });
    }
}

/** Klasy umieszczone w tym samym pliku co AllClasses, które
 * jednak mogą być używane także w innych kontekstach 
 * (powodują jednak wygenerowanie ostrzeżenia).
 */
class AnotherClass {                    // <3>
    // Metody i pola...
    AnotherClass() {
        // Oczywiście zdefiniowanej wcześniej klasy wewnętrznej
        // tutaj nie można używać.
        // Data d = new Data();    // Błąd kompilacji!
    }
}
// END main
