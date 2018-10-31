package Card_games.BlackJack_FX.BJ;


public class Card {

    private Suit cardSuit;
    private Value cardValue;
    private String imgFileName;

    public Card(final Suit suit, final Value value) {
        this.cardSuit = suit;
        this.cardValue = value;
        this.imgFileName = value.toString() + "_" + suit.toString();
    }

    public int getCardIntValue() {
        return cardValue.getValue();
    }

    public String getImgFileName() {
        return imgFileName;
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
