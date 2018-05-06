/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class Deck {
    private Queue<Card> cardDeck;

    public Deck(File file){
        BufferedReader br = null;
        cardDeck = new LinkedList(); // instantiate new deck object
        ArrayList<Card> cards = new ArrayList<>();
        try{
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
        } catch (IOException e) {
            e.printStackTrace();
            //throw new Error("Deck location invalid");
        }

        Collections.shuffle(cards); // Shuffle cards
        for (Card card : cards) {
            addCard(card);
        }

    }

    public final Card drawCard() {
        return cardDeck.remove();
    }

    public final void addCard(Card card) {
        cardDeck.add(card);
    }
}
