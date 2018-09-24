package WarCardGame;

import java.util.Scanner;

public class Main_War {

    private static Hand myHand = new Hand();
    private static Hand enemyHand = new Hand();
    private static Hand myNewHand = new Hand();
    private static Hand enemyNewHand = new Hand();
    private static Deck croupierDeck = new Deck();

    private static Card myCard = null, enemyCard = null;
    private static Card myfirstWarCard = null, enemyfirstWarCard = null;
    private static Boolean isWinner = false;
    private static int battleNumber = 0;

    public static void main(String[] args) {



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

        croupierDeck.shuffle();

        croupierDeck.giveCards(20, myHand, false);
        croupierDeck.giveCards(20, enemyHand, false);

        System.out.println("\n_______War Card Game________");


        while (!isWinner){

            myHand.handInfo("Jacek");
            enemyHand.handInfo("Enemy");

            battleNumber++;
            placeCard(myHand, myNewHand, "Jacek");
            placeCard(enemyHand, enemyNewHand, "Enemy");

            if (!isWinner) compareCards(false);

        }
    }

    private static void placeCard(Hand currentHand, Hand newHand, String owner){


        if (currentHand.getHandCards().size() > 0){                                         //Jesli w obecnej ręce są karty..
            if (currentHand == myHand) myCard = currentHand.deckOutCard();                  //to wyłóż kartę z ręki
            if (currentHand == enemyHand) enemyCard = currentHand.deckOutCard();
        }
        else if (newHand.getHandCards().size() > 0){                                        //jeśli nie to weź talię zapasową
            currentHand.swapHand(newHand);
            System.out.println(owner + " is changing deck");
        }
        else{                                                                               //jeśli nie ma kart w talii zapasowej to przegrana
            newHand.handInfo(owner + " new");
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

    private static void compareCards(Boolean drawMode){
        System.out.print("\nBattle " + battleNumber + ": " + myCard + " vs " + enemyCard);
        switch (myCard.compare(enemyCard)) {
            case "bigger":
                myNewHand.getHandCards().add(myCard);
                myNewHand.getHandCards().add(enemyCard);
                if (drawMode){
                    myNewHand.getHandCards().add(myfirstWarCard);
                    myNewHand.getHandCards().add(enemyfirstWarCard);
                }


                System.out.println("\t\t_________________________________Jacek's card wins");
                break;
            case "smaller":
                enemyNewHand.getHandCards().add(myCard);
                enemyNewHand.getHandCards().add(enemyCard);
                if (drawMode){
                    enemyNewHand.getHandCards().add(myfirstWarCard);
                    enemyNewHand.getHandCards().add(enemyfirstWarCard);
                }

                System.out.println("\t\t_________________________________Enemy's card wins");
                break;
            case "equal":
                System.out.println("\t\t_________________________________D R A W____________");
                drawProcedure();
        }
    }

    private static void drawProcedure(){
        placeCard(myHand, myNewHand, "Jacek");
        myfirstWarCard = myCard;
        placeCard(enemyHand, enemyNewHand, "Enemy");
        enemyfirstWarCard = enemyCard;

        placeCard(myHand, myNewHand, "Jacek");
        placeCard(enemyHand, enemyNewHand, "Enemy");
        System.out.println("Draw mode.\nJacek's cards: " +  myfirstWarCard + ", " + myCard + "\nEnemy's cards: " +  enemyfirstWarCard + ", " + enemyCard);

        if (!isWinner) compareCards(true);
    }

}

