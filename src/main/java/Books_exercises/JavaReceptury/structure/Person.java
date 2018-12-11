package Books_exercises.JavaReceptury.structure;

/** Prosta klasa używana w niektórych z uwag do tej grupy przykładów.
 * Swoją drogą, to nie jest przykład "najlepszych praktyk": wszystkie 
 * pola tej klasy powinny być zadeklarowane jako prywatne, klasa powinna
 * także posiadać odpowiednie metody set/get.
 */
public class Person {
    private String firstName, lastName;
    public String address;
    public String phone;
    public String Country;
    
    public Person(String f, String l) {
        firstName = f;
        lastName = l;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getName() {
        return getFirstName() + ' ' + getLastName();
    }
    public String toString() {
        return getName();
    }
}