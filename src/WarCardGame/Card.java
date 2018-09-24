package WarCardGame;

import java.util.Objects;

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

    public int getCardIntValue() {
        return cardValue.getValue();
    }



    public String toString(){
        return cardValue.toString() + "_" + cardSuit.toString();
    }


    public String compare(Card otherCard){
        if (this.getCardIntValue() > otherCard.getCardIntValue()) return "bigger";
        else if (this.getCardIntValue() < otherCard.getCardIntValue()) return "smaller";
        else return "equal";
    }


}
