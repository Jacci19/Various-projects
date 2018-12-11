package Books_exercises.JavaReceptury.numbers;

import java.util.*;
import java.io.*;

/** Wywołuje "n" razy nextDouble() i nextGaussian().
 */
public class Random4 {
	public static void main(String[] argv) throws IOException {
      // Metoda java.lang.Math.random() jest metodą statyczną, 
      // a zatem, aby jej użyć, nie trzeba tworzyć obiektu klasy Math. 
		Random r = new Random();
		PrintWriter file1 = new PrintWriter(new FileWriter("file1"));
		PrintWriter file2 = new PrintWriter(new FileWriter("file2"));
		for (int i=0; i<10000; i++) {
			file1.println(r.nextDouble());
			file2.println(r.nextGaussian());
		}
		file1.close();
		file2.close();
	}
}
