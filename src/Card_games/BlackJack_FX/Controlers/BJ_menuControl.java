package Card_games.BlackJack_FX.Controlers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BJ_menuControl {

    private BJ_mainControl mainControl;                                         //deklaracja obiektu g³ównego kontrolera

    @FXML
    private Button gameButton;
    @FXML
    private Button bonusButton;
    @FXML
    private Button exitButton;


    @FXML
    public void gameButtonOnAction(ActionEvent event) throws IOException {
        System.out.println("Wciœniêto gameButton");
        FXMLLoader gameLoader = new FXMLLoader(this.getClass().getResource("../FXML/BJ_gameScreen.fxml"));      //wczytuje loader fxml planszy game

        //Pane gamePane = gameLoader.load();
        AnchorPane gamePane = gameLoader.load();                    //³aduje fxml do g³ównego kontenera planszy game
        BJ_gameControl gameContr = gameLoader.getController();                  //wczytuje kontroler planszy game
        gameContr.setMainControl(mainControl);
        mainControl.setScreen(gamePane);                                        //ustawia w mainAnchorPane planszê game


    }

    @FXML
    void bonusButtonOnAction(ActionEvent event)  throws IOException {
        System.out.println("Wciœniêto bonusButton");
        FXMLLoader bonusLoader = new FXMLLoader(this.getClass().getResource("../FXML/BJ_bonusScreen.fxml"));

        AnchorPane bonusPane = bonusLoader.load();                      //³aduje fxml do g³ównego kontenera planszy bonus
        BJ_bonusControl bonusContr = bonusLoader.getController();       //wczytuje kontroler planszy bonus
        bonusContr.setMainControl(mainControl);                         //ustawia dostêp do g³ównego kontrolera
        mainControl.setScreen(bonusPane);                               //ustawia w mainAnchorPane planszê bonus (poprzez g³ówny kontroler)
    }

    @FXML
    void exitButtonOnAction(ActionEvent event) {
        System.out.println("Wciœniêto ExitBtn");
        Platform.exit();
    }

    public void setMainControl(BJ_mainControl mainContr) {
        this.mainControl = mainContr;
    }


}
