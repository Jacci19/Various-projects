package JavaFX.MojaBiblioteczka.Controllers;

import JavaFX.MojaBiblioteczka.ModelFx.AuthorFx;
import JavaFX.MojaBiblioteczka.ModelFx.BookModel;
import JavaFX.MojaBiblioteczka.ModelFx.CategoryFx;
import JavaFX.MojaBiblioteczka.Utils.DialogsUtils;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class BookController {

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    @FXML
    private ComboBox<AuthorFx> authorComboBox;

    @FXML
    private TextArea descTextArea;

    @FXML
    private Slider ratingSlider;

    @FXML
    private TextField isbnTextField;

    @FXML
    private DatePicker releaseDatePicker;

    @FXML
    private TextField titleTextField;


    private BookModel bookModel;


    @FXML
    public void initialize(){
        this.bookModel = new BookModel();

        try {
            this.bookModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

        bindings();
    }

    private void bindings() {
        this.categoryComboBox.setItems(this.bookModel.getCategoryFxObservableList());       //powiązanie comboboxów z listami z bookModelu
        this.authorComboBox.setItems(this.bookModel.getAuthorFxObservableList());


        this.bookModel.getBookFxObjectProperty().categoryFxProperty().bind(this.categoryComboBox.valueProperty());
        this.bookModel.getBookFxObjectProperty().authorFxProperty().bind(this.authorComboBox.valueProperty());
        this.bookModel.getBookFxObjectProperty().titleProperty().bind(this.titleTextField.textProperty());
        this.bookModel.getBookFxObjectProperty().descriptionProperty().bind(this.descTextArea.textProperty());
        this.bookModel.getBookFxObjectProperty().ratingProperty().bind(ratingSlider.valueProperty());
        this.bookModel.getBookFxObjectProperty().isbnProperty().bind(this.isbnTextField.textProperty());
        this.bookModel.getBookFxObjectProperty().releaseDateProperty().bind(this.releaseDatePicker.valueProperty());
    }


    public void onAddBookButtonAction() {
        System.out.println(this.bookModel.getBookFxObjectProperty().toString());
    }
}
