package BlackJack_enum;

public class Card {

    private Suit cardSuit;
    private Value cardValue;

    public Card(Suit suit, Value value) {
        cardSuit = suit;
        cardValue = value;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    public Value getCardValue() {
        return cardValue;
    }



    public String toString(){
        return cardValue.toString() + "_" + cardSuit.toString();
    }
}
