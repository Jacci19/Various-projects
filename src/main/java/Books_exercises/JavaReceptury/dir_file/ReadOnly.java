package Books_exercises.JavaReceptury.dir_file;

import java.io.*;

// BEGIN main
public class ReadOnly {
    public static void main(String[] a) throws IOException {

        File f = new File("f");

        if (!f.createNewFile()) {
            System.out.println("Nie można utworzyć nowego pliku.");
            return;
        }

        if (!f.canWrite()) {
            System.out.println("Nie można zapisywać danych w nowym pliku!");
            return;
        }

        if (!f.setReadOnly()) {
            System.out.println("Wrrr! Nie można ustawić atrybutu \"tylko do odczytu\".");
            return;
        }

        if (f.canWrite()) {
           System.out.println("Niemodyfikowalny, kapitanie!");
           System.out.println("Ale wciąż po wywołaniu setReadOnly() metoda canWrite() zwraca true.");
            return;
        } else {
           System.out.println("Logiczne, kapitanie!");
           System.out.println("canWrite() zwraca false po wywołaniu setReadOnly().");
        }
    }
}
// END main
