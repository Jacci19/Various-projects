package BlackJack_enum;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        Deck croupierDeck = new Deck();
        Hand myHand = new Hand();


        System.out.println("CroupierDeck:  " + croupierDeck.getCards());
        System.out.println("CroupierDeck' size:  " + croupierDeck.getCards().size());
        System.out.println("My Hand:  " + myHand.getHandCards());
        System.out.println("Croupier's Card nr 2:  " + croupierDeck.getCards().get(2));
        System.out.println("Card's suit:  " + croupierDeck.getCards().get(2).getCardSuit());
        System.out.println("Card's value:  " + croupierDeck.getCards().get(2).getCardValue());
        System.out.println("Card's int value:  " + croupierDeck.getCards().get(2).getCardIntValue());


        System.out.print("_____BLACKJACK_____\nLet's begin the game. I give you two cards: ");
        croupierDeck.shuffle();
        croupierDeck.giveCards(2, myHand, true);
        System.out.println("Your hand: " + myHand.getHandCards());
        System.out.println("My deck's size:  " + croupierDeck.getCards().size());
        System.out.println("Sum of your cards is " + myHand.sumHandCardsValues());

    }
}
