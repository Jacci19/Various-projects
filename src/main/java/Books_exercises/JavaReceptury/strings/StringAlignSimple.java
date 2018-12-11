package Books_exercises.JavaReceptury.strings;

/* Wyrównanie numery strony w wierszu o długo 70 znaków. */
// BEGIN main
public class StringAlignSimple {

    public static void main(String[] args) {
        // Tworzymy obiekt, który pomoże nam w wyrównywaniu łańcucha.
        StringAlign formatter = new StringAlign(70, StringAlign.Justify.CENTER);
        // Wypróbujmy jego działanie dla strony o numerze "i".
        System.out.println(formatter.format("- i -"));
        // Wypróbujmy go dla strony o numerze 4. Ponieważ formatowanie 
        // jest zoptymalizowane pod kątem łańcuchów znaków, a nie 
        // dla szczególnego przypadku, jakim są numery stron, musimy 
        // przekonwertować numer strony na łańcuch znaków.
        System.out.println(formatter.format(Integer.toString(4)));
    }
}
// END main
