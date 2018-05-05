import java.util.ArrayList;

/**
 *
 * @author
 */
class OpportunityKnocks {
    private final Deck deck;
    
    // Get card from top of deck
    public Card drawCard(){
        return this.deck.getNextCard();
    }
    
    public OpportunityKnocks(){
        ArrayList<Card> cardsOpportunityKnocks = new ArrayList();
        
        cardsOpportunityKnocks.add( new Card("Bank pays you divided of £50", "get", 50) );
        cardsOpportunityKnocks.add( new Card("You have won a lip sync battle. Collect £100", "get", 100) );
        cardsOpportunityKnocks.add( new Card("Advance to Turing Heights", "jump", 39) );
        cardsOpportunityKnocks.add( new Card("Advance to Han Xin Gardens. If you pass GO, collect £200", "jump", 24) );
        cardsOpportunityKnocks.add( new Card("Fined £15 for speeding", "payFreeParking", 15) );
        cardsOpportunityKnocks.add( new Card("Pay university fees of £150", "payBank", 150) );
        cardsOpportunityKnocks.add( new Card("Take a trip to Hove station. If you pass GO collect £200", "jump", 15) );
        cardsOpportunityKnocks.add( new Card("Loan matures, collect £150", "get", 150) );
        cardsOpportunityKnocks.add( new Card("You are assessed for repairs, £40/house, £115/hotel", "repair", 40) );
        cardsOpportunityKnocks.add( new Card("Advance to GO", "jump", 0) );
        cardsOpportunityKnocks.add( new Card("You are assessed for repairs, £25/house, £100/hotel", "repair", 25) );
        cardsOpportunityKnocks.add( new Card("Go back 3 spaces", "goBack3", 3) );
        cardsOpportunityKnocks.add( new Card("Advance to Skywalker Drive. If you pass GO collect £200", "jump", 11) );
        cardsOpportunityKnocks.add( new Card("Go to jail. Do not pass GO, do not collect £200", "jump", 30) );
        cardsOpportunityKnocks.add( new Card("Drunk in charge of a skateboard. Fine £20", "payFreeParking", 20) );
        cardsOpportunityKnocks.add( new Card("Get out of jail free", "multipass", 1) );
        
        this.deck = new Deck( cardsOpportunityKnocks );
    }
}
