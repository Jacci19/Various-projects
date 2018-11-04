package JavaFX.MojaBiblioteczka.Controllers;

import JavaFX.MojaBiblioteczka.ModelFx.AuthorFx;
import JavaFX.MojaBiblioteczka.ModelFx.BookFx;
import JavaFX.MojaBiblioteczka.ModelFx.CategoryFx;
import JavaFX.MojaBiblioteczka.ModelFx.ListBooksModel;
import JavaFX.MojaBiblioteczka.Utils.DialogsUtils;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class ListBooksController {

    @FXML
    private TableView<BookFx> booksTableView;
    @FXML
    private TableColumn<BookFx, String> titleColumn;
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

    private ListBooksModel listBooksModel;

    @FXML
    void initialize(){
        this.listBooksModel = new ListBooksModel();
        try {
            this.listBooksModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

        //bindujemy tabelę i wszystkie kolumny z naszą listą, która jest w BookModel
        this.booksTableView.setItems(this.listBooksModel.getBookFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());          //bindujemy kolumnę tytuł z titleProperty
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());     //analogicznie...
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        this.releaseColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
    }
}
