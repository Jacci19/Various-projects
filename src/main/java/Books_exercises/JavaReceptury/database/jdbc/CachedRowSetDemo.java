package Books_exercises.JavaReceptury.database.jdbc;

import javax.sql.RowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/** Prosty przykład wykorzystania obiektu CachedRowSet.
 */
// BEGIN main
public class CachedRowSetDemo {
    public static void main(String[] args) throws Exception {
        RowSet rs;

        RowSetFactory rsFactory = RowSetProvider.newFactory();
        rs = rsFactory.createCachedRowSet();

        rs.setUrl("jdbc:postgresql:tmclub");
        rs.setUsername("ian");
        rs.setPassword("secret");

        rs.setCommand("select * from members where name like ?");
        rs.setString(1, "I%");

        // To wywołanie spowoduje nawiązanie połącznia pomiędzy 
        // obiektem CachedRowSet i bazą danych, pobranie danych 
        // i zakończenie połączenia.
        rs.execute();

        // Jakiś czas później klient próbuje coś zrobić.

        // Suppose we want to update data:
        // Załóżmy, że użytkownik zdecydował się zmienić dane.
        while (rs.next()) {
            if (rs.getInt("id") == 42) {
                rs.setString(1, "Marvin");
                rs.updateRow();    // Normalne wywołanie JDBC

                // To dodatkowe wywołanie informuje obiekt CachedRowSet,
                // że należy nawiązać połączenie z bazą danych 
                // i zaktualizować przechowywane w niej informacje.
                rs.updateRow();
            }
        }
    
        // Jeśli wszystko już zrobiliśmy, to...
        rs.close();
    }
}
// END main
