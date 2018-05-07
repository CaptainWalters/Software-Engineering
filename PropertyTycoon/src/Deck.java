/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class Deck {
    private Queue<Card> cardDeck;

    public Deck(File file){
        cardDeck = new LinkedList(); // instantiate new deck object
        ArrayList<Card> cards = buildDeck(file);
        cards = shuffle(cards); // Shuffle cards
        for (Card card : cards) {
            addCard(card);
        }
    }

    ArrayList<Card> buildDeck(File file) {
        ArrayList<Card> cards = new ArrayList<>();
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
                cards.add(card);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public ArrayList<Card> shuffle(ArrayList<Card> cards) {
        Collections.shuffle(cards); // Shuffle cards
        return cards;
    }

    public Card drawCard() {
        return cardDeck.remove();
    }

    public void addCard(Card card) {
        cardDeck.add(card);
    }
}
