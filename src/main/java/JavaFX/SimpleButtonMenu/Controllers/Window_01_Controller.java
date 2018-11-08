package JavaFX.SimpleButtonMenu.Controllers;

import javafx.fxml.FXML;
import java.io.IOException;

public class Window_01_Controller {

    private MainController mainContr;

    @FXML
    public void onPowrotBtnAction() throws IOException{
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
