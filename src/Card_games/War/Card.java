package Card_games.War;


public class Card {

    private Suit cardSuit;
    private Value cardValue;

    Card(final Suit suit, final Value value) {
        this.cardSuit = suit;
        this.cardValue = value;
    }

    private int getCardIntValue() {
        return cardValue.getValue();
    }

    public String toString(){
        return cardValue.toString() + "_" + cardSuit.toString();
    }

    String compare(Card otherCard){
        if (this.getCardIntValue() > otherCard.getCardIntValue()) return "bigger";
        else if (this.getCardIntValue() < otherCard.getCardIntValue()) return "smaller";
        else return "equal";
    }


}
