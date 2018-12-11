package Books_exercises.JavaReceptury.starting;

import com.darwinsys.util.Debug;

public class FetchDebug {
	public static void main(String[] args) {
		String name = "wiersz", value;
		Fetch f = new Fetch();
		Debug.println("pobranie", "Pobieram " + name);
		value = f.fetch(name);
		System.out.println(value);
	}
	public String fetch(String name) {
	    // W rzeczywistości ta metoda odnajdywałaby klucz przekazany jako 
	    // parametr name w jakiejś tabeli lub bazie danych.
		return "Monachomachia";
	}
}
