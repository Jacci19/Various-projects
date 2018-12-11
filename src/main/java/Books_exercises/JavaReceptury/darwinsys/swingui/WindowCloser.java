// BEGIN main
package Books_exercises.JavaReceptury.darwinsys.swingui;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** WindowCloser - oczekujemy na zdarzenie WindowClosing, 
 * i w odpowiedzi na nie wywołujemy metody setVisible(false)
 * (czyli chowamy okno) oraz dispose() (zwalniamy jego zasoby).
 * @deprecated W przypadku prostych operacji zamykania należy
 *   użyć metody JFrame.setDefaultCloseOperation().
 */
public class WindowCloser extends WindowAdapter {

    /** Zamykane okno. */
    Window win;

    /** True, jeśli dodatkowo mamy zakończyć działanie programu. */
    boolean doExit = false;

    /** Konstruktor klasy powodujący jedynie zamknięcie okna. */
    public WindowCloser(Window w) {
        this(w, false);
    }

    /** Konstruktor klasy zapewniający kontrolę nad tym, kiedy
     * okno zostanie zamknięte. */
    public WindowCloser(Window w, boolean exit) {
        win = w;
        doExit = exit;
    }

    /** Metoda wywoływana przez AWT, kiedy użytkownik spróbuje
     * zamknąć okno. */
    public void windowClosing(WindowEvent e) {
        win.setVisible(false);
        win.dispose();
        if (doExit)
            System.exit(0);
    }
}
// END main
