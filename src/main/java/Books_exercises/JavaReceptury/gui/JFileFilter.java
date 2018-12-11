package Books_exercises.JavaReceptury.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// BEGIN main
/** Prosta klasa FileFilter operująca na rozszerzeniach plików,
 * podobnie jak klasa ExtensionFilter dostarczana jako przykład 
 * JDK. Ma ona być obsługiwana w przyszłych wersjach pakietu 
 * Swing.
 */
class JFileFilter extends javax.swing.filechooser.FileFilter {
    protected String description;
    protected List<String> exts = new ArrayList<String>();

    public void addType(String s) {
        exts.add(s);
    }

    /** Zwraca true jeśli dany plik jest akceptowany przez filtr. */
    public boolean accept(File f) {
        // Mała sztuczka: jeśli nie zostanie zastosowana, to 
        // w oknie dialogowym pojawią się wyłącznie nazwy 
        // katalogów zakończone jednym z podanych rozszerzeń.
        if (f.isDirectory()) {
            return true;

        } else if (f.isFile()) {
            for (String ext : exts) {
                if (f.getName().endsWith(ext))
                    return true;
            }
        }

        // Plik, który nie pasuje, lub jakieś dziwactwo (np. plik sterownika
        // w systemie Unix).
        return false;
    }

    /** Określa opis danego filtra. */
    public void setDescription(String s) {
        description = s;
    }
    /** Zwraca opis filtra. */
    public String getDescription() {
        return description;
    }
}
// END Main
