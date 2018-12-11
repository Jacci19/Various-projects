package Books_exercises.JavaReceptury.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/* Przykład zamykania okna JFrame.
 * @author Ian Darwin
 */
// BEGIN main
public class WindowDemo2 extends JFrame {

    public static void main(String[] argv) {
        JFrame f = new WindowDemo2();
        f.setVisible(true);
    }
    public WindowDemo2() {
        setSize(200, 100);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowDemoAdapter());
    }

    /** Nazwana klasa wewnętrzna służąca do zamykania okna. */
    class WindowDemoAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            // Losowe zamykanie z prawdopodobieństwem mniej więcej 1 do 3.
            if (Math.random() > 0.666) {
                System.out.println("Do zobaczenia!");
                WindowDemo2.this.setVisible(false);    // Zamknięcie okna.
                WindowDemo2.this.dispose();        // Zwolnienie zasobów.
                System.exit(0);
            } 
            System.out.println("Poprosiłeś o zamknięcie okna, lecz okno nie wyraziło zgody.");
        }
    }
}
// END main
