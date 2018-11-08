package JavaFX.MyMix.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane mainStackPane;
    @FXML
    public void initialize() throws IOException {
        loadMenuScreen();
    }

    public void loadMenuScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/MenuScreen.fxml"));
        Pane myPane = loader.load();                                                // ta linia wymuszała (try/catch) albo (import java.io.IOException i throws IOException)


        MenuController menuContr = loader.getController();
        menuContr.setMainContr(this);
        setScreen(myPane);
    }

    public void setScreen(Pane myPane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(myPane);                                //ładuje pane z menuScreen
    }


}
