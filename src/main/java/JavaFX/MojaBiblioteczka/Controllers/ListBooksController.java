package JavaFX.MojaBiblioteczka.Controllers;

import JavaFX.MojaBiblioteczka.ModelFx.AuthorFx;
import JavaFX.MojaBiblioteczka.ModelFx.BookFx;
import JavaFX.MojaBiblioteczka.ModelFx.CategoryFx;
import JavaFX.MojaBiblioteczka.ModelFx.ListBooksModel;
import JavaFX.MojaBiblioteczka.Utils.DialogsUtils;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class ListBooksController {

    @FXML
    private ComboBox categoryFilterComboBox;
    @FXML
    private ComboBox authorFilterComboBox;
    @FXML
    private TableView<BookFx> booksTableView;
    @FXML
    private TableColumn<BookFx, String> titleColumn;        // < na jakim typie pracuje tabela, na jakim kolumna tabeli >
    @FXML
    private TableColumn<BookFx, String> descColumn;
    @FXML
    private TableColumn<BookFx, AuthorFx> authorColumn;
    @FXML
    private TableColumn<BookFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn<BookFx, Number> ratingColumn;
    @FXML
    private TableColumn<BookFx, String> isbnColumn;
    @FXML
    private TableColumn<BookFx, LocalDate> releaseColumn;
    @FXML
    private TableColumn<BookFx, BookFx> deleteColumn;

    private ListBooksModel listBooksModel;

    @FXML
    void initialize(){
        this.listBooksModel = new ListBooksModel();
        try {
            this.listBooksModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

        //powiązanie list z comboboxami filtrujacymi
        this.categoryFilterComboBox.setItems(this.listBooksModel.getCategoryFxObservableList());
        this.authorFilterComboBox.setItems(this.listBooksModel.getAuthorFxObservableList());
        this.listBooksModel.categoryFxObjectPropertyProperty().bind(this.categoryFilterComboBox.valueProperty());
        this.listBooksModel.authorFxObjectPropertyProperty().bind(this.authorFilterComboBox.valueProperty());

        //bindujemy tabelę i wszystkie kolumny z naszą listą, która jest w BookModel
        this.booksTableView.setItems(this.listBooksModel.getBookFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());          //bindujemy kolumnę tytuł z titleProperty
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());     //analogicznie...
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        this.releaseColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));  //bookFx nie jest obiektem obserwowalnym więc musimy go opakować w SimpleObjectProperty

        this.deleteColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>() {             //budujemy komórki w naszych kolumnach
            Button button = createDeleteButton();

            @Override
            protected void updateItem(BookFx item, boolean empty) {                             //empty - czy obiekt jest pusty
                super.updateItem(item, empty);

                if (empty) {                                                                    //gdy wiersz jest pusty to usuwamy przycisk "x" z komórki
                    setGraphic(null);
                    return;
                }

                if (!empty) {                                                                   //gdy wiersz nie jest pusty to ustawiamy przycisk "x" w komórce
                    setGraphic(button);
                    button.setOnAction(event -> {                                               //to robi po naciśnięciu przycisku "x"
                        try {
                            listBooksModel.deleteBook(item);
                        } catch (ApplicationException e) {
                            DialogsUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });
    }

    private Button createDeleteButton() {                                           //utworzenie przycisku x do usuwania książek z tabeli (bez użycia sceneBuilder)
        Button button = new Button();
        Image image = new Image(this.getClass().getResource("JavaFX/MojaBiblioteczka/Icons/deleteIcon.png").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }


    public void onFilterComboBoxAction() {
        System.out.println(this.listBooksModel.categoryFxObjectPropertyProperty().get());
        this.listBooksModel.filterBookList();

    }

    public void onClearCategoryCBoxButtonAction() {                                          //naciśnięcie "X" przy filterCategory
        this.categoryFilterComboBox.getSelectionModel().clearSelection();                   //czyści wybranie comboBoxa
    }

    public void onClearAuthorCBoxButtonAction() {                                            //naciśnięcie "X" przy filterAuthor
        this.authorFilterComboBox.getSelectionModel().clearSelection();

    }
}
















