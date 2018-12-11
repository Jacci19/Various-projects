package Books_exercises.JavaReceptury.database.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.darwinsys.sql.ConnectionUtil;

// BEGIN main
/** Pobieranie informacji o bazie danych. */
public class DatabaseMetaDemo {

    public static void main(String[] args) {
        try {
            // Pobranie połączenia.
            Connection conn = 
                ConnectionUtil.getConnection(args[0]);

            // Pobranie metadanych bazy danych, stanowiące
            // sposób pobierania informacji o dostępnych tabelach. 
            DatabaseMetaData meta = conn.getMetaData();

            System.out.println("Używamy " + meta.getDatabaseProductName());
            System.out.println("W wersji " + meta.getDatabaseProductVersion() );
        
            int txisolation = meta.getDefaultTransactionIsolation();
            System.out.println("Domyślny poziom izolacji transakcji to " + 
                txisolation + " (" +
                transactionIsolationToString(txisolation) + ").");

            conn.close();

            System.out.println("Wszystko gotowe!");

        } catch (SQLException ex) {
            System.out.println("Błąd dostępu do bazy danych:");
            System.out.println(ex);
        }
    }

    /** Konwersja liczby całkowitej TransactionIsolation 
     * (zdefiniowanej w java.sql.Connection) na odpowiedni, opisowy 
     * łańcuch znaków.
     * 
     * XXX Usunąć stąd, kiedy zostanie udostępniony plik darwinsys.jar.
     */
    public static String transactionIsolationToString(int txisolation) {
        switch(txisolation) {
            case Connection.TRANSACTION_NONE: 
                // Transakcje nie są obsługiwane.
                return "TRANSACTION_NONE";
            case Connection.TRANSACTION_READ_UNCOMMITTED: 
                // Wszystkie trzy sytuacje mogą się zdarzyć.
                return "TRANSACTION_NONE";
            case Connection.TRANSACTION_READ_COMMITTED: 
                // Niedopuszczalne są odczyty danych przetwarzanych
                // przez inną transakcję; odczyt nie powodujący blokowania 
                // danych jest możliwy, ale każda kolejna operacja odczytu 
                // może zwrócić inne dane (zmodyfikowane w międzyczasie 
                // przez inną transakcję).
                return "TRANSACTION_READ_COMMITTED";
            case Connection.TRANSACTION_REPEATABLE_READ: 
                // Niedopuszczalne są odczyty danych przetwarzanych przez 
                // inną transakcję, dane odczytane są za każdym razem takie 
                // same, ale mogą być nieaktualne.
                return "TRANSACTION_REPEATABLE_READ";
            case Connection.TRANSACTION_SERIALIZABLE:
                // Wszystkie trzy sytuacje są zabronione; najwolniejsze 
                // działanie! Odczyt powoduje blokowanie wierszy bazy 
                // danych.
                return "TRANSACTION_SERIALIZABLE";
            default:
                throw new IllegalArgumentException(
                    txisolation + " nie jest prawidłową wartością TX_ISOLATION");
        }
    }
}
// END main
