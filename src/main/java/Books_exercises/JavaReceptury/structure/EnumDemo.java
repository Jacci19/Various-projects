package Books_exercises.JavaReceptury.structure;

public class EnumDemo {

	enum Color {
		RED, AMBER, GREEN;
	};

	public static void main(String[] args) {
		Color color = Color.RED;
		String line = getLine();
		if (line != null) {
			// Metoda valueOf zwraca wuage na wielkość liter, 
		    // należy zatem zmienić "dane prowadzone przez użytkownika" 
		    // by były zapisane wielkimi literami, gdyż właśnie w taki sposób
		    // (na mocy 30-letniej konwencji) są zapisywane wartości 
		    // typów wyliczeniowych.
			color = Color.valueOf(line.toUpperCase());
		}
		switch (color) {
		case RED:
			System.out.println("STOP");
			break;
		case GREEN:
			System.out.println("MOŻNA JECHAĆ");
			break;
		case AMBER:
			if (driverIsCrazy()) {
				System.out.println("Grzej ile wlezie!");
			} else {
				System.out.println("Zjechać ze skrzyżowania!");
			}
			break;
		}
	}

	private static boolean driverIsCrazy() {
		return false;	// Nie ja, w życiu!
	}

	static String[] lines = { "Green", "amber", "RED" };
	static int i = 0;

	private static String getLine() {
		return lines[i++%3];
	}
}
