package Card_games.BlackJack_FX.Controlers;

import Card_games.BlackJack_FX.BJ.Hand;
import Card_games.BlackJack_FX.BJ.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;

import Card_games.BlackJack_FX.BJ.Deck.*;


public class BJ_gameControl {

    private Hand myHand = new Hand("down");
    private Hand enemyHand = new Hand("up");
    private static Deck croupierDeck = new Deck();
    private BJ_mainControl mainControl;                                     //deklaracja obiektu g��wnego kontrolera
    private static int gamePart;
    private static boolean overload = false;
    private final static int overloadValue = 31;
    private static int gameNumber = 0;



    private static String gameStatus = "inGame";    //inGame, playerWin, enemyWin, checkResult

    @FXML
    private AnchorPane gameAnchorPane;
    @FXML
    private Button gameReturnButton;
    @FXML
    private Label croupierLabel;
    @FXML
    private VBox yesVBox;
    @FXML
    private Button yesButton;
    @FXML
    private Button gameEnoughButton;
    @FXML
    private Button gameTakeButton;
    @FXML
    private VBox takeEnoughVBox;
    @FXML
    private Label myScoreLabel;
    @FXML
    private Label enemyScoreLabel;
    @FXML
    private Label cardsInDeck;

    public void initialize(){
        gamePart = 1;
        addReversCard();
        takeEnoughVBox.setVisible(false);
        cardsInDeck.setText(String.valueOf(croupierDeck.getCards().size()));
        croupierLabel.setText("Welcome. I'm the croupier of this game. Can we start?");
    }


    @FXML
    void gameEnoughButtonOnAction(ActionEvent event) {
        takeEnoughVBox.setVisible(false);
        croupierLabel.setText("OK. Now is my turn. There is my first two cards");
        croupierDeck.giveCards(gameAnchorPane, 2, enemyHand, "up");
        yesVBox.setVisible(true);
        gamePart = 4;


    }

    @FXML
    void gameReturnButtonOnAction(ActionEvent event) throws IOException {
        mainControl.loadMenuScreen();
    }

    @FXML
    void gameTakeButtonOnAction(ActionEvent event) {
        croupierDeck.giveCards(gameAnchorPane, 1, myHand, "down");
        cardsInDeck.setText(  String.valueOf(croupierDeck.getCards().size())  );                        //odswie�enie warto�ci labela
        if (myHand.sumHandCardsValues() >= overloadValue) overload = true;
        if (overload) {
            croupierLabel.setText("Sum of your cards: " + myHand.sumHandCardsValues() + "\nYou overloaded! YOU LOSE.");
            takeEnoughVBox.setVisible(false);

            gameStatus = "enemyWin";
            summary(gameStatus);

        } else {
            croupierLabel.setText("You may take next card or pass. Sum of your cards: " + myHand.sumHandCardsValues());
        }

    }


    @FXML
    void yesButtonOnAction(ActionEvent event) {

        cardsInDeck.setText(  String.valueOf(croupierDeck.getCards().size())  );                        //odswie�enie warto�ci labela
        switch (gamePart) {
            case 1:                                                                                     //_1_pocz�tek gry
                croupierLabel.setText("Welcome. I'm the croupier of this game. Can we start?.");
                startGame();
                break;
            case 2:                                                                                     //_2_po summary
                croupierLabel.setText("Do you want to play again?");
                gamePart = 3;
                break;
            case 3:                                                                                     //_3_po _2_
                gameAnchorPane.getChildren().removeIf(n -> n instanceof ImageView);                     //usuniecie kart ze sto�u
                startGame();
                break;
            case 4:                                                                                     //_4_ przed summary
                switch (enemyHand.enemyTactic(overloadValue, croupierLabel, croupierDeck, gameAnchorPane)){
                    case "playerWin":
                        gameStatus = "playerWin";
                        gamePart = 5;
                        break;
                    case "checkResult":
                        if (myHand.sumHandCardsValues() > enemyHand.sumHandCardsValues()){
                            gameStatus = "playerWin_after_Check";

                        }
                        else if (myHand.sumHandCardsValues() < enemyHand.sumHandCardsValues()){
                            gameStatus = "enemyWin_after_Check";
                        }
                        else{
                            gameStatus = "draw";
                        }
                        gamePart = 5;
                        break;
                    case "inGame":
                        break;
                }
                break;
            case 5:
                summary(gameStatus);

                break;
        }
    }

    public void startGame() {
        gameNumber++;
        yesVBox.setVisible(false);
        resetGame();
        String welcomeText;
        System.out.println("start");
        takeEnoughVBox.setVisible(true);

        if (gameNumber == 1){
            welcomeText = "So let's begin. I'm shuffling a deck and give you two cards.\nYou may take next one or pass. Sum of your cards: ";
        }
        else if (croupierDeck.needShuffling()){
            croupierDeck = new Deck();
            welcomeText = "Only few cards left in deck. I'm taking a new deck. I give you two cards.\nYou may take next one or pass. Sum of your cards: ";
        }
        else{
            welcomeText = "Round " + gameNumber + ". I give you two cards.\nYou may take next one or pass. Sum of your cards: ";
        }
        croupierDeck.giveCards(gameAnchorPane, 2, myHand, "down");
        croupierLabel.setText(welcomeText + myHand.sumHandCardsValues());

    }

    public void setMainControl(BJ_mainControl mainCtrl) {
        this.mainControl = mainCtrl;
    }

    public void summary(String status){
        yesVBox.setVisible(true);
        switch (status){
            case "playerWin":
                myHand.scoreUp();
                myScoreLabel.setText(String.valueOf(myHand.getScore()));
                gamePart = 2;
                break;
            case "enemyWin":
                enemyHand.scoreUp();
                enemyScoreLabel.setText(String.valueOf(enemyHand.getScore()));
                gamePart = 2;
                break;
            case "playerWin_after_Check":
                myHand.scoreUp();
                myScoreLabel.setText(String.valueOf(myHand.getScore()));
                croupierLabel.setText("You win (" + myHand.sumHandCardsValues() + " : " + enemyHand.sumHandCardsValues() + ").");
                gamePart = 2;
                break;
            case "enemyWin_after_Check":
                enemyHand.scoreUp();
                enemyScoreLabel.setText(String.valueOf(enemyHand.getScore()));
                croupierLabel.setText("Enemy wins (" + myHand.sumHandCardsValues() + " : " + enemyHand.sumHandCardsValues() + ").");
                gamePart = 2;
                break;
            case "draw":
                croupierLabel.setText("Draw (" + myHand.sumHandCardsValues() + " : " + enemyHand.sumHandCardsValues() + "). No one gets a point");
                gamePart = 2;
                break;
        }
    }

    void resetGame(){
        myHand.getHandCards().clear();
        enemyHand.getHandCards().clear();
        overload = false;
        addReversCard();
    }
    void addReversCard(){
        ImageView imgView = new ImageView();
        imgView.setX((double)(40));
        imgView.setY((double)(76));
        Image img = new Image("Card_games/BlackJack_FX/Img/_rewers.png");
        imgView.setImage(img);
        gameAnchorPane.getChildren().add(imgView);
    }



/*
    public static void setCardsInDeckValue(int value){
        cardsInDeck.setText(String.valueOf(value));
    }
*/


}
