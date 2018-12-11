package Books_exercises.JavaReceptury.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

/** Przykład wykorzystania czcionek Truetype w programach pisanych 
 * w języku Java. Ta klasa pozwala na stosowanie w programach unikalnych
 * czcionek - Twoja aplikacja może używać własnych czcionek, których 
 * użytkownik NIE MUSI wcześniej instalować w JRE (oczywiście może to 
 * zrobić, jeśli ma odpowiednie uprawnienia).
 * <p>
 * Niezależnie od problemów występujących w starszych systemach, klasa 
 * musi korzystać z pakietu Swing, gdyż tylko komponenty Swing mogą 
 * korzystać z czcionek TTF.
 * <p>
 * Ze względu na problemy z zabezpieczeniami klasa NIE działa 
 * w apletach (Applet oraz JApplet). Można jednak zapewnić jej 
 * prawidłowe działanie poprzez zastosowanie odpowiedniego pliku 
 * z polityką bezpieczństwa.
 * @author    Ian Darwin
 * @since 1.3
 */
// BEGIN main
public class TTFontDemo extends JLabel {

    private static final long serialVersionUID = -2774152065764538894L;

    /** Konstruktor klasy TTFontDemo -- tworzy nową czcionkę
     * wczytaną z pliku TTF.
     */
    public TTFontDemo(String fontFileName, String text)
    throws IOException, FontFormatException {
        super(text, JLabel.CENTER);

        setBackground(Color.white);

        // W pierwszej kolejności sprawdzamy, czy można wczytać
        // plik czcionki.
        InputStream is = this.getClass().getResourceAsStream(fontFileName);
        if (is == null) {
            throw new IOException("Nie można otworzyć pliku " + fontFileName);
        }

        // Metoda createFont() tworzy czcionkę o wielkości 1 punktu,
        // odczytanie tekstu tej wielkości byłoby dosyć trudne :-)
        Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);

        // So scale it to 24 pt.
        // Zatem skalujemy czcionkę do wielkości 24 punktów.
        Font ttfReal = ttfBase.deriveFont(Font.PLAIN, 24);

        setFont(ttfReal);
    }

    /** Metoda main() aplikacji TTFontDemo. */
    public static void main(String[] args) throws Exception {

        String DEFAULT_MESSAGE =
            "O szyby deszcz dzwoni, deszcz dzwoni jesienny.";
        // Plik jest wyczytywany jako zasób, dlatego nie musimy
        // przed nim umieszczać ścieżki "graphics/".
        String DEFAULT_FONTFILE = "Kellyag_.ttf";
        String message = args.length == 1 ? args[0] : DEFAULT_MESSAGE;
        JFrame f = new JFrame("Przykład stosowania czcionek aplikacji");

        TTFontDemo ttfd = new TTFontDemo(DEFAULT_FONTFILE, message);
        f.getContentPane().add(ttfd);

        f.setBounds(100, 100, 700, 250);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
// END main
