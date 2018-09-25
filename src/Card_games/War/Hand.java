package Card_games.War;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    private ArrayList<Card> handCards;

    public Hand() {
        handCards = new ArrayList<>();
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void handInfo(String who) {
        System.out.println("--" + who.toUpperCase().charAt(0) + who.substring(1) + "'s hand: (" + getHandCards().size() + ") "  + this.getHandCards());       //aby who zaczynało się wielką literą a w następnej linii małą
   }

    public Card deckOutCard(){
        return  handCards.remove(0);                 //zwraca pierwszą kartę z listy i jednocześnie usuwa ją z tej listy
    }

    public void addCardToHandEnd(Card card){
        this.handCards.add(handCards.size(), card);
    }

    public void shuffle(){
        Collections.shuffle(handCards);                     //potasowanie kart
    }

}
