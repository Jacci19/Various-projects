package Books_exercises.JavaReceptury.gui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Przykład zamykania okna AWT.
 * @author Ian Darwin
 */
// BEGIN main
public class WindowDemo extends Frame {

    public static void main(String[] argv) {
        Frame f = new WindowDemo();
        f.setVisible(true);
    }
    public WindowDemo() {
        setSize(200, 100);
        addWindowListener(new WindowDemoAdapter());
    }

    /** Nazwana klasa wewnętrzna zamykająca okno. */
    class WindowDemoAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            // Losowe zamykanie z prawdopodobieństwem, mniej więcej, 1 do 3.
            if (Math.random() > 0.666) {
                System.out.println("Goodbye!");
                WindowDemo.this.setVisible(false);    // Zamknięcie okna.
                WindowDemo.this.dispose();        // Zwolnienie zasobów.
                System.exit(0);
            } 
            System.out.println("Poprosiłeś o zamknięcie okna, lecz okno nie wyraziło zgody.");
        }
    }
}
// END main
