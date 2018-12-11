package Books_exercises.JavaReceptury.darwinsys.swingui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/** Okno dialogowe do wyboru koloru w wersji dla pakietu Swing. 
 * Należy je tworzyć i wyświetlać (setVisible(true)) w standardowy sposób. 
 * <p>
 * Uses Listeners to ensure that Preview button isn't actually needed
 * Korzysta z procedur obsługi zdarzeń, dzięki czemu w rzeczywistości 
 * przycisk Podgląd nie jest potrzebny.
 * @author    Ian Darwin
 */
// BEGIN main
// package com.darwinsys.swingui;
public class FontChooser extends JDialog {

    private static final long serialVersionUID = 5363471384675038069L;

    public static final String DEFAULT_TEXT = "Lorem ipsem dolor";

    // Wyniki:

    /** Czcionka wybrana przez użytkownika. */
    protected Font resultFont = new Font("Serif", Font.PLAIN, 12);
    /** Nazwa wybranej czcionki */
    protected String resultName;
    /** Wielkość wybranej czcionki */
    protected int resultSize;
    /** Czy czcionka ma być pogrubiona */
    protected boolean isBold;
    /** Czy czcionka ma być zapisana kursywą */
    protected boolean isItalic;

    // Pola robocze

    /** Wyświetlany tekst */
    protected String displayText = DEFAULT_TEXT;
    /** Lista do wyboru nazwy czcionki */
    protected JList fontNameChoice;
    /** Pole wyboru wielkości czcionki */
    protected JList fontSizeChoice;
    /** Pola do wyboru typu czcionki */
    JCheckBox bold, italic;

    /** Lista dostępnych wielkości czcionek */
    protected Integer fontSizes[] = {
            8, 9, 10, 11, 12, 14, 16, 18, 20, 24, 30, 36, 40, 48, 60, 72
    };
    /** Indeks domyślnej wielkości czcionki (np. 14 punktów == 4) */
    protected static final int DEFAULT_SIZE = 4;
    /** Obszar prezentacji
     */
    protected JLabel previewArea;

    /** Tworzymy obiekt FontChooser -- określamy tytuł i 
     * pobieramy listę dostępnych czcionek z systemu operacyjnego.
     * Tworzymy interfejs użytkownika pozwalający na wybranie 
     * jednej czcionki i jednej wielkości czcionki.
     */
    public FontChooser(JFrame f) {
        super(f, "Wybieranie czcionek", true);

        Container cp = getContentPane();

        JPanel top = new JPanel();
        top.setBorder(new TitledBorder(new EtchedBorder(), "Font"));
        top.setLayout(new FlowLayout());

        // Zastosowane rozwiązanie zwraca dosyć długą listę; zawiera ona
        // większość czcionek dostępnych w systemie operacyjnym (takich
        // jak Helvetica, Times) oraz czcionki dostępne w języku Java
        // (Lucida, Lucida Bright, Lucida Sans...)
        String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().
            getAvailableFontFamilyNames();

        fontNameChoice = new JList(fontList);
        top.add(new JScrollPane(fontNameChoice));

        fontNameChoice.setVisibleRowCount(fontSizes.length);
        fontNameChoice.setSelectedValue("Serif", true);

        fontSizeChoice = new JList(fontSizes);
        top.add(fontSizeChoice);

        fontSizeChoice.setSelectedIndex(fontSizes.length * 3 / 4);

        cp.add(top, BorderLayout.NORTH);

        JPanel attrs = new JPanel();
        top.add(attrs);
        attrs.setLayout(new GridLayout(0,1));
        attrs.add(bold  =new JCheckBox("Pogrubiona", false));
        attrs.add(italic=new JCheckBox("Kursywa", false));

        // Trzeba pamiętać, że jakakolwiek zmiana ustawień w oknie
        // spowoduje zmianę podglądu czcionki.
        ListSelectionListener waker = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                previewFont();
            }
        };
        fontSizeChoice.addListSelectionListener(waker);
        fontNameChoice.addListSelectionListener(waker);
        ItemListener waker2 = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                previewFont();
            }
        };
        bold.addItemListener(waker2);
        italic.addItemListener(waker2);

        previewArea = new JLabel(displayText, JLabel.CENTER);
        previewArea.setSize(200, 50);
        cp.add(previewArea, BorderLayout.CENTER);

        JPanel bot = new JPanel();

        JButton okButton = new JButton("Wybierz");
        bot.add(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previewFont();
                dispose();
                setVisible(false);
            }
        });

        JButton canButton = new JButton("Anuluj");
        bot.add(canButton);
        canButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Wszystkim wartościom przypisujemy null. 
                // Lepszym rozwiązaniem byłoby przywrócenie wcześniejszych wartości.
                resultFont = null;
                resultName = null;
                resultSize = 0;
                isBold = false;
                isItalic = false;

                dispose();
                setVisible(false);
            }
        });

        cp.add(bot, BorderLayout.SOUTH);

        previewFont(); // zapewniamy, że wyświetlane są aktualne dane!

        pack();
        setLocation(100, 100);
    }

    /** Metoda wywoływana podczas obsługi czynności w celu 
     * pobrania informacji o czcionce, stworzenia odpowiedniego 
     * obiektu i użycia go.
     */
    protected void previewFont() {
        resultName = (String)fontNameChoice.getSelectedValue();
        String resultSizeName = fontSizeChoice.getSelectedValue().toString();
        int resultSize = Integer.parseInt(resultSizeName);
        isBold = bold.isSelected();
        isItalic = italic.isSelected();
        int attrs = Font.PLAIN;
        if (isBold) attrs = Font.BOLD;
        if (isItalic) attrs |= Font.ITALIC;
        resultFont = new Font(resultName, attrs, resultSize);
        // System.out.println("resultName = " + resultName + "; " +
        //         "resultFont = " + resultFont);
        previewArea.setFont(resultFont);
        pack();             // Zapewniamy że okno dialogowe będzie 
                            // wystarczająco duże.
    }

    /** Zwraca wybraną nazwę czcionki */
    public String getSelectedName() {
        return resultName;
    }
    /** Zwraca wybrany rozmiar czcionki */
    public int getSelectedSize() {
        return resultSize;
    }

    /** Zwraca wybraną czcionkę lub null */
    public Font getSelectedFont() {
        return resultFont;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
        previewArea.setText(displayText);
        previewFont();
    }

    public JList getFontNameChoice() {
        return fontNameChoice;
    }

    public JList getFontSizeChoice() {
        return fontSizeChoice;
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }
}
// END main
