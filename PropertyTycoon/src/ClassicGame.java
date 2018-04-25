import javax.swing.*;

/**
 *
 * @author Kieran
 */
public class ClassicGame implements PropertyTycoon {

    ClassicGame(int humanPlayers, int CPUPlayers) {
        System.out.println("ClassicGame made");
    }
    int noOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Does this work?"));




    public void init(){
        Board playBoard = new Board();

    }

    @Override
    public Board initBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player[] initPlayers(int noOfPlayers) {
        Player[] players = new Player[noOfPlayers];
        return players;
    }

    @Override
    public CPU[] initCPU() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Banker initBanker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dice initDice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PotLuck[] initPotLuck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpportunityKnocks[] initOpertunityKnocks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {

    }

    @Override
    public void movePlayer() {

    }

}
