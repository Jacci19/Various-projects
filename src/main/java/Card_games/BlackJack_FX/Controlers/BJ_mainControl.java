package Card_games.BlackJack_FX.Controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BJ_mainControl {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    public void initialize() throws IOException {
        loadMenuScreen();
    }



    public void loadMenuScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Card_games/BlackJack_FX/FXML/BJ_menuScreen.fxml"));
        AnchorPane myAnchorPane = loader.load();                                                // ta linia wymuszała (try/catch) albo (import java.io.IOException i throws IOException)

        BJ_menuControl menuContr = loader.getController();
        menuContr.setMainControl(this);
        setScreen(myAnchorPane);
    }


    public void setScreen(AnchorPane myAnchPane) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(myAnchPane);                                //ładuje AnchorPane z menuScreen
    }
}