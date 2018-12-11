package Books_exercises.JavaReceptury.environ;

import java.io.IOException;

/**
 * Wyświetla jedną lub kilka właściwości systemowych.
 */
// BEGIN main
public class SysPropDemo {
    public static void main(String[] argv) throws IOException {
        if (argv.length == 0)
            // BEGIN sysprops
            System.getProperties().list(System.out);
            // END sysprops
        else {
            for (String s : argv) {
                System.out.println(s + " = " + 
                    System.getProperty(s));
            }
        }
    }
}
// END main
