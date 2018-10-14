package JavaFX.MojaBiblioteczka.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane borderPane;                                              //taką nazwę nadaliśmy w id.

    @FXML
    private TopMenuButtonsController topMenuButtonsController;                  //nazwa z fx:id + Controller

    @FXML
    private void initialize(){
        topMenuButtonsController.setMainController(this);
    }
}
