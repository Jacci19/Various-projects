package Card_games.BlackJack_FX.Controlers;

import Card_games.BlackJack_FX.BJ.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.concurrent.TimeUnit;

import java.io.IOException;

public class BJ_bonusControl {

    private BJ_mainControl mainControl;

    @FXML
    private AnchorPane bonusAnchorPane;
    @FXML
    private Button bonusReturnButton;

    @FXML
    void bonusReturnButtonOnAction(ActionEvent event)   throws IOException {


/*
        Deck deck = new Deck();
        int j = 0;
        for (Card card: deck.getCards()){

            try{
                //TimeUnit.MILLISECONDS.sleep(200);

                Thread.sleep(1000);
                System.out.println(j);
                placeCard(card,  10+(10*j), 360+(2*j), j * 10);
                j++;
            }
            catch (java.lang.InterruptedException err){
                err.getMessage();
            }
        }
*/


        mainControl.loadMenuScreen();
    }
    @FXML
    private ImageView imgTest;

    @FXML
    public void initialize() throws IOException {
        Card card1 = new Card(Suit.Club, Value.Jack);
        setImg(imgTest, card1);

        placeCard(new Card(Suit.Club, Value.Jack), 40, 600, -20);
        placeCard(new Card(Suit.Heart, Value.Nine), 50, 600, -10);
        placeCard(new Card(Suit.Club, Value.King), 60, 600, 0);
        placeCard(new Card(Suit.Diamond, Value.Two), 70, 600, 10);
        placeCard(new Card(Suit.Spade, Value.Ace), 80, 600, 20);

        for (int i=0; i<30; i++){
            placeCard(new Card(Suit.Heart, Value.King),  100+(18*i), 100+(2*i), i * 10);
        }


        Deck deck = new Deck();
        int j = 0;
        for (Card card: deck.getCards()){
            placeCard(card,  10+(10*j), 360+(2*j), j * 10);
            j++;
        }


    }


    public void setMainControl(BJ_mainControl mainCtrl) {
        this.mainControl = mainCtrl;
    }

    void setImg(ImageView imageView, Card card){
       Image img = new Image("Card_games/BlackJack_FX/Img/"+ card.getImgFileName() +".png");
       imageView.setImage(img);
    }

    void placeCard(Card card, int x, int y, int angle){
        ImageView imgView = new ImageView();
        imgView.setX((double)(x));
        imgView.setY((double)(y));
        imgView.setRotate((double)(angle));
        setImg(imgView, card);
        bonusAnchorPane.getChildren().add(imgView);
    }


}