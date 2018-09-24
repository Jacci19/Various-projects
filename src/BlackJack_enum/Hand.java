package BlackJack_enum;

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
        System.out.println("--Sum of " + who + " hand's cards is: " + this.sumHandCardsValues());
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
}
