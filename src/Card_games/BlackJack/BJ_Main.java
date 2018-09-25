package Card_games.BlackJack;

import java.util.Scanner;

public class BJ_Main {
    private static boolean overload = false;
    private static boolean gameEnd = false;
    private final static int overloadValue = 31;
    private final static int enemyRiskLevel = 7;
    private final static int changeDeckValue = 10;

    private static Hand myHand = new Hand();
    private static Hand enemyHand = new Hand();
    private static String playerAnswer;

    private static Deck croupierDeck = new Deck();

    public static void main(String[] args) {


/*
        System.out.println("Initial checkings....");
        System.out.println("CroupierDeck:  " + croupierDeck.getCards());
        System.out.println("CroupierDeck' size:  " + croupierDeck.getCards().size());
        System.out.println("Your Hand:  " + myHand.getHandCards());
        System.out.println("My Hand:  " + enemyHand.getHandCards());
        System.out.println("Croupier's Card nr 2:  " + croupierDeck.getCards().get(2));
        System.out.println("Card's suit:  " + croupierDeck.getCards().get(2).getCardSuit());
        System.out.println("Card's value:  " + croupierDeck.getCards().get(2).getCardValue());
        System.out.println("Card's int value:  " + croupierDeck.getCards().get(2).getCardIntValue());
        System.out.println("Current result: " + myHand.getScore() + " : " + enemyHand.getScore());
*/

        croupierDeck.shuffle();
        playGame("\n__________BLACKJACK__________\nHi. I'm the croupier of this game. Let's begin. I'm shuffling a deck and give you two cards: ");

    }

    private static void playGame(String welcomeMsg) {
        System.out.print(welcomeMsg);
        croupierDeck.giveCards(2, myHand, true);
        handDeckInfo(myHand, "your");
        playerAnswer = askPlayer("Do you want next card?");            //Zwraca Y lub N

        while ((playerAnswer.equals("Y") || playerAnswer.equals("y")) && (!gameEnd)){                   //tura Gracza
            takeNextCard(myHand);
            if (!overload) playerAnswer = askPlayer("Do you want next card?");
            else{
                System.out.println("You overloaded!");
                enemyHand.setWin(true);
                gameEnd = true;
                summary();
            }
        }
        if (playerAnswer.equals("N") || playerAnswer.equals("n")){
            System.out.print("OK. Now is my turn. My first two cards are: ");           //tura Przeciwnika
            croupierDeck.giveCards(2, enemyHand, true);
            handDeckInfo(enemyHand, "my");
            enemyTactic(enemyHand);
            summary();
        }
    }

    private static void enemyTactic(Hand enemyHand) {
        if (overloadValue - enemyHand.sumHandCardsValues() > enemyRiskLevel){       //Jeżeli komputer akceptuje ryzyko i chce kartę...
            System.out.print("OK. I will take one more card: ");
            croupierDeck.giveCards(1, enemyHand, true);                   //...to bierze kolejną kartę
            handDeckInfo(enemyHand, "my");

            if (enemyHand.sumHandCardsValues() >= overloadValue){                    //Jeśli jest "fura"...
                System.out.println("Oh, I overloaded...  :(");                   //...to przegrywa
                myHand.setWin(true);
            }
            else{                                                                    //jeśli nie...
                enemyTactic(enemyHand);                                  //...to decyduje o kolejnej karcie
            }
        }
        else{                                                                       //jeśli nie akceptuje ryzyka...
            System.out.println("Ok, that's enough. Let's check");                   // ...to sprawdza
            checkResult();
        }
    }

    private static void takeNextCard(Hand myHand) {
        System.out.print("Next card is: ");
        croupierDeck.giveCards(1, myHand, true);
        handDeckInfo(myHand, "your");
        if (myHand.sumHandCardsValues() >= overloadValue) overload = true;
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

    private static void handDeckInfo(Hand hand, String who) {
        hand.handInfo(who);
    }

    private static void checkResult(){
        System.out.println("Your sum is: " + myHand.sumHandCardsValues() + ", my sum is: " + enemyHand.sumHandCardsValues());
        if (myHand.sumHandCardsValues() > enemyHand.sumHandCardsValues()){
            myHand.setWin(true);
        }
        else {
           enemyHand.setWin(true);
        }
    }

    private static void summary(){
        if (myHand.getWin()){
            System.out.println("YOU WIN!");
            myHand.scoreUp();
        }
        if (enemyHand.getWin()){
            System.out.println("YOU LOSE!");
            enemyHand.scoreUp();
        }
        System.out.println("Current result: " + myHand.getScore() + " : " + enemyHand.getScore());
        playerAnswer = askPlayer("Do you want to play next?");
        if ((playerAnswer.equals("Y")) || (playerAnswer.equals("y"))){
            gameEnd = false;
            overload = false;
            myHand.throwCards();
            enemyHand.throwCards();
            System.out.println("\nDeck's size:  " + croupierDeck.getSize());

            if (croupierDeck.getSize() < changeDeckValue){
                System.out.println("Only few cards left in deck. I'm taking a new deck.");
                croupierDeck = new Deck();
                System.out.println("New deck's size:  " + croupierDeck.getSize() +"\n");
            }
            playGame("OK. Next round. I give you two cards: ");
        }
        else{
            System.out.println("---GAME OVER---");
        }
    }
}
