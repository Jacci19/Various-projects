package Books_exercises.JavaReceptury.database.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

/** Sprawdzenie wczytywania sterowników i nawiązywania połączenia 
 * z bazą danych.
 * Adres URL zakłada, że dysponujemy przykładową bazą danych 
 * "Companies" firmy Microsoft, ze skonfigurowanym systemowym
 * źródłem DNS (bądź źródłem określonym dla danego użytkownika),
 * podanym w panelu sterowania ODBC.
 */
// BEGIN main
public class Connect {

    public static void main(String[] av) {
        String dbURL = "jdbc:odbc:Companies";
        try {
            // Wczytanie sterownika mostu jdbc-odbc. 
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // Włączamy rejestrowanie komunikatów.
            DriverManager.setLogWriter(new PrintWriter((System.err)));

            System.out.println("Nawiązujemy połączenie");
            Connection conn = 
                DriverManager.getConnection(dbURL, "ian", "");  
                                               // Nazwa użytkownika i hasło.

            // Jeśli obiekt SQLWarning jest dostępny, to wyświetlamy
            // przechowywane w nim ostrzeżenia. Może być dostępnych 
            // kilka ostrzeżeń zapisanych w określonej kolejności.

            SQLWarning warn = conn.getWarnings();
            while (warn != null) {
                System.out.println("Stan SQL: " + warn.getSQLState());
                System.out.println("Komunikat: " + warn.getMessage());
                System.out.println("Sprzedawca: " + warn.getErrorCode());
                System.out.println("");
                warn = warn.getNextWarning();
            }

            // Obsługa połączenia...

            conn.close();    // Wszystko gotowe - zamykamy połączenie 
                             // z bazą danych.


        } catch (ClassNotFoundException e) {
            System.out.println("Nie można pobrać sterownika " + e);
        } catch (SQLException e) {
            System.out.println("Nie można nawiązać połączenia z bazą danych " + e);

        }
    }
}
// END main
