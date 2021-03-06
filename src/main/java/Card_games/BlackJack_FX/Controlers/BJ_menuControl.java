package Card_games.BlackJack_FX.Controlers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BJ_menuControl {

    private BJ_mainControl mainControl;                                         //deklaracja obiektu głównego kontrolera
    private boolean isNewBlackJackGame;


    @FXML
    private Button gameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button bonusButton;
    @FXML
    private Button exitButton;

    @FXML
    public void gameButtonOnAction(ActionEvent event) throws IOException {
        System.out.println("Wcisnieto New game Button");
        isNewBlackJackGame = true;
        goToTheGame();
    }

    @FXML
    public void loadGameButtonOnAction(ActionEvent actionEvent) throws IOException  {
        System.out.println("Wcisnieto Load game Button");
        isNewBlackJackGame = false;
        goToTheGame();
    }

    @FXML
    void bonusButtonOnAction(ActionEvent event)  throws IOException {
        System.out.println("Wcisnieto bonusButton");
        FXMLLoader bonusLoader = new FXMLLoader(this.getClass().getResource("/Card_games/BlackJack_FX/FXML/BJ_bonusScreen.fxml"));

        AnchorPane bonusPane = bonusLoader.load();                      //ładuje fxml do głównego kontenera planszy bonus
        BJ_bonusControl bonusContr = bonusLoader.getController();       //wczytuje kontroler planszy bonus
        bonusContr.setMainControl(mainControl);                         //ustawia dostęp do głównego kontrolera
        mainControl.setScreen(bonusPane);                               //ustawia w mainAnchorPane planszę bonus (poprzez główny kontroler)
    }

    @FXML
    void exitButtonOnAction(ActionEvent event) {
        System.out.println("Wcisnieto ExitBtn");
        Platform.exit();
    }

    private void goToTheGame () throws IOException{

        System.out.println("AAAA  " + this.isNewBlackJackGame);
        FXMLLoader gameLoader = new FXMLLoader(this.getClass().getResource("/Card_games/BlackJack_FX/FXML/BJ_gameScreen.fxml"));      //wczytuje loader fxml planszy game
        AnchorPane gamePane = gameLoader.load();                                //ładuje fxml do głównego kontenera planszy game
        BJ_gameControl gameContr = gameLoader.getController();                  //wczytuje kontroler planszy game
        gameContr.setMainControl(mainControl);
        mainControl.setScreen(gamePane);                                        //ustawia w mainAnchorPane planszę game
    }


    public void setMainControl(BJ_mainControl mainContr) {
        this.mainControl = mainContr;
    }

    public Boolean isNewBlackJackGame() {
        return isNewBlackJackGame;
    }
}