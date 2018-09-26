package Card_games.BlackJack_FX;

import java.util.ArrayList;

public class Hand {

    private int score = 0;
    private ArrayList<Card> handCards = new ArrayList<>();
    private boolean win = false;

    ArrayList<Card> getHandCards() {
        return this.handCards;
    }

    int sumHandCardsValues(){
        int sum = 0;
        for (final Card card : this.handCards) {
            sum += card.getCardIntValue();
        }
        return sum;
    }

   void handInfo(final String who) {
//        System.out.println("--" + who.toUpperCase().charAt(0) + who.substring(1) + " hand: " + this.getHandCards());       //aby who zaczynało się wielką literą a w następnej linii małą
       System.out.println(String.format("--%s%s hand: %s", who.toUpperCase().charAt(0), who.substring(1), this.getHandCards()));
       System.out.println("--Sum of " + who + " hand's cards is: " + this.sumHandCardsValues());
   }

    boolean getWin() {
        return win;
    }
    void setWin(boolean win) {
        this.win = win;
    }

    public void setScore(int score) {
        this.score = score;
    }

    int getScore() {
        return score;
    }

    void scoreUp(){
        this.score++;
    }

    void throwCards(){
        handCards.clear();
        setWin(false);
    }
}
