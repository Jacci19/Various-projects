package Books_exercises.JavaReceptury.lang;

/**
 * Czy można wywołać statyczną metodę klasy abstrakcyjnej?
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public abstract class AbstractStatic {

    public static void main(String[] argv) {
        // BEGIN main
        System.out.println("Witaj. Odpowiedź brzmi: 'tak'.");
        // Odpowiedź jest nadmiarowa, gdyż udało się nam tu dotrzeć.
        AbstractStatic.foo();
        // END main
    }

    public static void foo() {
        System.out.println("Witaj w metodzie foo. Odpowiedź wciąż brzmi: 'tak'.");
    }

    /* A jeśli się nad tym zastanowić, to okaże się, że faktycznie można 
     * wywoływać statyczne metody klas abstrakcyjnych. Czy to nie właśnie 
     * w taki sposób tworzona jest większość "metod wytwórczych"?
     */
}
