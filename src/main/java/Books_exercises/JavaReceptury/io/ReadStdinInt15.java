package Books_exercises.JavaReceptury.io;

import java.util.Scanner;
/**
 * Odczyt liczby ze standardowego strumienia wejściowego, 
 * wymaga werji Java 1.5.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class ReadStdinInt15 {
	public static void main(String[] ap) {
		int val;
		Scanner sc = new Scanner(System.in);      // Wymaga wersji Java 5
		val = sc.nextInt();
		System.out.println("Odczytano liczbę: " + val);
	}
}
