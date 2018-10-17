package JavaFX.MojaBiblioteczka.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
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
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));                //skopiowane z Main (3 linie + czwarta przerobiona na parent
        ResourceBundle bundle = ResourceBundle.getBundle("JavaFX.MojaBiblioteczka.Bundles.messages");
        loader.setResources(bundle);
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(parent);
    }

    public void onZamknijMItemAction() {
        Platform.exit();
        System.exit(0);
    }

    public void onMItemCaspianAction() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void onMItemModenaAction() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void onMItemZawszeWierzchAction(ActionEvent myActionEvent) {
        Stage myStage = (Stage)borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem)myActionEvent.getSource()).selectedProperty().get();
        myStage.setAlwaysOnTop(value);
    }

    public void onMItemAboutAction() {
    }
}
