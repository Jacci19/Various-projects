package Books_exercises.JavaReceptury.database.jdbc;


/** 
 * Wczytanie sterowników bazy danych.
 */
// BEGIN main
public class LoadDriver {
    public static void main(String[] av) {
        try {
            // Próba wczytania sterownika mostu jdbc-odbc. 
            // Powinien on być dostępny w implementacjach Sun JDK.
            Class<?> c = Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            System.out.println("Wczytano " + c.getName());
            // Próba wczytania sterownika firmy Oracle.
            Class<?> d = Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Wczytano " + d.getName());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
}
// END main
