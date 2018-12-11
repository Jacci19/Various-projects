package Books_exercises.JavaReceptury.darwinsys.swingui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** Narzędzia do obsługi GUI (graficznego interfejsu użytkownika).
 */
// BEGIN main
// package com.darwinsys.swingui;
public class UtilGUI {

    /** Centrujemy obiekty Window, Frame, JFrame, Dialog i tak dalej. */
    public static void centre(final Window w) {
        // Po "spakowaniu" komponentów umieszczonych w pojemnikach
        // Frame lub Dialog wyświetlamy je na środku ekranu.
        Dimension us = w.getSize(), 
            them = Toolkit.getDefaultToolkit().getScreenSize();
        int newX = (them.width - us.width) / 2;
        int newY = (them.height- us.height)/ 2;
        w.setLocation(newX, newY);
    }

    /** Centrujemy pojemniki Window, Frame, JFrame, Dialog i tak dalej, 
     * ale przy użyciu metody zgodnej ze słownictwem amerykańskim :-)
     */
    public static void center(final Window w) {
        UtilGUI.centre(w);
    }

    /** Maksymalizacja okna w brutalny sposób. */
    public static void maximize(final Window w) {
        Dimension them = 
            Toolkit.getDefaultToolkit().getScreenSize();
        w.setBounds(0,0, them.width, them.height);
    }
    
    /** 
     * Skopiowanie łańcucha znaków do schowka systemowego
     */
    public static void setSystemClipboardContents(Component c, String srcData) {
        if (srcData != null) {
            Clipboard clipboard = c.getToolkit().getSystemClipboard();
            StringSelection contents = new StringSelection(srcData);
            clipboard.setContents(contents, new ClipboardOwner() {
                public void lostOwnership(Clipboard clipboard,
                    Transferable contents) {

                    // Nie ważne...
                }
            });
        }
    }

    /** Wyświetlamy pytanie z opcjami odpowiedzi "Tak/Nie", zwracamy true, 
     * jeśli użytkownik wybrał odpowiedź "Tak".
     */
    public static boolean confirm(JFrame parent, String message) {
        int confirm = JOptionPane.showConfirmDialog(parent, message, "Potwierdź", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // Tylko wybranie odpowiedzi "Tak" pozwoli zwrócić wartość true
        return confirm == 0;
    }
    
    /** Zapisujemy wartości X i Y w przekazanym obiekcie Preferences.
     */
    public static void setSavedLocation(
        final Preferences pNode, final Window w) {

        Point where = w.getLocation();
        int x = (int)where.getX();
        pNode.putInt("mainwindow.x", Math.max(0, x));
        int y = (int)where.getY();
        pNode.putInt("mainwindow.y", Math.max(0, y));
    }

    /** Pobieramy wartości X i Y z przekazanego obiektu Preferences.
     */
    public static Point getSavedLocation(final Preferences pNode) {
        int savedX = pNode.getInt("mainwindow.x", -1);
        int savedY = pNode.getInt("mainwindow.y", -1);
        return new Point(savedX, savedY);
    }
    
    /** 
     * Rejetrujemy położenie okna w kolejnych uruchomieniach aplikacji;
     * położenie jest zapisywane w formie obiektu Preferences, który 
     * zostaje przekazany w wywołaniu metody; do okna dołączany jest
     * obiekt ComponentListener.
     */
    public static void monitorWindowPosition(
        final Window w, final Preferences pNode) {

        // Jeśli jest, to pobieramy aktualnie zapisane położenie okna.
        Point p = getSavedLocation(pNode);
        int savedX = (int)p.getX();
        int savedY = (int)p.getY();
        if (savedX != -1) {
            // Przenosimy okno w zapamiętane miejsce.
            w.setLocation(savedX, savedY);
        } else {
            // Nie ma zapamiętanego położenia, centrujemy okno.
            centre(w);
        }
        // Teraz upewniamy się, że jeśli użytkownik przesunie okno
        // to jego nowe położenie zostanie zapamiętane.
        w.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                setSavedLocation(pNode, w);
            }
        });
    }
}
// END main
