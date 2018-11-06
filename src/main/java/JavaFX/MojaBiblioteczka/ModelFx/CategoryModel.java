package JavaFX.MojaBiblioteczka.ModelFx;

//ta klasa będzie zajmowała się obsługą logiki biznesowej która bedzie wywoływana w kontrolerze (który obsługuje nasz wil ???).
// Będzie warstwą pośrednią między javąFX a bazą danych

import JavaFX.MojaBiblioteczka.Database.dao.BookDao;
import JavaFX.MojaBiblioteczka.Database.dao.CategoryDao;
import JavaFX.MojaBiblioteczka.Database.dbutils.DbManager;
import JavaFX.MojaBiblioteczka.Database.models.Book;
import JavaFX.MojaBiblioteczka.Database.models.Category;
import JavaFX.MojaBiblioteczka.Utils.converters.ConverterCategory;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel {

    //nasz model obsługuje stworzenie kategorii i zapisanie jej do bazy danych

    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();          //to powiążemy z naszym ComboBoxem
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();                     //to powiążemy z elementami ComboBoxa
    private TreeItem<String> root = new TreeItem<>();                                               //____________https://docs.oracle.com/javafx/2/ui_controls/tree-view.htm


    public void init() throws ApplicationException {                                                //wypełniamy comboBox elementami BD (inicjalizuje model z danymi), wywołujemy to w categoryController (initialize)
        CategoryDao categoryDao = new CategoryDao();                                                //był refaktor
        List<Category> categories = categoryDao.queryForAll(Category.class);                        //robimy listę ze wszystkich category w BD
        initCategoryList(categories);
        initRoot(categories);
        //DbManager.closeConnectionSource();                                                                //po refactoringu wsżędzie tą linię usunęliśmy (dodaliśmy finally w commonDao)

    }

    private void initRoot(List<Category> categories) {                                          // init naszego treeView z kategoriami
        this.root.getChildren().clear();                                                        // aby elementy drzewa się nie duplikowały
        categories.forEach(c->{                                                                 // W pętli pobieramy każdą kategorię
            TreeItem<String> categoryItem = new TreeItem<>(c.getName());                        // tworzymy od początku nowy treeItem który inicjalizowany jest nazwą kategorii
            c.getBooks().forEach(b->{
                categoryItem.getChildren().add(new TreeItem<>(b.getTitle()));
            });
            root.getChildren().add(categoryItem);                                               // dodajemy ten item do naszego roota.
        });                                                                                     // W treeView pojawiają się kategorie
    }

    private void initCategoryList(List<Category> categories) {
        this.categoryList.clear();                                                              //czyścimy listę z niepotrzebnych elementów (aby stare elementy się nie duplikowały)
        categories.forEach(c->{
            CategoryFx categoryFx = ConverterCategory.convertToCategoryFx(c);
            this.categoryList.add(categoryFx);
        });
    }

    public void deleteCategoryById() throws ApplicationException, SQLException {                              //wyjątki wszystkich metod przerzucamy do categoryController
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.deleteById(Category.class, category.getValue().getId());
        BookDao bookDao = new BookDao();                                                                            //dodane w lekcji 50
        bookDao.deleteByColumnName(Book.CATEGORY_ID, category.getValue().getId());

        init();                                                                                 //aby kategoria usunęła się także z listy a nie tylko z BD
    }

    public void saveCategoryInDataBase(String name) throws ApplicationException {               //wyjątek puszczemy wyżej, obsłużymy go w categoryController
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category();
        category.setName(name);
        categoryDao.createOrUpdate(category);
        init();                                                                                 //aby po kliku dodaj kategoria od razu pojawiła się w ComboBoxie
    }

    public void updateCategoryInDataBase() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());                                          //ustawiamy nazwę kategorii znalezionej w bazie na nową wpisaną przez usera w oknie
        categoryDao.createOrUpdate(tempCategory);
        init();                                                                                 //aby po kliku dodaj kategoria od razu pojawiła się w ComboBoxie
    }


    public ObservableList<CategoryFx> getCategoryList() {                                           //gettery i settery
        return categoryList;
    }
    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }
    public CategoryFx getCategory() {
        return category.get();
    }
    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }
    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }
    public TreeItem<String> getRoot() {
        return root;
    }
    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }
}























