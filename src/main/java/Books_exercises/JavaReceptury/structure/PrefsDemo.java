package Books_exercises.JavaReceptury.structure;

import java.util.prefs.Preferences;

/**
 * Przykład zastosowania klady Preferences.
 */
// BEGIN main
public class PrefsDemo {

    public static void main(String[] args) throws Exception {

        // Przygotowujemy obiekt Preferences dla tej aplikacji,
        // posługując się przy tym nazwą klasy.
        Preferences prefs = Preferences.userNodeForPackage(PrefsDemo.class);

        // Pobieramy kilka zapisanych wcześniej preferencji, używając 
        // wartości domyślnych, jeśli jest to pierwsze uruchomienie 
        // programu.
        String text    = prefs.get("textFontName", "lucida-bright");
        String display = prefs.get("displayFontName", "lucida-blackletter");
        System.out.println(text);
        System.out.println(display);

        // Zakładamy, że użytkownik zmienił wartość preferencji - 
        // w takim przypadku zapisujemy ją.
        prefs.put("textFontName", "times-roman");
        prefs.put("displayFontName", "helvetica");

        // Dodajemy kilka kolejnych wartości dla tych, którzy chcieliby
        // zobaczyć, jak w rzeczywistości są zapisywane wartości 
        // preferencji.
        Preferences child = prefs.node("a/b");
        child.putInt("znaczenie", 42);
        child.putDouble("pi", Math.PI);

        // Zapisujemy w formacie XML poddrzewo pierwszego węzła.
        prefs.exportSubtree(System.out);
    }
}
// END main
