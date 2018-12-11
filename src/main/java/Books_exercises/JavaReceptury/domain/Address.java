package Books_exercises.JavaReceptury.domain;

// BEGIN main
import javax.persistence.*;

@Entity
public class Address {

    private int id;

    private String streetAddress;
    private String city;
    private String country;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }
    // Pozostałe metody pominięte w celu skrócenia kodu przykładu.
    // END main
    
    public void setId(int id) {
            this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
