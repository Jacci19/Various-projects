package Books_exercises.JavaReceptury.testing;

import org.junit.*;
import static org.junit.Assert.*;

import domain.Person;

// BEGIN main
public class HamcrestDemoS {

    @Test
    public void testNameConcat() {
        Person p = new Person("Ian", "Darwin");
        String f = p.getFullName();
        assertEquals("Kontatenacja imienia i nazwiska", f, equals("Ian Darwin"));
    }
}
// END main
