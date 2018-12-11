package Books_exercises.JavaReceptury.io;

import java.io.*;
/**
 * Odczytanie liczby typu int ze standardowego strumienia wejściowego.
 * @author    Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ReadStdinInt {
    public static void main(String[] ap) {
        String line = null;
        int val = 0;
        try {
            BufferedReader is = new BufferedReader(
                new InputStreamReader(System.in));
            line = is.readLine();
            val = Integer.parseInt(line);
            System.out.println("Odczytana liczba to: " + val);
        } catch (NumberFormatException ex) {
            System.err.println("Podany łańcuch - " + line + " nie jest poprawną liczbą");
        } catch (IOException e) {
            System.err.println("Nieoczekiwany błąd wejścia-wyjścia: " + e);
        }
    }
}
// END main
