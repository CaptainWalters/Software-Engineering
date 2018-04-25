import java.io.IOException;

/**
 *
 * @author Kieran
 */
public interface PropertyTycoon {

    public Board initBoard();

    public Player[] initPlayers(int noOfPlayers);

    public CPU[] initCPU();

    public Banker initBanker();

    public Dice initDice();

    public PotLuck[] initPotLuck();

    public OpportunityKnocks[] initOpertunityKnocks();
}
