package Books_exercises.JavaReceptury.darwinsys.swingui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Połączenie etykiety i pola tekstowego. Do stworzenia tego komponentu 
 * zainspirował mnie element sterujący LabelText opublikowany w książce
 * Guya Eddona pt.: ActiveX Components (2. wydanie, strona 203). 
 * Ten komponent został jednak stworzony znacznie prościej.
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
// package com.darwinsys.swingui;
public class LabelText extends JPanel implements java.io.Serializable {

    private static final long serialVersionUID = -8343040707105763298L;
    /** Komponent etykiety. */
    protected JLabel theLabel;
    /** Komponent pola tekstowego. */
    protected JTextField theTextField;
    /** Używana czcionka. */
    protected Font myFont;

    /** Tworzymy obiekt bez wartości początkowych.
     * Aby klasa mogła być komponentem JavaBeans, MUSI mieć konstruktor 
     * bezargumentowy.
     */
    public LabelText() {
        this("(Test etykiety)", 12);
    }

    /** Tworzymy obiekt z etykietą o podanej treści i polem tekstowym 
     * o domyślnej wielkości. 
     */ 
    public LabelText(String label) {
        this(label, 12);
    }

    /** Tworzymy obiekt z etykietą o podanej treści i polem tekstowym 
     * o podanej wielkości. 
     */
    public LabelText(String label, int numChars) {
        this(label, numChars, null);
    }

    /** Tworzymy obiekt z etykietą o podanej treści, polem tekstowym 
     * o podanej wielkości i "dodatkowym" komponentem. 
     * @param label Wyświetlany tekst.
     * @param numChars Wielkość pola tekstowego.
     * @param extra Trzeci komponent, taki jak przycisk Anuluj.
     * Może być równy null, w takim przypadku zostaną wyświetlone
     * jedynie etykieta i pole tekstowe.
     */
    public LabelText(String label, int numChars, JComponent extra) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        theLabel = new JLabel(label);
        add(theLabel);
        theTextField = new JTextField(numChars);
        add(theTextField);
        if (extra != null) {
            add(extra);
        }
    }

    /** Pobieramy wyrównanie etykiety w poziomie. */
    public int getLabelAlignment() {
        return theLabel.getHorizontalAlignment();
    }

    /** Określamy wyrównanie etykiety w poziomie. */
    public void setLabelAlignment(int align) {
        theLabel.setHorizontalAlignment(align);
    }

    /** Pobieramy tekst wyświetlany aktualnie w polu tekstowym. */
    public String getText() {
        return theTextField.getText();
    }

    /** Określamy tekst, który będzie wyświetlony w polu tekstowym. */
    public void setText(String text) {
        theTextField.setText(text);
    }

    /** Pobieramy tekst etykiety. */
    public String getLabel() {
        return theLabel.getText();
    }

    /** Określamy tekst etykiety. */
    public void setLabel(String text) {
        theLabel.setText(text);
    }

    /** Określamy czcionkę używaną w obu komponentach. */
    public void setFont(Font f) {
        // Wywołanie super() wykonywane w konstruktorze tej klasy
        // może spowodować wywołanie metody setFont() (z metody
        // Swing.LookAndFeel.installColorsAndFont), jeszcze zanim
        // utworzymy nasz komponent, obchodzimy ten problem.
        if (theLabel != null)
            theLabel.setFont(f);
        if (theTextField != null)
            theTextField.setFont(f);
    }

    /** Dodajemy ActionListener, aby otrzymywać zdarzenia akcyjne z pola 
        tekstowego. */
    public void addActionListener(ActionListener l) {
        theTextField.addActionListener(l);
    }

    /** Usuwamy ActionListener z pola tekstowego. */
    public void removeActionListener(ActionListener l) {
        theTextField.removeActionListener(l);
    }
}
// END main
