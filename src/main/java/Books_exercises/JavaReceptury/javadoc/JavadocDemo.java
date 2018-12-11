package Books_exercises.JavaReceptury.javadoc;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JPanel;

/**
 * JavadocDemo - prosty program demonstrujący komentarze dokumentujące.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class JavadocDemo extends JPanel {

    /**
     * Tworzymy graficzny interfejs użytkownika.
     * @throws java.lang.IllegalArgumentException w razie uruchomienia 
     * w niedzielę.
     */
    public void JavadocDemo() {
        // Tworzymy i dodajemy przycisk, który jeszcze niczego nie robi.
        Button b = new Button("Witam");
        add(b);                    // Powiązanie przycisku z komponentem.
        // Bardzo kapryśny przykład pokazujący, czego absolutnie nie 
        // należy robić.
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            throw new IllegalArgumentException("W niedzielę nie działam!");
        }
    }

    /** paint() to metoda klasy Component biblioteki AWT, jest ona
     * wywoływana, kiedy należy przerysować zawartość komponentu. Nasz 
     * komponent wyświetla w oknie jedynie kolorowe prostokąty.
     *
     * @param g Obiekt java.awt.Graphics, którego używamy do wywoływania
     * wszystkich metod graficznych.
     */
    public void paint(Graphics g) {
        int w = getSize().width, h = getSize().height;
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, w/2, h);
        g.setColor(Color.GREEN);
        g.fillRect(w/2, 0, w, h);
        g.setColor(Color.BLACK);
        g.drawString("Witamy w Javie", 50, 50);
    }
}
// END main
