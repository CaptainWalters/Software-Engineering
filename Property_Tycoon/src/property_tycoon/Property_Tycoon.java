package property_tycoon;

import java.io.IOException;

/**
 *
 * @author Kieran
 */
public interface Property_Tycoon {

    public Board initBoard();
    
    public Player[] initPlayers();
    
    public CPU[] initCPU();
    
    public Banker initBanker();
    
    public Dice initDice();
    
    public PotLuck[] initPotLuck();
    
    public OpportunityKnocks[] initOpertunityKnocks();
}
