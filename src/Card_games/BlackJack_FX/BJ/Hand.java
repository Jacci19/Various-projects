package Card_games.BlackJack_FX.BJ;

import Card_games.BlackJack_FX.BJ.Deck;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    private ArrayList<Card> handCards;
    private int score = 0;
    private String tableSide;

    public Hand(String side) {
        this.handCards = new ArrayList<>();
        this.tableSide = side;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public int sumHandCardsValues(){
        int sum = 0;
        for (Card card : handCards) {
            sum += card.getCardIntValue();
        }
        return sum;
    }

    public int getScore() {
        return score;
    }

    public void scoreUp(){
        this.score++;
    }

    public String enemyTactic(int overloadValue, Label croupLabel, Deck croupDeck, Pane pane) {
        int enemyRiskLevel = 7;
        if (overloadValue - this.sumHandCardsValues() > enemyRiskLevel) {                                               //Jeżeli komputer akceptuje ryzyko i chce kartę...
            croupLabel.setText("OK. My sum was " + this.sumHandCardsValues() +". I took one more card: ");
            croupDeck.giveCards(pane, 1, this, "up");                                                                   //...to bierze kolejną kartę
            if (this.sumHandCardsValues() >= overloadValue) {                                                           //Jeśli jest "fura"...
                croupLabel.setText("Oh, my sum is " + this.sumHandCardsValues() + ". I overloaded...  :(");                   //...to przegrywa
                return "playerWin";
            } else {                                                                                                    //jeśli nie...
                return "inGame";
            }
        } else {                                                                                                        //jeśli nie akceptuje ryzyka...
            croupLabel.setText("Ok, my sum is " + this.sumHandCardsValues() + ". That's enough. Let's check");          // ...to sprawdza
            return "checkResult";
        }

    }

/*
    public void handInfo(String who) {
        System.out.println("--" + who.toUpperCase().charAt(0) + who.substring(1) + "'s hand: (" + getHandCards().size() + ") "  + this.getHandCards());       //aby who zaczynało się wielką literą a w następnej linii małą
   }

    public Card deckOutCard(){
        return  handCards.remove(0);                 //zwraca pierwszą kartę z listy i jednocześnie usuwa ją z tej listy
    }

    public void addCardToHandEnd(Card card){
        this.handCards.add(handCards.size(), card);
    }


    public void shuffle(){
        Collections.shuffle(handCards);                     //potasowanie kart
    }
*/
}
