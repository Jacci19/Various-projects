package WarCardGame;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    private int score;
    private ArrayList<Card> handCards;
    private boolean win = false;


    public Hand() {
        handCards = new ArrayList<>();
        score = 0;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public int sumHandCardsValues(){
        int sum = 0;
        for (int i=0; i<handCards.size(); i++){
            sum += handCards.get(i).getCardIntValue();
        }
        return sum;
    }

   public void handInfo(String who) {
        System.out.println("--" + who.toUpperCase().charAt(0) + who.substring(1) + " hand: " + this.getHandCards());       //aby who zaczynało się wielką literą a w następnej linii małą
        //System.out.println("--Sum of " + who + " hand's cards is: " + this.sumHandCardsValues());
   }

    public Card deckOutCard(){
        return  handCards.remove(0);                 //zwraca pierwszą kartę z listy i jednocześnie usuwa ją z tej listy
    }

    public void giveCards (int ilosc, Hand player, boolean info){
        for (int i = 0; i < ilosc; i++){
            Card someCard =  handCards.get(0);
            handCards.remove(0);
            player.getHandCards().add(someCard);
            if (info){
                if (i < ilosc - 1) System.out.print(someCard + ", ");
                else System.out.println(someCard);
            }
        }
    }

    public boolean getWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void scoreUp(){
        this.score++;
    }

    public void throwCards(){
        handCards.clear();
        setWin(false);
    }

    public void swapHand(Hand newHand){
        Hand tempHand = new Hand();
        tempHand.handCards = this.handCards;
        this.handCards = newHand.handCards;
        newHand.handCards = tempHand.handCards;
    }

    public void addCardToHandEnd(Card card){
        this.handCards.add(handCards.size(), card);
    }

    public void shuffle(){
        Collections.shuffle(handCards);                     //potasowanie kart
    }

}
