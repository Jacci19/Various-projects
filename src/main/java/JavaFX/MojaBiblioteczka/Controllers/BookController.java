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
    private Button saveBookButton;
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
        validation();
    }

    private void validation() {
        this.saveBookButton.disableProperty().bind(this.authorComboBox.valueProperty().isNull()              //if fields are empty then saveButton should be disabled
                .or(this.categoryComboBox.valueProperty().isNull())
                .or(this.titleTextField.textProperty().isEmpty())
                .or(this.descTextArea.textProperty().isEmpty())
                .or(this.isbnTextField.textProperty().isEmpty())
                .or(this.releaseDatePicker.valueProperty().isNull()));
    }

    public void bindings() {
        this.categoryComboBox.setItems(this.bookModel.getCategoryFxObservableList());       //powiązanie comboboxów z listami z bookModelu
        this.authorComboBox.setItems(this.bookModel.getAuthorFxObservableList());


        this.authorComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().authorFxProperty());
        this.categoryComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().categoryFxProperty());
        this.titleTextField.textProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().titleProperty());
        this.descTextArea.textProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().descriptionProperty());
        this.ratingSlider.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().ratingProperty());
        this.isbnTextField.textProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().isbnProperty());
        this.releaseDatePicker.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().releaseDateProperty());
    }


    public void onAddBookButtonAction() {
        System.out.println(this.bookModel.getBookFxObjectProperty().toString());
        try {
            this.bookModel.saveBookInDataBase();
            clearFields();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    private void clearFields() {                                            //kasujemy wartości pól kiedy książka zostanie dodana (żeby nie dodać dwóch takich samych)
        this.authorComboBox.getSelectionModel().clearSelection();
        this.categoryComboBox.getSelectionModel().clearSelection();
        this.titleTextField.clear();
        this.descTextArea.clear();
        this.ratingSlider.setValue(1);
        this.isbnTextField.clear();
        this.releaseDatePicker.getEditor().clear();
    }

    public BookModel getBookModel() {
        return bookModel;
    }
}
