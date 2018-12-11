package Books_exercises.JavaReceptury.graphics;

import java.awt.*;
import javax.swing.*;

/** 
 * DropShadow -- wyświetlanie cienia.
 */
// BEGIN main
public class DropShadow extends JComponent {
    /** Wyświetlany tekst. */
    protected String theLabel;
    /** Nazwa czcionki. */
    protected String fontName;
    /** Obiekt czcionki. */
    protected Font theFont;
    /** Wielkość czcionki. */
    protected int fontSize = 18;
    /** Przesunięcie cienia. */
    protected int theOffset = 3;

    /**
     * Przygotowanie graficznego interfejsu użytkownika,
     * ograniczamy się do wszechobecnego wyjątku IllegalArgumentException.
     */
    public DropShadow() {
        this("DropShadow");
    }

    public DropShadow(String theLabel) {
        this.theLabel = theLabel == null ? "DropShadow" : theLabel;
        // Now handle font stuff.
        fontName = "Sans";
        fontSize = 24;
        if (fontName != null || fontSize != 0) {
            theFont = new Font(fontName, Font.BOLD + Font.ITALIC, fontSize);
            System.out.println("Nazwa czcionki " + fontName + 
                                             ", czcionka " + theFont);
        }
        setBackground(Color.green);
    }

    /** Metoda paint() generująca efekt cienia. */
    public void paint(Graphics g) {
        g.setFont(theFont);
        g.setColor(Color.black);
        g.drawString(theLabel, theOffset+30, theOffset+50);
        g.setColor(Color.white);
        g.drawString(theLabel, 30, 50);
    }
}
// END main
