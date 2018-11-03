package JavaFX.MojaBiblioteczka.Utils.converters;

import JavaFX.MojaBiblioteczka.Database.models.Category;
import JavaFX.MojaBiblioteczka.ModelFx.CategoryFx;

public class ConverterCategory  {

    public static CategoryFx convertToCategoryFx(Category category){
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());                                                        //wszystko z mojej category przepisujemy do CategoryFX  (id)
        categoryFx.setName(category.getName());                                                    //wszystko z mojej category przepisujemy do CategoryFX  (name)
        return categoryFx;
    }
}
