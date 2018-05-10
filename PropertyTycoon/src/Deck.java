/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

import java.io.*;
import java.util.*;


public class Deck {
    private Queue<Card> cardDeck;

    public Deck(File file){
        cardDeck = new LinkedList(); // instantiate new deck object
        buildDeck(file);
    }

    void buildDeck(File file) {
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
        //return cards;
    }

    public void shuffle() {
        System.out.println();
        List<Card> list = new ArrayList(cardDeck);
        Collections.shuffle(list); // Shuffle cards
        cardDeck = new LinkedList<>(list);
    }

    public Card drawCard() {
        return cardDeck.remove();
    }

    public void addCard(Card card) {
        cardDeck.add(card);
    }

    public int getDeckSize() {
        return cardDeck.size();
    }

    Card peekAtNextCard() {
        return cardDeck.peek();
    }

}
