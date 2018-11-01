package JavaFX.MojaBiblioteczka.ModelFx;

//ta klasa będzie zajmowała się obsługą logiki biznesowej która bedzie wywoływana w kontrolerze (który obsługuje nasz wil ???).
// Będzie warstwą pośrednią między javąFX a bazą danych

import JavaFX.MojaBiblioteczka.Database.dao.CategoryDao;
import JavaFX.MojaBiblioteczka.Database.dbutils.DbManager;
import JavaFX.MojaBiblioteczka.Database.models.Category;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryModel {

    //nasz model obsługuje stworzenie kategorii i zapisanie jej do bazy danych

    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();          //to powiążemy z naszym ComboBoxem
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();                     //to powiążemy z elementami ComboBoxa


    public void init(){                                                                             //wypełniamy comboBox elementami BD (inicjalizuje model z danymi), wywołujemy to w categoryController (initialize)
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());                 //połączenie do BD
        List<Category> categories = categoryDao.queryForAll(Category.class);                        //robimy listę ze wszystkich category w BD
        this.categoryList.clear();                                                              //czyścimy listę z niepotrzebnych elementów (aby stare elementy się nie duplikowały)
        categories.forEach(c->{
            CategoryFx categoryFx = new CategoryFx();
            categoryFx.setId(c.getId());                                                        //wszystko z mojej category przepisujemy do CategoryFX  (id)
            categoryFx.setName(c.getName());                                                    //wszystko z mojej category przepisujemy do CategoryFX  (name)
            this.categoryList.add(categoryFx);
        });
        DbManager.closeConnectionSource();                                                          //zamykamy połączenie z BD

    }

    public void deleteCategoryById(){
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        categoryDao.deleteById(Category.class, category.getValue().getId());
        DbManager.closeConnectionSource();
        init();                                                                                 //aby kategoria usunęła się także z listy a nie tylko z BD
    }

    public void saveCategoryInDataBase(String name){
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();
        init();                                                                                 //aby po kliku dodaj kategoria od razu pojawiła się w ComboBoxie
    }

    public void updateCategoryInDataBase() {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());                                          //ustawiamy nazwę kategorii znalezionej w bazie na nową wpisaną przez usera w oknie
        categoryDao.creatOrUpdate(tempCategory);
        DbManager.closeConnectionSource();
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

}























