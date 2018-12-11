package Books_exercises.JavaReceptury.io;

import java.io.*;

/** Prezentacja tworzenia czytelników i pisarzy używających określonego 
 * spsobu kodowania.
 */
public class UseConverters {
	public static void main(String[] args) {
		try {
			BufferedReader fromKanji = new BufferedReader(
				new InputStreamReader(
					new FileInputStream("kanji.txt"), "EUC_JP"));
			PrintWriter toSwedish = new PrintWriter(
				new OutputStreamWriter(			// XXX sprawdzić kodowanie.
					new FileOutputStream("sverige.txt"), "ISO8859_3"));

			// reading and writing here...
			String line = fromKanji.readLine();
			System.out.println("-->" + line + "<--");
			toSwedish.println(line);
			fromKanji.close();
			toSwedish.close();
		} catch (UnsupportedEncodingException exc) {
			System.err.println("Nieprawidłowe kodowanie " + exc);
			return;
		} catch (IOException err) {
			System.err.println("Błąd wejścia-wyjścia: " + err);
			return;
		}
	}
}
