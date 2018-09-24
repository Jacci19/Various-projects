package BlackJack_enum;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    private int score = 0;
    private ArrayList<Card> handCards = new ArrayList<>();
    private boolean win = false;

    public ArrayList<Card> getHandCards() {
        return this.handCards;
    }

    public int sumHandCardsValues(){
        int sum = 0;
        for (final Card card : this.handCards) {
            sum += card.getCardIntValue();
        }
        return sum;
    }

   public void handInfo(final String who) {
//        System.out.println("--" + who.toUpperCase().charAt(0) + who.substring(1) + " hand: " + this.getHandCards());       //aby who zaczynało się wielką literą a w następnej linii małą
       System.out.println(String.format("--%s%s hand: %s", who.toUpperCase().charAt(0), who.substring(1), this.getHandCards()));
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
