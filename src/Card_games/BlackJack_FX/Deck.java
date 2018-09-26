package Card_games.BlackJack_FX;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<>();

    Deck() {                                     //wypełnienie talii 52 kartami
        for (int s = 0; s < 4; s++){
            for (int v = 0; v < 13; v++){
                cards.add(new Card(Suit.values()[s], Value.values()[v]));
            }
        }
    }

    private ArrayList<Card> getCards() {
        return this.cards;
    }

    void shuffle(){
        Collections.shuffle(cards);                     //potasowanie kart
    }

    void giveCards (int ilosc, final Hand player, boolean info){
        for (int i = 0; i < ilosc; i++){
            final Card someCard = cards.get(0);
            cards.remove(0);
            player.getHandCards().add(someCard);
            if (info){
                if (i < ilosc - 1) {
                    System.out.print(someCard + ", ");
                }
                else {
                    System.out.println(someCard);
                }
            }
        }
    }

    int getSize(){
        return this.getCards().size();
    }
}
