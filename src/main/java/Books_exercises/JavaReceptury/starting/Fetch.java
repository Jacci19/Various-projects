package Books_exercises.JavaReceptury.starting;

public class Fetch {
	public static void main(String[] args) { 
	    Fetch f = new Fetch();
		String name = "wiersz", value;
		if (System.getProperty("debug.fetch") != null) {
			System.err.println("Pobieramy " + name);
		}
		value = f.fetch(name);
	}
	public String fetch(String name) {
	    // W praktyce, ta metoda wyszukalaby "name" w jakiejś tabeli lub 
	    // bazie danych.
		return "jabberwocky";
	}
}
