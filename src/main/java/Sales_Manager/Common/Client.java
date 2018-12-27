package Sales_Manager.Common;

import com.darwinsys.geo.Country;

public class Client {

    private String name;
    private Country country;
    private Boolean isAdult;

    public Client(String name, Country country, Boolean isAdult) {
        this.name = name;
        this.country = country;
        this.isAdult = isAdult;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    @Override
    public String toString() {
        return "\nClient{" +
                "name='" + name + '\'' +
                ", country=" + country +
                ", isAdult=" + isAdult +
                '}';
    }
}
