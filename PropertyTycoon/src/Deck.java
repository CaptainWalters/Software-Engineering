/**
 *
 * @author Oliver, 146674
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    private Queue<Card> cardDeck;

    public Deck(ArrayList<Card> cards) {
        this.cardDeck = new LinkedList(); // instantiate new deck object
        
        Collections.shuffle(cards); // Shuffle cards
        cards.forEach((card) -> {
            addCard( card ); // Add cards to deck object
        });
    }

    public final Card getNextCard() {
        return this.cardDeck.remove();
    }

    public final void addCard(Card card) {
        this.cardDeck.add(card);
    }
}
