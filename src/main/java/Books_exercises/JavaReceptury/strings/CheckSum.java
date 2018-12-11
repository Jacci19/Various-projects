package Books_exercises.JavaReceptury.strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CheckSum - wyświetla sumę kontrolną pliku.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class CheckSum {
    public static void main(String[] args) {
        int sum = 0;
        if (args.length == 0) {
            sum = CheckSum.process(new BufferedReader(
            new InputStreamReader(System.in)));
        } else for (String arg : args) {
            try {
                sum += CheckSum.process(
                    new BufferedReader(new FileReader(arg)));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Nie znaleziono pliku: " + arg, e);
            }
        }
        System.out.println(sum);
    }

    // BEGIN main
    /** Określa sumę kontrolną pliku tekstowego, przkazanego jako 
     *  BufferedReader. Ta wersja metody nie uwzglęnia znaków końca
     *  wiersza, zatem zwróci identyczne wyniki dla tego samego tekstu
     *  niezależnie do platformy w której plik został zapisany.
     *  Metody nie należy używać do przetwarzania plików binarnych!
     */
    public static int process(BufferedReader is) {
        int sum = 0;
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                int i;
                for (i=0; i<inputLine.length(); i++) {
                    sum += inputLine.charAt(i);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e);
        }
        return sum;
    }
// END main
}
