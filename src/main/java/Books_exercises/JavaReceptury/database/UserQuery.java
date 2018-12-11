package Books_exercises.JavaReceptury.database;

// import jabadot.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Odczytujemy z relacyjnej bazy danych informacje 
 * o jednym użytkowniku, wykorzystując JDBC.
 */
// BEGIN main
public class UserQuery {

    public static void main(String[] fn)
    throws ClassNotFoundException, SQLException, IOException {

        // Wczytujemy sterownik bazy danych.
        Class.forName(JDConstants.getProperty("jabadot.jabadb.driver"));

        System.out.println("Pobieramy połączenie");
        Connection conn = DriverManager.getConnection(
            JDConstants.getProperty("jabadot.dburl"));

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(
            "SELECT * from jabadb where name='ian'");

        // Teraz pobieramy (wszystkie) wiersze pasujące do zapytania.
        while (rs.next()) {

            // Pole 1 to nazwa użytkownika.
            String name = rs.getString(1);

            // Pole 2 to hasło, którego nie wyświetlamy.

            // Pole 3 to personalia użytkownika.
            String fullName = rs.getString(3);

            System.out.println("Użytkownik " + name + " to " + fullName);
        }

        rs.close();   // Wszystkie operacje na zbiorze wyników zostały wykonane.
        stmt.close(); // Wszystkie operacje na danym poleceniu zostały wykonane.
        conn.close(); // Wszystkie operacje na danym połączeniu zostały wykonane.
        System.exit(0);    // Kończymy działanie programu.
    }
}
// END main
