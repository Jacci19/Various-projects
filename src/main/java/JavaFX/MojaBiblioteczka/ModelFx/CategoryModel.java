package JavaFX.MojaBiblioteczka.ModelFx;

//ta klasa będzie zajmowała się obsługą logiki biznesowej która bedzie wywoływana w kontrolerze (który obsługuje nasz wil ???).
// Będzie warstwą pośrednią między javąFX a bazą danych

import JavaFX.MojaBiblioteczka.Database.dao.CategoryDao;
import JavaFX.MojaBiblioteczka.Database.dbutils.DbManager;
import JavaFX.MojaBiblioteczka.Database.models.Category;

public class CategoryModel {

    //nasz model obsługuje stworzenie kategorii i zapisanie jej do bazy danych

    public void saveCategoryInDataBase(String name){
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();
    }
}
