package Books_exercises.JavaReceptury.darwinsys.util;

import java.awt.Window;
import java.awt.event.*;

/** A WindowCloser - oczekujemy na zdarzenie Window Closing, 
 * i w odpowiedzi na nie wywołujemy metodę setVisible(false)
 * (czyli chowamy okno) oraz dispose() (zwalniamy jego zasoby).
 * @author Ian F. Darwin, ian@darwinsys.com
 */
public class WindowCloser extends WindowAdapter {
    /** Zamykane okno */
    Window win;
    /** True, jeśli dodatkowo mamy zakończyć działanie programu. */
    boolean doExit = false;

    public WindowCloser(Window w) {
        this(w, false);
    }
    public WindowCloser(Window w, boolean exit) {
        win = w;
        doExit = exit;
    }
    public void windowClosing(WindowEvent e) {
        win.setVisible(false);
        win.dispose();
        if (doExit)
            System.exit(0);
    }
}
