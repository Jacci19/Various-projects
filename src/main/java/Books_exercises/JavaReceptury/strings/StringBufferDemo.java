package Books_exercises.JavaReceptury.strings;

/**
 * StringBuilderDemo: program tworzy ten sam łańcuch znaków na trzy 
 * różne sposoby.
 */
// BEGIN main
public class StringBufferDemo {

    public static void main(String[] argv) {

       String s1 = "Witaj" + ", " + "świecie";
       System.out.println(s1);

       // Tworzymy obiekt StringBuffer i dodajemy do niego łańcuchy znaków.
       StringBuffer sb2 = new StringBuffer();
       sb2.append("Witaj");
       sb2.append(',');
       sb2.append(' ');
       sb2.append("świecie");

       // Pobieramy zawartość obiektu StringBuffer w formie 
       // obiektu String i wyświetlamy ją.
       String s2 = sb2.toString();
       System.out.println(s2);

       // Teraz ponownie wykonujemy te same czynności co wcześniej,
       // lecz tym razem w sposób bardziej zwięzły, przypominający
       // rozwiązania faktycznie stosowane podczas pisania programów
       // w Javie.

       System.out.println(
         new StringBuffer()
           .append("Witaj")
           .append(',')
           .append(' ')
           .append("świecie"));

    }
}
// END main
