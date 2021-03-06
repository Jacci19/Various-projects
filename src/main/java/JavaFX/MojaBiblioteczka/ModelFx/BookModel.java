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

import java.util.List;

public class BookModel {


    private ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    
    public void init() throws ApplicationException {
        initAuthorList();
        initCategoryList();
    }

    private void initCategoryList() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();                                                            //był refaktor
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        categoryFxObservableList.clear();
        categoryList.forEach(c->{
            CategoryFx categoryFx = ConverterCategory.convertToCategoryFx(c);
            categoryFxObservableList.add(categoryFx);
        });
        //DbManager.closeConnectionSource();                                                                //po refactoringu wszędzie tą linię usunęliśmy (dodaliśmy finally w commonDao)
    }

    private void initAuthorList() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        this.authorFxObservableList.clear();
        authorList.forEach(author->{
            AuthorFx authorFx = ConverterAuthor.convertToAuthorFx(author);
            this.authorFxObservableList.add(authorFx);
        });
    }

    public void saveBookInDataBase() throws ApplicationException {
        Book book = ConverterBook.convertToBook(this.getBookFxObjectProperty());
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(Category.class, this.getBookFxObjectProperty().getCategoryFx().getId());
        book.setCategory(category);

        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.findById(Author.class, this.getBookFxObjectProperty().getAuthorFx().getId());
        book.setAuthor(author);

        BookDao bookDao = new BookDao();
        bookDao.createOrUpdate(book);



    }
                                                                                                        //gettery i settery
    public BookFx getBookFxObjectProperty() {
        return bookFxObjectProperty.get();
    }

    public ObjectProperty<BookFx> bookFxObjectPropertyProperty() {
        return bookFxObjectProperty;
    }

    public void setBookFxObjectProperty(BookFx bookFxObjectProperty) {
        this.bookFxObjectProperty.set(bookFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
}
