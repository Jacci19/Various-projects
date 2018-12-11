package Books_exercises.JavaReceptury.structure;

import java.util.Properties;

/**
 * Program przedstawia wykorzystanie klas Properties.
 * see HashTableDemo, for the older Hashtable.
 */
// BEGIN main
public class PropsCompanies {

    public static void main(String[] argv) throws java.io.IOException {

        Properties props = new Properties();

        // Podajemy dane.
        props.put("Adobe", "Mountain View, CA");
        props.put("IBM", "White Plains, NY");
        props.put("Learning Tree", "Los Angeles, CA");
        props.put("Microsoft", "Redmond, WA");
        props.put("Netscape", "Mountain View, CA");
        props.put("O'Reilly", "Sebastopol, CA");
        props.put("Sun", "Mountain View, CA");

        // Teraz pobieramy dodatkowe dane.
        props.load(System.in);

        // A teraz wyświetlamy wszystkie właściwości
        // w standardowym strumieniu wyjściowym System.out.
        props.list(System.out);
    }
}
// END main
