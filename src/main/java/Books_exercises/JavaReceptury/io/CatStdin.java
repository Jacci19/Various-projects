package Books_exercises.JavaReceptury.io;

import java.io.*;

/**
 * Użycie klasy BufferedReader do odczytania łańcucha znaków 
 * ze standardowego wejścia (System.in) i wyświetlenia go na 
 * standardowym wyjściu (System.out).
 */
// BEGIN main
public class CatStdin {

    public static void main(String[] av) {
        try {
            BufferedReader is =
                new BufferedReader(new InputStreamReader(System.in));
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                System.out.println(inputLine);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("Nieoczekiwany błąd wejścia-wyjścia: " + e);
        }
    }
}
// END main
