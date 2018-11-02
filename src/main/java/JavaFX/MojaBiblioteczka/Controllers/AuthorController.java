package JavaFX.MojaBiblioteczka.Controllers;

import JavaFX.MojaBiblioteczka.ModelFx.AuthorModel;
import JavaFX.MojaBiblioteczka.Utils.DialogsUtils;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AuthorController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button addAuthorButton;

    private AuthorModel authorModel;

    public void initialize(){
        this.authorModel = new AuthorModel();
        this.authorModel.authorFxObjectPropertyProperty().get().nameProperty().bind(this.nameTextField.textProperty());             //bindowanie pola tekstowego z objProp autora
        this.authorModel.authorFxObjectPropertyProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());       //bindowanie pola tekstowego z objProp autora
        this.addAuthorButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty().or(this.surnameTextField.textProperty().isEmpty()));    //if nameField or surnameField is empty then addButton is disabled
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
}