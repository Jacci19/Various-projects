package BlackJack_enum;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {                                     //wypełnienie talii 52 kartami
        cards = new ArrayList<>();
        for (int s = 0; s < 4; s++){
            for (int v = 0; v < 13; v++){
                cards.add(new Card(Suit.values()[s], Value.values()[v]));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle(){
        Collections.shuffle(cards);                     //potasowanie kart
    }

    public Card handOutCard(){
        return cards.remove(0);                 //zwraca pierwszą kartę z listy i jednocześnie usuwa ją z tej listy
    }
}
