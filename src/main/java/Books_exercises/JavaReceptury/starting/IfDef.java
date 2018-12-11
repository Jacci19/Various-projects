package Books_exercises.JavaReceptury.starting;

/** Prosty test sprawdzający czy kompilatory usuwają nieosiągalny kod. */
public class IfDef {
	public static void main(String[] argv) {
		final boolean DEBUG = false;
		System.out.println("Witaj, świecie");
		if (DEBUG)
			System.out.println("Życie jest podróżą, nie celem.");
	}
}
