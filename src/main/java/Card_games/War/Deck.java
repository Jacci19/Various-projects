package Card_games.War;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    Deck() {                                     //wypełnienie talii 52 kartami
        this.cards = new ArrayList<>();
        for (int s = 0; s < 4; s++){                                                //liczba kolorów (suits) w talii
            for (int v = 0; v < 13; v++){                                           //liczba wartości (values) w talii
                cards.add(new Card(Suit.values()[s], Value.values()[v]));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    void shuffle(){
        Collections.shuffle(cards);                     //potasowanie kart
    }

    void giveCards (int ilosc, Hand player, boolean info){
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
