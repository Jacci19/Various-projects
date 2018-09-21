package BlackJack_enum;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    Scanner inputKey;
    String playerDecision;
    boolean overload = false;
    private static int overloadValue = 31;
    private static int enemyRiskLevel = 7;

    private Deck croupierDeck = new Deck();
    private Hand myHand = new Hand();

    public static void main(String[] args) {

        Deck croupierDeck = new Deck();
        Hand myHand = new Hand();
        Hand enemyHand = new Hand();

        System.out.println("Initial checkings....");
        System.out.println("CroupierDeck:  " + croupierDeck.getCards());
        System.out.println("CroupierDeck' size:  " + croupierDeck.getCards().size());
        System.out.println("Your Hand:  " + myHand.getHandCards());
        System.out.println("My Hand:  " + enemyHand.getHandCards());
        System.out.println("Croupier's Card nr 2:  " + croupierDeck.getCards().get(2));
        System.out.println("Card's suit:  " + croupierDeck.getCards().get(2).getCardSuit());
        System.out.println("Card's value:  " + croupierDeck.getCards().get(2).getCardValue());
        System.out.println("Card's int value:  " + croupierDeck.getCards().get(2).getCardIntValue());


        System.out.print("\n__________BLACKJACK__________\nHi. I'm the croupier of this game. Let's begin. I'm shuffling a deck and give you two cards: ");
        croupierDeck.shuffle();
        croupierDeck.giveCards(2, myHand, true);
        handDeckInfo(croupierDeck, myHand, "your");
        String playerAnswer = askPlayer("Do you want next card?");            //Zwraca Y lub N

        while (playerAnswer.equals("Y") || playerAnswer.equals("y")){                   //tura Gracza
            takeNextCard(croupierDeck, myHand);
            playerAnswer = askPlayer("Do you want next card?");
        }
        if (playerAnswer.equals("N") || playerAnswer.equals("n")){
            System.out.print("OK. Now is my turn. My first two cards are: ");           //tura Przeciwnika
            croupierDeck.giveCards(2, enemyHand, true);
            handDeckInfo(croupierDeck, enemyHand, "my");
            if (enemyHand.sumHandCardsValues() < overloadValue){
                while ( overloadValue - enemyHand.sumHandCardsValues() > enemyRiskLevel){
                    System.out.println("OK. I will take one more card...");
                    croupierDeck.giveCards(1, enemyHand, true);
                    handDeckInfo(croupierDeck, enemyHand, "my");
                }
                System.out.println("Ok, that's enough. Let's check");
            }
        }
    }



    private static void takeNextCard(Deck croupierDeck, Hand myHand) {
        System.out.print("Next card is: ");
        croupierDeck.giveCards(1, myHand, true);
        handDeckInfo(croupierDeck, myHand, "your");
    }


    private static String askPlayer(String question){
        Scanner inputKey;
        String playerDecision;
        do{
            System.out.println(question + " (Y/N) ");
            inputKey = new Scanner(System.in);
            playerDecision = inputKey.next();
        }while (!playerDecision.equals("Y") && !playerDecision.equals("y") && !playerDecision.equals("N") && !playerDecision.equals("n"));
        return playerDecision;
    }

    public static void handDeckInfo(Deck deck, Hand hand, String who) {
        System.out.println("Deck's size:  " + deck.getSize());
        hand.handInfo(who);
    }



}
