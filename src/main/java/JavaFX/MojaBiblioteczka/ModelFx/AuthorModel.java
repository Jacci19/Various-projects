package JavaFX.MojaBiblioteczka.ModelFx;

import JavaFX.MojaBiblioteczka.Database.dao.AuthorDao;
import JavaFX.MojaBiblioteczka.Database.dao.BookDao;
import JavaFX.MojaBiblioteczka.Database.dbutils.DbManager;
import JavaFX.MojaBiblioteczka.Database.models.Author;
import JavaFX.MojaBiblioteczka.Database.models.Book;
import JavaFX.MojaBiblioteczka.Utils.converters.ConverterAuthor;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class AuthorModel {

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());           //w stworzonym SOP inicjalizujemy pustego authora
    private ObjectProperty<AuthorFx> authorFxObjectPropertyEdit = new SimpleObjectProperty<>(new AuthorFx());       //do edycji komórek tabeli

    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();                   //lista do obsługi tableView

    public void init() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);                                              //Lista autorów z BD
        this.authorFxObservableList.clear();                                                                        //czyścimy listę, aby w tabeli nie było duplikatów
        authorList.forEach(author -> {
            AuthorFx authorFx = ConverterAuthor.convertToAuthorFx(author);
            this.authorFxObservableList.add(authorFx);
        });                                                                                                         //dodajemy wszystkich autorów do naszej listy
    }

    public void saveAuthorInDataBase() throws ApplicationException {
        saveOrUpdate(this.getAuthorFxObjectProperty());
    }
    public void saveAuthorEditInDataBase() throws ApplicationException {                                           //zapis dla obiektów: authorFxObjectPropertyEdit
        saveOrUpdate(this.getAuthorFxObjectPropertyEdit());
    }

    public void deleteAuthorInDataBase() throws ApplicationException, SQLException {
        AuthorDao authorDao = new AuthorDao();                                                                      //był refaktor
        authorDao.deleteById(Author.class, this.getAuthorFxObjectPropertyEdit().getId());
        BookDao bookDao = new BookDao();                                                                            //dodane w lekcji 50
        bookDao.deleteByColumnName(Book.AUTHOR_ID, this.getAuthorFxObjectPropertyEdit().getId());
        this.init();
    }

    private void saveOrUpdate(AuthorFx authorFxObjectPropertyEdit) throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        Author author = ConverterAuthor.convertToAuthor(authorFxObjectPropertyEdit);
        authorDao.createOrUpdate(author);
        this.init();
    }


    public AuthorFx getAuthorFxObjectProperty() {                                           //gettery i settety
        return authorFxObjectProperty.get();
    }
    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {                          //dostęp do całego obiektu
        return authorFxObjectProperty;
    }
    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {                    //dostęp do wartości tego obiektu
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }
    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }
    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
    public AuthorFx getAuthorFxObjectPropertyEdit() {
        return authorFxObjectPropertyEdit.get();
    }
    public ObjectProperty<AuthorFx> authorFxObjectPropertyEditProperty() {
        return authorFxObjectPropertyEdit;
    }

    public void setAuthorFxObjectPropertyEdit(AuthorFx authorFxObjectPropertyEdit) {
        this.authorFxObjectPropertyEdit.set(authorFxObjectPropertyEdit);
    }
}
