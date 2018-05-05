import java.util.ArrayList;

/**
 *
 * @author 146674
 *
 */
class PotLuck {
    private final Deck deck;
    
    // Get card from top of deck
    public Card drawCard(){
        return this.deck.getNextCard();
    }
    
    public PotLuck(){
        ArrayList<Card> cardsPotLuck = new ArrayList();
        
        cardsPotLuck.add( new Card("You inherit £100", "get", 200) );
        cardsPotLuck.add( new Card("You have won 2nd prize in a beauty contest, collect £20", "get", 20) );
        cardsPotLuck.add( new Card("Go back to Crapper Street", "jump", 1) );
        cardsPotLuck.add( new Card("Student loan refund. Collect £20", "get", 20) );
        cardsPotLuck.add( new Card("Bank error in your favour. Collect £200", "get", 200) );
        cardsPotLuck.add( new Card("Pay bill for text books of £100", "payBank", 100) );
        cardsPotLuck.add( new Card("Mega late night taxi bill pay £50", "payBank", 50) );
        cardsPotLuck.add( new Card("Advance to go", "jump", 0) );
        cardsPotLuck.add( new Card("From sale of Bitcoin you get £50", "get", 50) );
        cardsPotLuck.add( new Card("Pay a £10 fine or take opportunity knocks", "payFreeParking", 10) );
        cardsPotLuck.add( new Card("Pay insurance fee of £50", "payFreeParking", 50) );
        cardsPotLuck.add( new Card("Savings bond matures, collect £100", "get", 100) );
        cardsPotLuck.add( new Card("Go to jail. Do not pass GO, do not collect £200", "jump", 30) ); // Move the player on GoToJail location
        cardsPotLuck.add( new Card("Received interest on shares of £25", "get", 25) );
        cardsPotLuck.add( new Card("It's your birthday. Collect £10 from each player", "collect", 10) );
        cardsPotLuck.add( new Card("Get out of jail free", "multipass", 1) );
        
        this.deck = new Deck( cardsPotLuck );
    }
}
