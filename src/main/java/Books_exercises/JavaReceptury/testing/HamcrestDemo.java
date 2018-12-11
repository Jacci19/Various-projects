package Books_exercises.JavaReceptury.testing;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import domain.Person;

// BEGIN main
public class HamcrestDemo {

    @Test
    public void testNameConcat() {
        Person p = new Person("Ian", "Darwin");
        String f = p.getFullName();
        assertThat(f, containsString("Ian"));
        assertThat(f, equalTo("Ian Darwin"));
        assertThat(f, not(containsString("/"))); // Sztuczny przykład, 
                             // mający jedynie pokazać składnię testów.
    }
}
// END main
