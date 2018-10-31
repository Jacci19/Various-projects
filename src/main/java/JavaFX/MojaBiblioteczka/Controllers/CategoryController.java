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

    @FXML
    private Button deleteCategoryButton;


    private CategoryModel categoryModel;

    @FXML
    public void initialize(){
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();                                              //inicjacja comboBoxa z CategoryModel (wypełniamy go danymi z BD)
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());   //przypisujemy te dane do comboBoxa
        initBindings();
    }

    private void initBindings() {
        this.addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());       //if textField is empty then button is disabled
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());    //if comboBoxField is empty then deleteBtn is disabled
    }


    @FXML
    void onAddCategoryButtonAction() {
        this.categoryModel.saveCategoryInDataBase(categoryTextField.getText());
        categoryTextField.clear();
    }

    @FXML
    void onDeleteCategoryButtonAction() {
        this.categoryModel.deleteCategoryById();
    }

    @FXML
    void onCategoryComboBoxAction() {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());    //każdy wybór w comboBox ustawia odpowiednie category

    }
}

















