package JavaFX.MojaBiblioteczka.Controllers;

import JavaFX.MojaBiblioteczka.ModelFx.CategoryFx;
import JavaFX.MojaBiblioteczka.ModelFx.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CategoryController {

    @FXML
    private TextField categoryTextField;

    @FXML
    private Button addCategoryButton;

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    private CategoryModel categoryModel;

    @FXML
    public void initialize(){
        this.categoryModel = new CategoryModel();
        initBindings();
    }

    private void initBindings() {
        addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());       //if textField is empty then button is disabled
    }


    @FXML
    void onAddCategoryButtonAction() {
        this.categoryModel.saveCategoryInDataBase(categoryTextField.getText());
        categoryTextField.clear();
    }
}
