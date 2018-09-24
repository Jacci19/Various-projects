package WarCardGame;

import java.util.Scanner;

public class Main_War {

    private static Hand myHand = new Hand();
    private static Hand enemyHand = new Hand();
    private static Deck croupierDeck = new Deck();

    private static Card myCard = null, enemyCard = null;
    private static Card myfirstWarCard = null, enemyfirstWarCard = null, myZeroWarCard = null, enemyZeroWarCard = null;
    private static Boolean isWinner = false;
    private static int battleNumber = 0;

    private static String player_1_name = "Jacek";
    private static String player_2_name = "Kamil";

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

        croupierDeck.giveCards(26, myHand, false);
        croupierDeck.giveCards(26, enemyHand, false);

        System.out.println("\n___________________________________- WAR Card Game -_________________________________________");
        System.out.println("Play: " + player_1_name + " vs " + player_2_name);


        while (!isWinner && battleNumber < 10000) {
            showBothPlayersHandCards();
            battleNumber++;
            if (battleNumber % 500 == 0) {                           // co tyle rund tasujemy karty w ręce
                myHand.shuffle();
                enemyHand.shuffle();
                System.out.println("Cards have been shuffled");
            }

            placeCard(myHand, player_1_name);
            placeCard(enemyHand, player_2_name);

            if (!isWinner) compareCards(false);
        }
        if (battleNumber >= 50000) System.out.println("Limit of rounds is reached. The game ends with a draw");
    }

    private static void placeCard(Hand currentHand, String owner) {


        if (currentHand.getHandCards().size() > 0) {                                         //Jesli w obecnej ręce są karty..
            if (currentHand == myHand) myCard = currentHand.deckOutCard();                  //to wyłóż kartę z ręki
            if (currentHand == enemyHand) enemyCard = currentHand.deckOutCard();
        } else {                                                                               //jeśli nie ma kart w talii zapasowej to przegrana
            System.out.println(owner.toUpperCase() + " NO LONGER HAS CARDS.");
            System.out.println(owner.toUpperCase() + " LOSES!");
            isWinner = true;
        }
    }


    private static String askPlayer(String question) {
        Scanner inputKey;
        String playerDecision;
        do {
            System.out.println(question + " (Y/N) ");
            inputKey = new Scanner(System.in);
            playerDecision = inputKey.next();
        }
        while (!playerDecision.equals("Y") && !playerDecision.equals("y") && !playerDecision.equals("N") && !playerDecision.equals("n"));
        return playerDecision;
    }

    private static void compareCards(Boolean drawMode) {

        if (!drawMode){
            System.out.println("\n_____________________________________________________________________________________________");
            System.out.println("Battle " + battleNumber + ": " + myCard + " vs " + enemyCard);
        }
        else System.out.println("\nWar Battle: " + myCard + " vs " + enemyCard);

        switch (myCard.compare(enemyCard)) {
            case "bigger":
                myHand.addCardToHandEnd(myCard);
                myHand.addCardToHandEnd(enemyCard);
                if (drawMode) {
                    myHand.addCardToHandEnd(myZeroWarCard);
                    myHand.addCardToHandEnd(enemyZeroWarCard);
                    myHand.addCardToHandEnd(myfirstWarCard);
                    myHand.addCardToHandEnd(enemyfirstWarCard);
                }
                if (!drawMode) System.out.println(" - " + player_1_name + " win cards: (" + myCard +  ", " + enemyCard + ")");
                else System.out.println(" - " + player_1_name + " win cards: (" + myCard +  ", " + enemyCard +  ", " + myZeroWarCard +  ", " + enemyZeroWarCard +  ", " + myfirstWarCard +  ", " + enemyfirstWarCard + ")");
                break;

            case "smaller":
                enemyHand.addCardToHandEnd(enemyCard);
                enemyHand.addCardToHandEnd(myCard);
                if (drawMode) {
                    enemyHand.addCardToHandEnd(enemyZeroWarCard);
                    enemyHand.addCardToHandEnd(myZeroWarCard);
                    enemyHand.addCardToHandEnd(enemyfirstWarCard);
                    enemyHand.addCardToHandEnd(myfirstWarCard);
                }
                if (!drawMode) System.out.println(" - " + player_2_name + " win cards: (" + enemyCard + ", " + myCard + ")");
                else System.out.println(" - " + player_2_name + " win cards: (" + enemyCard +  ", " + myCard +  ", " + enemyZeroWarCard +  ", " + myZeroWarCard +  ", " + enemyfirstWarCard +  ", " + myfirstWarCard + ")");
                break;

            case "equal":
                System.out.println(" - DRAW mode (war): ");
                drawProcedure();
                break;
        }
    }

    private static void drawProcedure() {
        myZeroWarCard = myCard;
        enemyZeroWarCard = enemyCard;
        placeCard(myHand, player_1_name);
        myfirstWarCard = myCard;
        placeCard(enemyHand, player_2_name);
        enemyfirstWarCard = enemyCard;

        if (!isWinner) {
            placeCard(myHand, player_1_name);
            placeCard(enemyHand, player_2_name);
            System.out.println(player_1_name + "'s war cards: " + myfirstWarCard + ", " + myCard + "\n" + player_2_name + "'s war cards: " + enemyfirstWarCard + ", " + enemyCard);
            showBothPlayersHandCards();
        }
        if (!isWinner) compareCards(true);
    }

    private static void showBothPlayersHandCards(){
        System.out.println();
        myHand.handInfo(player_1_name);
        enemyHand.handInfo(player_2_name);
    }
}

