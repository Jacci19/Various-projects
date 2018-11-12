package Card_games.BlackJack_FX.BJ;

//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

import static Card_games.BlackJack_FX.Controlers.BJ_gameControl.REVERSE_CARD_X;
import static Card_games.BlackJack_FX.Controlers.BJ_gameControl.REVERSE_CARD_Y;
import Card_games.BlackJack_FX.Controlers.BJ_gameControl.*;

public class Deck {

    private ArrayList<Card> cards;


    public Deck() {                                     //wypełnienie talii 52 kartami
        this.cards = new ArrayList<>();
        for (int s = 0; s < 4; s++){                                                //liczba kolorów (suits) w talii
            for (int v = 0; v < 13; v++){                                           //liczba wartości (values) w talii
                cards.add(new Card(Suit.values()[s], Value.values()[v]));
            }
        }
        this.shuffle();
    }

    public Boolean needShuffling(){
        return (this.cards.size() < 10);            //jak jest < 10 to zwraca true, jak większe to zwraca false
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle(){
        Collections.shuffle(cards);                     //potasowanie kart
    }


    public void giveCards (Pane pane, int ilosc, Hand player, String side){
        for (int i = 0; i < ilosc; i++){
            Card someCard = cards.get(0);
            cards.remove(0);

           //setCardsInDeckValue(this.cards.size());                         // metoda zaimportowana z Card_games.BlackJack_FX.Controlers.BJ_gameControl

            player.getHandCards().add(someCard);
            placeCard(pane, someCard, player.getHandCards().size(), side);
        }
    }


    void setImg(ImageView imageView, Card card){
        Image img = new Image("Card_games/BlackJack_FX/Img/"+ card.getImgFileName() +".png");
        imageView.setImage(img);
    }

    public void placeCard(Pane pane, Card card, int which, String side){

        double newX, newY;

        ImageView imgView = new ImageView();
        imgView.setX(REVERSE_CARD_X);
        imgView.setX(REVERSE_CARD_Y);

        if (side.equals("up")){
            newX = (double)(180+(70*which));
            newY = (double)(76);
        }
        else{
            newX = (double)(-30+(70*which));
            newY = (double)(460);
        }


        setImg(imgView, card);
        pane.getChildren().add(imgView);
        animateCardMotion(imgView, newX, newY);
    }

    private void animateCardMotion (ImageView imageView, double newX, double newY){

        final Timeline timeline = new Timeline();
        final int duration = (200+ (int)(newX/3) + (int)(newY/2));                                          //aby prędkość ruchu kard była mniej więcej jednakowa
        timeline.setCycleCount(1);
        //timeline.setAutoReverse(true);

        final KeyValue kv = new KeyValue(imageView.xProperty(), newX, Interpolator.EASE_BOTH);              // wartość klatki kluczowej
        final KeyFrame kf = new KeyFrame(Duration.millis(duration), kv);                                    // klatka kluczowa
        timeline.getKeyFrames().add(kf);                                                                    // dodanie kk do timeline

        final KeyValue kv2 = new KeyValue(imageView.yProperty(), newY,Interpolator.EASE_BOTH);              //  to działa równocześnie z poprzednim
        final KeyFrame kf2 = new KeyFrame(Duration.millis(duration), kv2);                                        //
        timeline.getKeyFrames().add(kf2);                                                                    //

        timeline.play();
    }


/*
    void giveCards (Pane pane, int ilosc, Hand player, boolean info,  int x, int y, int angle){
        for (int i = 0; i < ilosc; i++){
            Card someCard = cards.get(0);
            cards.remove(0);

            player.getHandCards().add(someCard);
            if (info){
                if (i < ilosc - 1) System.out.print(someCard + ", ");
                else System.out.println(someCard);
            }
        }
    }
*/

/*
    public void placeCard(Pane pane, Card card, int x, int y, int angle){
        ImageView imgView = new ImageView();
        imgView.setX((double)(x));
        imgView.setY((double)(y));
        imgView.setRotate((double)(angle));
        setImg(imgView, card);
        pane.getChildren().add(imgView);
    }
*/



}
