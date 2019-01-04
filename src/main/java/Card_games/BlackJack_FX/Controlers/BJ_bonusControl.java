package Card_games.BlackJack_FX.Controlers;

import Card_games.BlackJack_FX.BJ.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class BJ_bonusControl {

    private BJ_mainControl mainControl;

    private int idx;
    private int offset = 0;
    private Deck deck;


    @FXML
    private AnchorPane bonusAnchorPane;
    @FXML
    private Button bonusReturnButton;

    @FXML
    void bonusReturnButtonOnAction(ActionEvent event)   throws IOException {

        mainControl.loadMenuScreen();



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
    }

    @FXML
    private ImageView imgTest;

    @FXML
    public void initialize() throws IOException {
/*
        Card card1 = new Card(Suit.Club, Value.Jack);
        setImg(imgTest, card1);

        placeCard(new Card(Suit.Club, Value.Jack), 40, 600, -20);
        placeCard(new Card(Suit.Heart, Value.Nine), 50, 600, -10);
        placeCard(new Card(Suit.Club, Value.King), 60, 600, 0);
        placeCard(new Card(Suit.Diamond, Value.Two), 70, 600, 10);
        placeCard(new Card(Suit.Spade, Value.Ace), 80, 600, 20);
*/

        idx = 0;
        deck = new Deck();


        AnimationTimerExt timer = new AnimationTimerExt(200) {

            @Override
            public void handle() {
                int x = 100 + (15 * idx);
                int y = 80 + (0 * idx) + offset;
                if (y < 500) {
                    placeCard(takeCard(), x, y, idx * 8);
                }
            }


        };


        timer.start();


//        for (int i=0; i<50; i++){
//            placeCard(new Card(Suit.Heart, Value.King),  80+(18*i), 50+(2*i), i * 8);
//        }


//        Deck deck = new Deck();
//        int j = 0;
//        for (Card card: deck.getCards()){
//            placeCard(card,  10+(15*j), 400+(2*j), j * 9);
//            j++;
//        }
    }

    private Card takeCard(){
        Card card = deck.getCards().get(idx);
        idx++;
        if (idx > 51){
            idx = 0;
            offset += 80;
            deck = new Deck();
        }
        return card;
    }


    public void setMainControl(BJ_mainControl mainCtrl) {
        this.mainControl = mainCtrl;
    }

    void setImg(ImageView imageView, Card card){
       Image img = new Image("Card_games/BlackJack_FX/Img/"+ card.getImgFileName() +".png");
       imageView.setImage(img);
    }

    public void placeCard(Card card, int x, int y, int angle){
        ImageView imgView = new ImageView();
        imgView.setX((double)(x));
        imgView.setY((double)(y));
        imgView.setRotate((double)(angle));
        setImg(imgView, card);
        bonusAnchorPane.getChildren().add(imgView);
    }


}