package Books_exercises.JavaReceptury.email;

public class Alias {
	/** Nazwa aliasu */
	protected String name;
	/** Adres e-mail dla tego aliasu */
	protected String address;

	public Alias(String n, String addr) {
		name = n;
		address = addr;
	}

	public String toString() {
		return name + " = " + address;
	}

	/** Pobierz nazw� */
	public String getName() {
		return name;
	}

	/** Ustaw nazw� */
	public void setName(String name) {
		this.name = name;
	}

	/** Pobierz adres */
	public String getAddress() {
		return address;
	}

	/** Ustaw adres */
	public void setAddress(String address) {
		this.address = address;
	}
}
