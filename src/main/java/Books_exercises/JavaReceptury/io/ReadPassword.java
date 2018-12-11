package Books_exercises.JavaReceptury.io;

import java.io.Console;

/**
 * Wczytanie hasła podanego przez użytkownika.
 * Program wymaga Javy w wersji 6 lub wyższej.
 */
// BEGIN main
public class ReadPassword {
    public static void main(String[] args) {
        Console cons;
        if ((cons = System.console()) != null) {
            char[] passwd = null;
            try {
                passwd = cons.readPassword("Podaj hasło:");
                // W rzeczywistych aplikacjach hasło zostałoby zapewne
                // przekazane do jakiegoś kodu odpowiadającego za 
                // uwierzytelnianie użytkownika.
                System.out.println("Wpisałeś hasło: " + new String(passwd));
            } finally {
                // Ze względów bezpieczeństwa nadpisujemy zawartość tablicy.
                if (passwd != null) {
                    java.util.Arrays.fill(passwd, ' ');
                }
            }
        } else {
            throw new RuntimeException("Brak konsoli, nie można wczytać hasła.");
        }
    }
}
// END main
