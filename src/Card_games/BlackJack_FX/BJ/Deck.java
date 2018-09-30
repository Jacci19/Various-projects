package Card_games.BlackJack_FX.BJ;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;

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

            //setCardsInDeckValue(String.valueOf(this.cards.size()));

            player.getHandCards().add(someCard);
            placeCard(pane, someCard, player.getHandCards().size(), side);
        }
    }


    void setImg(ImageView imageView, Card card){
        Image img = new Image("Card_games/BlackJack_FX/Img/"+ card.getImgFileName() +".png");
        imageView.setImage(img);
    }

    public void placeCard(Pane pane, Card card, int which, String side){

        ImageView imgView = new ImageView();
        if (side.equals("up")){
            imgView.setX((double)(180+(70*which)));
            imgView.setY((double)(76));
        }
        else{
            imgView.setX((double)(-30+(70*which)));
            imgView.setY((double)(460));
        }
        setImg(imgView, card);
        pane.getChildren().add(imgView);
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
