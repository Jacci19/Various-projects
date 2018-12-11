package Books_exercises.JavaReceptury.graphics;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

/** Program przedstawiający kod, który wyświetla tekst wyśrodkowany 
 * w komponencie JComponent.
 * W praktyce wykorzystalibyśmy po prostu komponent JLabel, ten przykład
 * pokazuje jedynie sposób rozwiązania takiego problemu.
 */
// BEGIN main
public class DrawStringDemo2 extends JComponent {

    private static final long serialVersionUID = -6593901790809089107L;
    //-
    String message = "Witaj, Javo!";

    /** Metoda paint() jest wywoływana (przez AWT), gdy nadszedł 
     * czas, by wyświetlić tekst. */
    @Override
    public void paintComponent(Graphics g) {

        // Pobieramy bieżącą czcionkę oraz jej metryki (FontMetrics).
        FontMetrics fm = getFontMetrics(getFont());

        // Dysponując metrykami czcionki, określamy szerokość łańcucha
        // znaków. Odejmujemy ją od szerokości komponentu, dzielimy 
        // wynik przez 2 i w ten sposób wyznaczamy współrzędne punktu,
        // w którym należy wyświetlić tekst.
        int textX = (getSize().width - fm.stringWidth(message))/2;
        if (textX<0)        // Jeśli łańcuch jest zbyt duży, zaczynamy 
            textX = 0;      // w punkcie 0.

        // To samo co wyżej, lecz dla wysokości łańcucha znaków.
        int textY = (getSize().height - fm.getAscent())/2 - fm.getDescent();
        if (textY < 0)
            textY = getSize().height - fm.getDescent() - 1;

        // A teraz wyświetlamy tekst w wyznaczonym punkcie.
        g.drawString(message, textX, textY);
    }
    //-

    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    public static void main(final String[] args) {
        final JFrame jf = new JFrame();
        jf.add(new DrawStringDemo2());
        jf.setBounds(100, 100, 100, 100);
        jf.setVisible(true);
    }
}
// END main
