package JavaFX.MojaBiblioteczka.Database.dao;

import JavaFX.MojaBiblioteczka.Database.models.Book;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class BookDao extends CommonDao {

    public BookDao() {
        super();
    }

    public void deleteByColumnName(String columName, int id) throws ApplicationException, SQLException {        //usuwamy książki po nazwie kolumny (jak usuniemy autora, to jego książki również powinny zostać usunięte)
        Dao<Book, Object> dao = getDao(Book.class);
        DeleteBuilder<Book, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columName, id);                                 //usuń tam gdzie w podanej kolumnie jest to id
        dao.delete(deleteBuilder.prepare());
    }

}
