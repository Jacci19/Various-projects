package BlackJack_enum;

public class Main {
    public static void main(String[] args) {
        System.out.println("BLACKJACK");

        Deck myDeck = new Deck();
        System.out.println(myDeck.getCards());
        myDeck.shuffle();
        System.out.println(myDeck.getCards());


    }
}
