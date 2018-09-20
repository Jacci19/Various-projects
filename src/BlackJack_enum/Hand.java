package BlackJack_enum;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    private ArrayList<Card> handCards;

    public Hand() {                                     //wypełnienie talii 52 kartami
        handCards = new ArrayList<>();
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void shuffle(){
        Collections.shuffle(handCards);                     //potasowanie kart
    }

    public Card handOutCard(){
        return handCards.remove(0);                 //zwraca pierwszą kartę z listy i jednocześnie usuwa ją z tej listy
    }

    public int sumHandCardsValues(){
        int sum = 0;
        for (int i=0; i<handCards.size(); i++){
            sum += handCards.get(i).getCardIntValue();
        }
        return sum;
    }
}
