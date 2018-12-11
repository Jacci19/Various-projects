// BEGIN main
package Books_exercises.JavaReceptury.darwinsys.swingui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.*;

/** Grupa metod pomocniczych ułatwiających tworzenie programów
 * wielojęzycznych.
 * Aby zapewnić jak najprostsze możliwości użycia, wszystkie 
 * metody pomocnicze są statyczne.
 */
public class I18N {

    /** Metoda pomocnicza służąca do tworzenia obiektów JButton */
    public static JButton mkButton(ResourceBundle b, String name) {
        String label;
        try { label = b.getString(name+".label"); }
        catch (MissingResourceException e) { label=name; }
        return new JButton(label);
    }

    /** Metoda pomocnicza służąca do tworzenia obiektów JMenu */
    public static JMenu mkMenu(ResourceBundle b, String name) {
        String menuLabel;
        try { menuLabel = b.getString(name+".label"); }
        catch (MissingResourceException e) { menuLabel=name; }
        return new JMenu(menuLabel);
    }

    /** Metoda pomocnicza służąca do tworzenia obiektów JMenuItem */
    public static JMenuItem mkMenuItem(ResourceBundle b,
            String menu, String name) {

        String miLabel;
        try { miLabel = b.getString(menu + "." + name + ".label"); }
        catch (MissingResourceException e) { miLabel=name; }
        String key = null;
        try { key = b.getString(menu + "." + name + ".key"); }
        catch (MissingResourceException e) { key=null; }

        if (key == null)
            return new JMenuItem(miLabel);
        else
            return new JMenuItem(miLabel, key.charAt(0));
    }

    /** Pokazuje informacyjne okno dialogowe JOptionPane */
    public static void mkDialog(ResourceBundle b,JFrame parent,
        String dialogTag, String titleTag, int messageType) {
            JOptionPane.showMessageDialog(
                parent,
                getString(b, dialogTag, "BRAK TEKSTU OKNA DIALOGOWEGO: " + dialogTag),
                getString(b, titleTag, "BRAK TYTUŁU OKNA DIALOGOWEGO: "  + titleTag),
                messageType);
    }

    /** Pobiera łańcuch znaków (dla okien dialogowych, etykiet itd.) */
    public static String getString(ResourceBundle b, String name, String dflt) {
        String result;
        try {
            result = b.getString(name);
        } catch (MissingResourceException e) {
            result = dflt;
        }
        return result;
    }
}
// END main
