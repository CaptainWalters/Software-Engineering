/**
 *
 * @author Oliver
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Deck {
    private Queue<Card> cardDeck;

    public Deck(ArrayList<Card> cards) {
        Collections.shuffle(cards)  ;
        for(int i=0;i<cards.size();i++) {
            cardDeck.add(cards.get(i));
        }
    }

    public Card getNextCard() {
        return cardDeck.remove();
    }

    public void returnCard(Card card) {
        cardDeck.add(card);
    }


}
