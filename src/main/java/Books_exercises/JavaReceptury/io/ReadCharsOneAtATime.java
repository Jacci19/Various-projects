package Books_exercises.JavaReceptury.io;

import java.io.IOException;
import java.io.Reader;

/** Takie rozwiązanie działa, ale napewno będzie bardzo wolne. */
// BEGIN main
public class ReadCharsOneAtATime {

    void doFile(Reader is) throws IOException {
        int c;
        while ((c=is.read( )) != -1) {
            System.out.print((char)c);
        }
    }
}
// END main
