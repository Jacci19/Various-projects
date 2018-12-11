package Books_exercises.JavaReceptury.dir_file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


// BEGIN main
/** Klasa odpowiedzialna za selekcję plików, na jakich operuje 
 * program Find.
 * Wystarczy tworzyć metody setTTTFilter(). 
 * Gdyby miała być możliwa obsługa złożonych warunków, takich
 * jak: -n "*.html" -a \( -size < 0 -o mtime < 5 \)
 * to musiałaby to być złożona struktura danych.
 */
public class FindFilter implements FilenameFilter {
    boolean sizeSet;
    int size;
    String name;
    Pattern nameRE;
    boolean debug = false;

    void setSizeFilter(String sizeFilter) {
        size = Integer.parseInt(sizeFilter);
        sizeSet = true;
    }

    /** Konwertuje podany wzorzec znaków wieloznacznych na postać
     * używaną wewnętrznie - czyli wyrażenie regularne. 
     */
    void setNameFilter(String nameFilter) {
        name = nameFilter;
        StringBuilder sb = new StringBuilder('^');
        for (char c : nameFilter.toCharArray()) {
            switch(c) {
                case '.':    sb.append("\\."); break;
                case '*':    sb.append(".*"); break;
                case '?':    sb.append('.'); break;
                // Niektóre znaki mają specjalne znaczenie w wyrażeniach  
                // regularnych i muszą być odpowiednio oznaczane.
                case '[':    sb.append("\\["); break;
                case ']':    sb.append("\\]"); break;
                case '(':    sb.append("\\("); break;
                case ')':    sb.append("\\)"); break;
                default:    sb.append(c); break;
            }
        }
        sb.append('$');
        if (debug)
            System.out.println("RE=\"" + sb + "\".");
        try {
            nameRE = Pattern.compile(sb.toString());
        } catch (PatternSyntaxException ex) {
            System.err.println("Błąd: nie udało się skompilować " +
                " wyrażenia regularnego: " + sb.toString() + " : " + ex);
        }
    }

    /** Realizacja filtrowania. W tym przypadku wyłącznie 
     * na podstawie nazwy.
     */
    public boolean accept(File dir, String fileName) {
        File f = new File(dir, fileName);
        if (f.isDirectory()) {
            return true;    // Zezwalamy na rekurencję.
        }

        if (nameRE != null) {
            return nameRE.matcher(fileName).matches();
        }

        // TODO Obsługa wielkości plików.

        // Cała reszta...
        return false;
    }
    
    public String getName() {
        return name;
    }
}
// END main
