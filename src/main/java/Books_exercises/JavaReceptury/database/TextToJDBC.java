package Books_exercises.JavaReceptury.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.StringTokenizer;

import domain.User;

// BEGIN main
/** Konwertujemy tekstową bazę danych na JDBC.
 * Postać formatu tekstowego: name:password:fullname:city:prov:country:privs
 */
public class TextToJDBC {

    protected final static String TEXT_NAME = "users.txt";
    protected final static String DB_URL = "jdbc:idb:userdb.prp";
    protected static boolean dropAndReCreate = false;

    public static void main(String[] fn) throws Exception {

        BufferedReader is = new BufferedReader(new FileReader(TEXT_NAME));

        // Pobieramy sterownik bazy.
        Class.forName("jdbc.idbDriver");

        System.out.println("Nawiązujemy połączenie");
        Connection conn = DriverManager.getConnection(
            DB_URL, "admin", "");    // Użytkownik i hasło.

        System.out.println("Tworzymy obiekt Statement");
        Statement stmt = conn.createStatement();

        System.out.println("Ponownie tworzymy tabelę oraz jej indeks");
        if (dropAndReCreate)
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.executeUpdate("CREATE TABLE users (\n" +
            "name     char(12) PRIMARY KEY,\n" +
            "password char(20),\n" +
            "fullName char(30),\n" +
            "email    char(60),\n" +
            "city     char(20),\n" +
            "prov     char(20),\n" +
            "country  char(20),\n" +
            "privs    int\n" +
            ")");
        stmt.executeUpdate("CREATE INDEX nickIndex ON users (name)");
        stmt.close();

        // Wstawiamy dane do tabeli.
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO users VALUES (?,?,?,?,?,?,?,?)");

        String line;
        while ((line = is.readLine()) != null) {

            if (line.startsWith("#")) {        // Komentarz.
                continue;
            }

            StringTokenizer st =
                new StringTokenizer(line, ":");
            String nick = st.nextToken();
            String pass = st.nextToken();
            String full = st.nextToken();
            String email = st.nextToken();
            String city = st.nextToken();
            String prov = st.nextToken();
            String ctry = st.nextToken();
            // User u = new User(nick, pass, full, email,
            //    city, prov, ctry);
            String privs = st.nextToken();
            int iprivs = 0;
            if (privs.indexOf("A") != -1) {
                iprivs |= User.P_ADMIN;
            }
            if (privs.indexOf("E") != -1) {
                iprivs |= User.P_EDIT;
            }
            ps.setString(1, nick);
            ps.setString(2, pass);
            ps.setString(3, full);
            ps.setString(4, email);
            ps.setString(5, city);
            ps.setString(6, prov);
            ps.setString(7, ctry);
            ps.setInt(8, iprivs);
            ps.executeUpdate();
        }
        ps.close();        // Operacje na poleceniu zostały zakończone.
        conn.close();      // Operacje na połączeniu zostały zakończone.
        return;            // Kończymy program.
    }
}
// END main
