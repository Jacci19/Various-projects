package Books_exercises.Rychlicki.Rychlicki_2_Javadoc;

/** Klasa <code>Z19_3</code> stanowi prosty przykład aplikacji
 * konsolowej.Początki programowania w wielu językach zawierają ten
 * klasyczny przykład - wyświetlanie na konsoli napisu <b>Hello
 * World!</b> czyli <i>Witaj Swiecie!</i>.
 * <p>To radosny "okrzyk", wydawany przez każdego początkującego
 * programistę, po napisaniu pierwszego prostego programu w poznawanym
 * języku.</p>
 * <p>Zobacz przykłady takich programów:
 * <a href="http://en.wikipedia.org/wiki/Hello_world_program_examples"
 * target="_blank"> Hello world program examples.</a></p>
 */
public class Z19_3 {
    /** Identyfikator stałej (zmiennej, której wartości nie możemy zmienić),
     * zawierającej łańcuch znaków <code>Hello World!"</code>. Zwyczajowo
     * takie identyfikatory zapisujemy wielkimi literami. Prywatna stała
     * <code>HELLO</code> jest dostępna wyłącznie wewnątrz klasy
     * <code>Z19_3</code>. Zastosowanie stałej przedstawimy w dwóch
     * przykładach:
     * <p><b>Przykład 1.</b>
     * <code><br>static String hello() {<br>&nbsp;&nbsp;&nbsp;&nbsp;
     * return HELLO;<br>}</code></p>
     * <p><b>Przykład 2.</b>
     * <code><br>System.out.println("Jeszcze raz: "+HELLO");</code></p>
     */
    private static final String HELLO = "Hello World!";
    /** Metoda zwraca łańcuch znaków - jest to napis zawarty w stałej
     * <code>HELLO</code>. Metoda jest widoczna w pakiecie. Metody z innych
     * klas pakietu mogą tą metodę wywołać: <code>Z19_3.hello();</code>
     */
    static String hello() {
        return HELLO;
    }
    /** Metoda <code>main()</code> jest miejscem, od którego rozpoczyna się
     * wykonywanie programu. Program może składać się z wielu klas, ale
     * tylko jedna z nich może zawierać publiczną, statyczną i nie
     * zwracającą żadnej wartości (<code>public static void</code>) metod�
     * <code>main()</code>. Metoda <code>main()</code>
     * posiada obowiązkowo jeden parametr.<br>
     * Metodę <code>main()</code> możemy uruchomić wewnątrz innej klasy:
     * <p><b>Przykład 1.</b><code><br>Z19_3.main(null);<br>}<br></code>
     * Do metody <code>main()</code> z klasy <code>Z19_3</code> nie
     * przekazano żadnych parametrów.</p>
     * <p><b>Przykład 2.</b><code><br>String[] p = {"par1", "par2"};<br>
     * Z19_3.main(p);<br></code> Do metody <code>main()</code> z klasy
     * <code>Z19_3</code> przekazano dwa parametry <code>"par1"</code>
     * i <code>"par2".</code></p>
     * @param args tablica argumentów (egzemplarzy klasy <code>String
     * </code>), przekazywana przez system do metody <code>main()</code>.
     * Identyfikator <code>args</code> jest skrótem słowa argumenty (ang.
     * <i>arguments</i>) i może być zastąpiony dowolnym innym, poprawnie
     * zbudowanym identyfikatorem.
     */
    public static void main(String[] args) {
        System.out.println(hello());
        System.out.println("Jeszcze raz: "+HELLO);
    }
    /** Bezparametrowy konstruktor <code>Z19_3()</code> pozwala na tworzenie
     * obiektów klasy <code>Z19_3</code> przez inne klasy. Umożliwia to
     * wywoływanie metody <code>hello()</code> i metody <code>main()</code>
     * zdefiniowanej w klasie <code>Z19_3</code> przez te klasy.<br>
     * W przypadku metody <code>main()</code> musimy przekazać parametr -
     * tablicę argumentów. Parametr ten może być tablicą pustą.<br>
     * <p><b>Przykład</b><br>
     * <code>Z19_3 x = new Z19_3();<br>
     * System.out.println(x.hello());<br>
     * x.main(null);<br></code></p>
     */
    public Z19_3(){}
}