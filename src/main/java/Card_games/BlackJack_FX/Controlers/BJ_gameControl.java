package Card_games.BlackJack_FX.Controlers;

import Card_games.BlackJack_FX.BJ.Hand;
import Card_games.BlackJack_FX.BJ.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;


public class BJ_gameControl{

    public static final double REVERSE_CARD_X = 40.0;
    public static final double REVERSE_CARD_Y = 76.0;

    public boolean isAnimationCheckboxSelected = true;
    private Hand myHand = new Hand("down");
    private Hand enemyHand = new Hand("up");
    private static Deck croupierDeck = new Deck();
    private BJ_mainControl mainControl;                                     //deklaracja obiektu głównego kontrolera
    private static int gamePart;
    private static boolean overload = false;
    private final static int overloadValue = 31;
    private static int gameNumber = 0;

//    private BJ_menuControl menuControl;
//    private Boolean bb;

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
    @FXML
    private CheckBox animationCheckBox;

    public void initialize() {
        gamePart = 1;
        addReversCard();
        takeEnoughVBox.setVisible(false);
        cardsInDeck.setText(String.valueOf(croupierDeck.getCards().size()));
        croupierLabel.setText("Welcome. I'm the croupier of this game. Can we start?");
        //System.out.println("MC.isNewBlackJackGame ____" + MC.isNewBlackJackGame());
        //bb = menuControl.isNewBlackJackGame();
    }


    @FXML
    void gameEnoughButtonOnAction() {
        takeEnoughVBox.setVisible(false);
        croupierLabel.setText("OK. Now is my turn. There is my first two cards");
        croupierDeck.giveCards(gameAnchorPane, 2, enemyHand, "up");
        yesVBox.setVisible(true);
        gamePart = 4;
    }

    @FXML
    void gameReturnButtonOnAction() throws IOException {
        mainControl.loadMenuScreen();
    }

    @FXML
    void gameTakeButtonOnAction(ActionEvent event) {
        croupierDeck.giveCards(gameAnchorPane, 1, myHand, "down");
        cardsInDeck.setText(String.valueOf(croupierDeck.getCards().size()));                        //odswieżenie wartości labela
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
    void onAnimationCheckBoxAction() {
        System.out.println(animationCheckBox.isSelected());
    }


    @FXML
    void yesButtonOnAction() {

        cardsInDeck.setText(String.valueOf(croupierDeck.getCards().size()));                        //odswieżenie wartości labela
        switch (gamePart) {
            case 1:                                                                                     //_1_początek gry
                croupierLabel.setText("Welcome. I'm the croupier of this game. Can we start?.");
                startGame();
                break;
            case 2:                                                                                     //_2_po summary
                croupierLabel.setText("Do you want to play again?");
                gamePart = 3;
                break;
            case 3:                                                                                     //_3_po _2_
                gameAnchorPane.getChildren().removeIf(n -> n instanceof ImageView);                     //usuniecie kart ze stosu
                startGame();
                break;
            case 4:                                                                                     //_4_ przed summary
                switch (enemyHand.enemyTactic(overloadValue, croupierLabel, croupierDeck, gameAnchorPane)) {
                    case "playerWin":
                        gameStatus = "playerWin";
                        gamePart = 5;
                        break;
                    case "checkResult":
                        if (myHand.sumHandCardsValues() > enemyHand.sumHandCardsValues()) {
                            gameStatus = "playerWin_after_Check";

                        } else if (myHand.sumHandCardsValues() < enemyHand.sumHandCardsValues()) {
                            gameStatus = "enemyWin_after_Check";
                        } else {
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

    private void startGame() {
        gameNumber++;
        yesVBox.setVisible(false);
        resetGame();
        String welcomeText;
        System.out.println("start");
        takeEnoughVBox.setVisible(true);

        if (gameNumber == 1) {
            welcomeText = "So let's begin. I'm shuffling a deck and give you two cards.\nYou may take next one or pass. Sum of your cards: ";
        } else if (croupierDeck.needShuffling()) {
            croupierDeck = new Deck();
            welcomeText = "Only few cards left in deck. I'm taking a new deck. I give you two cards.\nYou may take next one or pass. Sum of your cards: ";
        } else {
            welcomeText = "Round " + gameNumber + ". I give you two cards.\nYou may take next one or pass. Sum of your cards: ";
        }
        croupierDeck.giveCards(gameAnchorPane, 2, myHand, "down");
        croupierLabel.setText(welcomeText + myHand.sumHandCardsValues());

    }

    void setMainControl(BJ_mainControl mainCtrl) {
        this.mainControl = mainCtrl;
    }

    public void summary(String status) {
        yesVBox.setVisible(true);
        switch (status) {
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
        try {
            writeScoresToFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("___Pliku nie znaleziono!!!___");
        }
    }

    private void resetGame() {
        myHand.getHandCards().clear();
        enemyHand.getHandCards().clear();
        overload = false;
        addReversCard();
    }

    private void addReversCard() {
        ImageView imgView = new ImageView();
        imgView.setX(REVERSE_CARD_X);
        imgView.setY(REVERSE_CARD_Y);
        Image img = new Image("/Card_games/BlackJack_FX/Img/_rewers.png");
        imgView.setImage(img);
        gameAnchorPane.getChildren().add(imgView);
    }

    private void writeScoresToFile() throws IOException {
        System.out.println("writeScoresToFile");
        String filePath = "src/main/resources/Card_games/BlackJack_FX/BlackJackFX.txt";

        FileWriter fw = new FileWriter(filePath);
        //FileWriter fw = new FileWriter(filePath, true);                      //jeśli nie chcemy aby nadpisywało stare
        System.out.println("Scores: " + myHand.getScore() + " : " + enemyHand.getScore());
        fw.write(Integer.toString(myHand.getScore()) + "\n");
        fw.write(Integer.toString(enemyHand.getScore()));
        //fw.write(Integer.toString(tablica[i][j])+" ");                    //po zastosowaniu tego zamiast (char) w pliku wyświetla się dobrze
        //fw.write("\r\n");                                               //przejście do następnej linii (r - aby działało w notatniku), zakomentuj aby w 3. konsoli były tylko 0 i 1
        fw.close();
        //System.out.println("MC.isNewBlackJackGame: " + MC.isNewBlackJackGame);
}


    public boolean isAnimationCheckboxChecked() {
        return animationCheckBox.isSelected();
    }



}
