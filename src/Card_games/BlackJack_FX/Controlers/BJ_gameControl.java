package Card_games.BlackJack_FX.Controlers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class BJ_gameControl {

    private BJ_mainControl mainControl;                                     //deklaracja obiektu g³ównego kontrolera

    @FXML
    private Button gameReturnButton;

    @FXML
    void gameReturnButtonOnAction()  throws IOException {
        mainControl.loadMenuScreen();
    }

    public void setMainControl(BJ_mainControl mainCtrl) {
        this.mainControl = mainCtrl;
    }
}
