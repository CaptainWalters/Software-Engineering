/**
 * The Class creates a Deck object that contains Card objects and acts as an interface for random Card selection
 * @author 132206, 134730, 146674
 *
 */

import java.io.*;
import java.util.*;

public class Deck {
    private Queue<Card> cardDeck;

    /**
     * Deck class constructor that instantiates new Deck object and fills using buildDeck()
     * @param file OpportunityKnocks.csv or PotLuck.csv
     */
    public Deck(File file){
        cardDeck = new LinkedList(); // instantiate new deck object
        buildDeck(file);
    }

    /**
     * Builds the Deck dynamically from a data CSV file
     * @param file OpportunityKnocks.csv or PotLuck.csv
     */
    public void buildDeck(File file) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                String description = attributes[0];
                String action = attributes[1];
                int value = Integer.parseInt(attributes[2]);
                Card card = new Card(description,action,value);
                addCard(card);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shuffle the Deck to reorganise order of Card objects in the Deck
     */
    public void shuffle() {
        System.out.println();
        List<Card> list = new ArrayList(cardDeck);
        Collections.shuffle(list); // Shuffle cards
        cardDeck = new LinkedList<>(list);
    }

    /**
     * Returns a random Card from the top of the Deck
     * @return Card random Card object from Deck
     */
    public Card drawCard() {
        return cardDeck.remove();
    }

    /**
     * Adds a Card object to the bottom of the Deck
     * @param card Card object
     */
    public void addCard(Card card) {
        cardDeck.add(card);
    }

    /**
     * Get the number of Cards within the Deck
     * @return int Number of Card objects in Deck
     */
    public int getDeckSize() {
        return cardDeck.size();
    }

    /**
     * View the top Card of the Deck without removing it from the Deck
     * @return Card Next Card from that will be drawn from the Deck
     */
    public Card peekAtNextCard() {
        return cardDeck.peek();
    }

}
