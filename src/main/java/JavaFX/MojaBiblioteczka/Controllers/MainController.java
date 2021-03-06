package JavaFX.MojaBiblioteczka.Controllers;

import JavaFX.MojaBiblioteczka.Utils.DialogsUtils;
import JavaFX.MojaBiblioteczka.Utils.FxmlUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private BorderPane borderPane;                                              //taką nazwę nadaliśmy w id.

    @FXML
    private TopMenuButtonsController topMenuButtonsController;                  //nazwa z fx:id + Controller

    @FXML
    private void initialize(){
        topMenuButtonsController.setMainController(this);
    }

    public void setCenter(String fxmlPath){
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

    public void onZamknijMItemAction() {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog();        //zdefiniowane w DialogUtils
        if (result.get()==ButtonType.OK){                                       //Jeśli wcisnięto OK to zamykamy aplikację
            Platform.exit();
            System.exit(0);
        }
    }

    public void onMItemCaspianAction() {                                                        //zmienia styl wyglądu programu na caspian
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void onMItemModenaAction() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);                      //zmienia styl wyglądu programu na modena
    }

    public void onMItemZawszeWierzchAction(ActionEvent myActionEvent) {
        Stage myStage = (Stage)borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem)myActionEvent.getSource()).selectedProperty().get();
        myStage.setAlwaysOnTop(value);
    }

    public void onMItemAboutAction() {
        DialogsUtils.dialogAboutApplication();                                      //wywołanie okna (alert) z Utils/DialogUtils
    }
}
