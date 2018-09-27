package Card_games.BlackJack_FX.Controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class BJ_bonusControl {

    private BJ_mainControl mainControl;

    @FXML
    private Button bonusReturnButton;

    @FXML
    void bonusReturnButtonOnAction(ActionEvent event)   throws IOException {
        mainControl.loadMenuScreen();
    }

    public void setMainControl(BJ_mainControl mainCtrl) {
        this.mainControl = mainCtrl;
    }


}