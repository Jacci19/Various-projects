package JavaFX.MojaBiblioteczka.ModelFx;

import JavaFX.MojaBiblioteczka.Database.dao.BookDao;
import JavaFX.MojaBiblioteczka.Database.models.Book;
import JavaFX.MojaBiblioteczka.Utils.converters.ConverterBook;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ListBooksModel {

    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        books.forEach(book -> {
            this.bookFxObservableList.add(ConverterBook.convertToBookFx(book));
        });
    }

    public ObservableList<BookFx> getBookFxObservableList() {                                       //getter i setter
        return bookFxObservableList;
    }

    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
    }
}
