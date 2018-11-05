package JavaFX.MojaBiblioteczka.ModelFx;

import JavaFX.MojaBiblioteczka.Database.dao.AuthorDao;
import JavaFX.MojaBiblioteczka.Database.dao.BookDao;
import JavaFX.MojaBiblioteczka.Database.dao.CategoryDao;
import JavaFX.MojaBiblioteczka.Database.models.Author;
import JavaFX.MojaBiblioteczka.Database.models.Book;
import JavaFX.MojaBiblioteczka.Database.models.Category;
import JavaFX.MojaBiblioteczka.Utils.converters.ConverterAuthor;
import JavaFX.MojaBiblioteczka.Utils.converters.ConverterBook;
import JavaFX.MojaBiblioteczka.Utils.converters.ConverterCategory;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListBooksModel {

    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();                      //do filtrowania
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();                  //do filtrowania

    //obiekty, które będą pzechowywały wybrane elementy z filterComboboxów
    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();

    private List<BookFx> bookFxList = new ArrayList<>();        //tymczasowa lista operacyjna, aby podczas filtrowania nie usuwać trwale elementów z głównej listy

    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        books.forEach(book -> {
            //this.bookFxObservableList.add(ConverterBook.convertToBookFx(book));
            this.bookFxList.add(ConverterBook.convertToBookFx(book));
        });
        this.bookFxObservableList.setAll(bookFxList);           //wykasuj zawartość listy i dodaj ją od nowa. TempListę przerzucamy do głównej.

        //do filtrujących comboboxów
        initAuthors();
        initCategory();
    }

    public void filterBookList(){
        //filtrujemy listę książek i dorzucamy ją do bookFxObservableList

        if(getAuthorFxObjectProperty() != null && getCategoryFxObjectProperty() != null){                           //jeśli w obu filterCombo user coś wybrał
            filterPredicate(predicateAuthor().and(predicateCategory()));                         // predikejty będą filtrowały autora i kategorię i przefiltrują naszą listę
        } else if (getCategoryFxObjectProperty() != null){
            filterPredicate(predicateCategory());
        } else if (getAuthorFxObjectProperty() != null){
            filterPredicate(predicateAuthor());
        } else {                                                                                //jeśli w obu filterCombo user nic nie wybrał
            this.bookFxObservableList.setAll(bookFxList);                                       //... to ładujemy do tableView wszystkie ksiązki
        }
    }
    private void initAuthors() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        this.authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = ConverterAuthor.convertToAuthorFx(author);
            this.authorFxObservableList.add(authorFx);
        });
    }
    private void initCategory() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.queryForAll(Category.class);
        this.categoryFxObservableList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = ConverterCategory.convertToCategoryFx(c);
            this.categoryFxObservableList.add(categoryFx);
        });
    }

    //filtrowanie, bedziemy pobierać z BD całe listy i filtrować je na poziomie javy

    private Predicate<BookFx> predicateCategory() {
        //pracuje na bookFx; orzeka, czy każdy obiekt BookFx w kolekcji książek ma takie id, jak id modelu wybranego z filterComboBoxa. Będą służyły jako warunki filtrowania.
        Predicate<BookFx> predicate = bookFx -> bookFx.getCategoryFx().getId() == getCategoryFxObjectProperty().getId();
        return predicate;
        //return bookFx -> bookFx.getCategoryFx().getId() == getCategoryFxObjectProperty().getId();                     //można to zawrzeć w takiej jednej linii
    }

    private Predicate<BookFx> predicateAuthor() {
        return bookFx -> bookFx.getAuthorFx().getId() == getAuthorFxObjectProperty().getId();
    }

    private void filterPredicate(Predicate<BookFx> predicate) {
        List<BookFx> newList = bookFxList.stream().filter(predicate).collect(Collectors.toList());      //filtruje listę (używając predicate), tworzy nową i zapisuje ją do newList
        this.bookFxObservableList.setAll(newList);                                                      //wstawiamy newList do głównej listy
    }


    public ObservableList<BookFx> getBookFxObservableList() {                                       //gettery i settery
        return bookFxObservableList;
    }
    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public AuthorFx getAuthorFxObjectProperty() {
        return authorFxObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
        return authorFxObjectProperty;
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }

    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }
}
