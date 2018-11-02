package JavaFX.MojaBiblioteczka.Controllers;

import JavaFX.MojaBiblioteczka.ModelFx.AuthorFx;
import JavaFX.MojaBiblioteczka.ModelFx.AuthorModel;
import JavaFX.MojaBiblioteczka.Utils.DialogsUtils;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

public class AuthorController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button addAuthorButton;

    @FXML
    private TableView<AuthorFx> authorTableView;

    @FXML
    private TableColumn<AuthorFx, String> nameColumn;

    @FXML
    private TableColumn<AuthorFx, String> surnameColumn;


    private AuthorModel authorModel;

    public void initialize(){
        this.authorModel = new AuthorModel();
        try {
            this.authorModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.authorModel.authorFxObjectPropertyProperty().get().nameProperty().bind(this.nameTextField.textProperty());             //bindowanie pola tekstowego z objProp autora
        this.authorModel.authorFxObjectPropertyProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());       //bindowanie pola tekstowego z objProp autora
        this.addAuthorButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty().or(this.surnameTextField.textProperty().isEmpty()));    //if nameField or surnameField is empty then addButton is disabled

        this.authorTableView.setItems(this.authorModel.getAuthorFxObservableList());                        //podpięcie listy do tableView
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());                //ustalamy że kolumna name ma operować na nameProperty które jest w autorze
        this.surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());                            //uaktywnia komórki tabeli (po kliknięciu można je edytować)
        this.surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.authorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{    //klikanie na wiersz tabeli
            this.authorModel.setAuthorFxObjectPropertyEdit(newValue);                                  //to robię z wierszem tabeli który kliknąłem
        } );
    }

    @FXML
    void onAddAuthorButtonAction() {
        //System.out.println(this.authorModel.getAuthorFxObjectProperty().getName());
        //System.out.println(this.authorModel.getAuthorFxObjectProperty().getSurname());
        try {
            this.authorModel.saveAuthorInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

        this.nameTextField.clear();
        this.surnameTextField.clear();

    }

    public void onNameEditCommit(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        this.authorModel.getAuthorFxObjectPropertyEdit().setName(authorFxStringCellEditEvent.getNewValue());       //pobieramy obiekt który kliknął user i ustawiamy mu name
        updateInDatabase();
    }

    public void onSurnameEditCommit(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        this.authorModel.getAuthorFxObjectPropertyEdit().setSurname(authorFxStringCellEditEvent.getNewValue());       //pobieramy obiekt który kliknął user i ustawiamy mu surname
        updateInDatabase();
    }

    private void updateInDatabase() {
        try {
            this.authorModel.saveAuthorEditInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}