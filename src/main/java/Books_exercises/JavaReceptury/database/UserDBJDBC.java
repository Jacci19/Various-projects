package Books_exercises.JavaReceptury.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import domain.User;

/** Klasa UserDB wykorzystująca JDBC oraz relacyjną bazę danych.
 * Używamy odziedziczonej metody getUser ("Odszuka obiekt User 
 * dla podanej nazwy użytkownika"), gdyż w tej wersji klasy wszystkie 
 * dane są przechowywany w pamięci.
 * <p>
 * ToDo: Czy nie należałoby zaimplementować tej klasy jako Entity EJB.
 */
// BEGIN main
public class UserDBJDBC extends UserDB {

    protected PreparedStatement setPasswordStatement;
    protected PreparedStatement addUserStmt;
    protected PreparedStatement setLastLoginStmt;
    protected PreparedStatement deleteUserStmt;

    /** Wstawiamy kilkanaście pól do bazy danych. */
    final static String SQL_INSERT_USER =
        "insert into users " +
        " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /** Konstruktor domyślny. */
    protected UserDBJDBC() throws NamingException, SQLException, IOException {
        super();

        System.out.println("UserDBJDBC.<init> zaczynamy...");
        
        System.out.println("Wczytywanie sterownika...");
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("NIE UDAŁO SIĘ: " + ex.toString());
            throw new IllegalStateException(ex.toString());
        }
        Connection conn = DriverManager.getConnection(
            "jdbc:hsqldb:/home/ian/src/jabadot/WEB-INF/jabadot",
            "jabadmin", "fredonia");

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select * from users");

        while (rs.next()) {
            //name:password:fullname:City:Prov:Country:privs

            // Pobieramy pola z zapytania.
            // 
            // Można by użyć Entity EJB oraz CMP: to rozwiązanie jest 
            // w zbyt dużym stopniu zależne od SQL. Informacje o polach
            // można znaleźć w pliku CreateUserDatabase.java 
            int i = 1;
            String nick = rs.getString(i++).trim();
            String pass = rs.getString(i++).trim();
            // System.err.println(nick + " (" + pass + ")");
            String first = rs.getString(i++);
            String last = rs.getString(i++);
            String email = rs.getString(i++);
            String city = rs.getString(i++);
            String prov = rs.getString(i++);
            String ctry = rs.getString(i++);
            java.sql.Date credt = rs.getDate(i++);
            java.sql.Date lastlog = rs.getDate(i++);
            String skin = rs.getString(i++);
            boolean editPrivs = rs.getBoolean(i++);
            boolean adminPrivs = rs.getBoolean(i++);

            // Tworzymy obiekt User na podstawie odczytanych informacji.
            // System.out.println("Tworzenie obiektu User.");
            User u = new User(nick, pass, first, last, email,
                prov, ctry, credt, lastlog,
                skin, editPrivs, adminPrivs);
            // System.out.println("Dodawanie obiektu User " + u + " do " + users);
            // Dodajemy obiekt do danych w pamięci.
            users.add(u);
            // System.err.println("Użytkownik " + nick + "; hasło " + pass.charAt(0));
        }
        rs.close();        // To koniec operacji na obiekcie ResultSet.
        stmt.close();

        // Tworzymy przygotowane polecenia, abyśmy nie musieli
        // tworzyć ich za każdym razem w dalszej części programu.
        addUserStmt = conn.prepareStatement(SQL_INSERT_USER);
        setPasswordStatement = conn.prepareStatement(
            "update users SET password = ? where name = ?");
        setLastLoginStmt = conn.prepareStatement(
            "update users SET lastLogin = ? where name = ?");
        deleteUserStmt = conn.prepareStatement(
            "delete from users where name = ?");
        
        conn.close();
    }

    /** Dodajemy jednego użytkownika do listy zarówno w pamięci, 
     * jak i na dysku. */
    public synchronized void addUser(User nu)
    throws IOException, SQLException {
        // Dodajemy do listy w pamięci.
        super.addUser(nu);

        // Kopiujemy pola z obiektu do bazy danych.
        // XXX ROZWIĄZANIE NIE JEST KOMPLETNE.
        int i = 1;
        addUserStmt.setString(i++, nu.getName());
        addUserStmt.setString(i++, nu.getPassword());
        addUserStmt.setString(i++, nu.getFirstName()); 
        addUserStmt.setString(i++, nu.getLastName());
        addUserStmt.setString(i++, nu.getEmail());
        addUserStmt.setString(i++, nu.getCity());
        addUserStmt.setString(i++, nu.getProvince());
        addUserStmt.setString(i++, nu.getCountry());
        java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
        addUserStmt.setDate(i++, now);
        addUserStmt.setDate(i++, now);
        addUserStmt.setString(i++, nu.getSkin());
        addUserStmt.setBoolean(i++, false);
        addUserStmt.setBoolean(i++, false);
        --i;

        if (i != 13) {
            System.out.println("Ostrzeżenie: nie podano wartości wszystkich pól! i = " + i);
        }

        // Zapisujemy w bazie danych.
        addUserStmt.executeUpdate();
    }

    public void deleteUser(String nick) throws SQLException {
        // Szukamy użytkownika w bazie.
        User u = getUser(nick);
        if (u == null) {
            throw new SQLException("Użytkownik " + nick + 
                    " nie jest dostępny w bazie danych w pamięci.");
        }
        deleteUserStmt.setString(1, nick);
        int n = deleteUserStmt.executeUpdate();
        if (n != 1) {    // Więcej niż jeden rekord??
            /*NIEMOŻLIWE */
            throw new SQLException("BŁĄD: usunięto " + n + " wierszy!!");
        }

        // Jeśli usunęliśmy wiersze z bazy, to musimy je także usunąć 
        // z pamięci.
        users.remove(u);
    }

    public synchronized void setPassword(String nick, String newPass) 
    throws SQLException {

        // Znajdujemy obiekt użytkownika.
        User u = getUser(nick);

        // Zmieniamy go w bazie danych, dane przechowywane
        // w pamięci nie zostaną jednocześnie zaktualizowane.
        setPasswordStatement.setString(1, newPass);
        setPasswordStatement.setString(2, nick);
        setPasswordStatement.executeUpdate();

        // Zmieniamy informacje przechowywane w pamięci.
        u.setPassword(newPass);
    }

    /** Aktualizacja pola ostatniego logowania. */
    public synchronized void setLoginDate(String nick, java.util.Date date) 
    throws SQLException {
    
        // Znajdujemy obiekt użytkownika.
        User u = getUser(nick);

        // Zmieniamy go w bazie danych, dane przechowywane
        // w pamięci nie zostaną jednocześnie zaktualizowane.
        // Musimy dokonać konwersji z typu java.util.Date na java.sql.Date.
        // Znacznie bardziej efektywne byłoby wykorzystanie typu 
        // java.sql.Date w całym programie.
        setLastLoginStmt.setDate(1, new java.sql.Date(date.getTime()));
        setLastLoginStmt.setString(2, nick);
        setLastLoginStmt.executeUpdate();

        // Zmieniamy dane w pamięci.
        u.setLastLoginDate(date);
    }
}
// END main
