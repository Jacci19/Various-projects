package JavaFX.MyMix.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Window_04_Controller {

    private MainController mainContr;

    @FXML
    private Pane window04Pane;
    @FXML
    public void initialize(){

    }








    @FXML
    public void onPowrotBtnAction() throws IOException {
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
