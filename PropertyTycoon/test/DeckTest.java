/**
 *
 * @author Oliver(134730)
 *
 */

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    File luck = new File(getClass().getResource("PotLuck.csv").getPath());
    File knocks = new File(getClass().getResource("OpportunityKnocks.csv").getPath());

    @Test
    void testFullFileRead() {
        Deck test = new Deck(luck);
        Card held = null;
        assertEquals(test.getDeckSize(),16);
        assertNotNull(test.peekAtNextCard().getDescription());
        for(int i=0;i<16;i++) {
            held = test.drawCard();
        }
        assertNotNull(held.getDescription());
    }

    @Test
    void testBuildDeck() {
        Deck dL = new Deck(luck);
        assertEquals(16, dL.getDeckSize());
        while(dL.getDeckSize() > 0){
            Card c = dL.drawCard();
            //System.out.println(c.getDescription());
        }
        assertEquals(0, dL.getDeckSize());

        Deck oK = new Deck(knocks);
        assertEquals(16, oK.getDeckSize());
        while(oK.getDeckSize() > 0){
            Card c = oK.drawCard();
            //System.out.println(c.getDescription());
        }
        assertEquals(0, oK.getDeckSize());

    }

    @Test
    void testShuffle() {
        Deck dL = new Deck(luck);
        Card a = dL.peekAtNextCard();
        dL.shuffle();
        Card b = dL.peekAtNextCard();
//        System.out.println(a.getDescription());
//        System.out.println(b.getDescription());
        assertNotNull(a);
        assertNotEquals(a, b);
    }

    @Test
    void testDrawCard() {
        Deck dL = new Deck(luck);
        int size = dL.getDeckSize();
        Card a = dL.peekAtNextCard();
        assertEquals(a, dL.drawCard());
        assertEquals(size-1, dL.getDeckSize());
    }

    @Test
    void testAddCard() {
        Deck dL = new Deck(luck);
        Card a = dL.drawCard();
        while(dL.getDeckSize() > 0){
            dL.drawCard();
        }
        assertEquals(0, dL.getDeckSize());
        dL.addCard(a);
        assertEquals(a, dL.peekAtNextCard());
        assertEquals(1, dL.getDeckSize());
    }
    
}