package Books_exercises.JavaReceptury.testing;

import org.junit.*;
import static org.junit.Assert.*;

import domain.Person;

/** Prosty test klasy Person. */
// BEGIN main
public class PersonTest {

    @Test
    public void testNameConcat() {
        Person p = new Person("Ian", "Darwin");
        String f = p.getFullName();
        assertEquals("Konkatenacja imienia i nazwiska", "Ian Darwin", f);
    }
}
// END main
