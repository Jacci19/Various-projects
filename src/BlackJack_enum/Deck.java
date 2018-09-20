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

    public Card deckOutCard(){
        return cards.remove(0);                 //zwraca pierwszą kartę z listy i jednocześnie usuwa ją z tej listy
    }

    public void giveCards (int ilosc, Hand player, boolean info){
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
}
