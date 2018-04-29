/**
 *
 * @author Kieran(132206)
 *
 */

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *The ClassicGame class runs an instance of the classic game of property tycoon. It is responible for initializing
 * the board, players, dice and cards.
 *
 */
public class ClassicGame{

    int noOfPlayers = 0;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Player player5;
    Player player6;
    ArrayList<Player> players = new ArrayList<>();
    Dice dice1;
    Dice dice2;
    Board board;

    int freeParking = 0;

    PotLuck potLuck;
    OpportunityKnocks opportunityKnocks;

    Boolean gameFinished = false;

    int currentTurn = 0;
    int roundNumber = 0;

    public ClassicGame(int noOfPlayers) throws IOException {
        this.noOfPlayers = noOfPlayers;
        init();
        takeTurn();
        takeTurn();
    }

    /**
     * This method is used to set up the board, players, dice and cards.
     * @throws IOException
     */
    public void init() throws IOException {

        board = new Board();
        player1 = new Player(1, "Thea", Token.Smartphone, false);
        player2 = new Player(2, "Oliver", Token.Cat, false);
        player3 = new Player(3,"Kieran", Token.Hatstand, false);
        player4 = new Player(4, "Loza", Token.Spoon, false);
        player5 = new Player(5, "Vlad", Token.Boot, false);
        player6 = new Player(6, "Thomas", Token.Goblet, false);


        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);

        dice1 = new Dice();
        dice2 = new Dice();

        potLuck = new PotLuck();
        opportunityKnocks = new OpportunityKnocks();

        System.out.println("Game has been initialized");
    }

    public int[] rollDice(){
        int[] diceRoll = {dice1.rollDice(),dice2.rollDice()};
        return diceRoll;
    }

    public void takeTurn() {
        // TODO: Check whether space is owned after each roll
        // offer to buy/ trade
        // pay rent
        // check to see if player passed go
        //option for player to buy house etc.
        // need to check if space is "Special"
        while(!gameFinished) {
            Player currPlayer = players.get(currentTurn);
            String playerName = currPlayer.getPlayerName();
            BoardLocation currLoc = board.board[currPlayer.getPosition()];

            //1st roll
            rollDiceDialog(playerName + ", it is your turn. Please roll the dice.");
            int diceRoll[] = rollDice();
            diceRollDialog(diceRoll);
            currPlayer.movePosition(diceRoll[0] + diceRoll[1]);

            //Check location.
            offerToBuy(currPlayer);


            //check space and do operations

            //Check to see if player has rolled doubles.
            if (diceRoll[0] == diceRoll[1]) {
                rollDiceDialog("You rolled doubles. Please roll again");
                diceRoll = rollDice();
                diceRollDialog(diceRoll);
                currPlayer.movePosition(diceRoll[0] + diceRoll[1]);

                //Check space and do operations
                offerToBuy(currPlayer);

                //Check to see if player rolled doubles again
                if (diceRoll[0] == diceRoll[1]) {
                    rollDiceDialog("You rolled doubles again. Roll again. If you get doubles you will go to jail.");
                    diceRoll = rollDice();
                    diceRollDialog(diceRoll);
                    if (diceRoll[0] == diceRoll[1]) {
                        jailDialog("You rolled three doubles in a row, your going to jail!");
                        //SHOULD BE SENT TO JAIL NEED TO IMPLEMENT
                        currPlayer.movePosition(diceRoll[0]+diceRoll[1]);
                        //currPlayer.moveToPosition(99);
                    } else {
                        currPlayer.movePosition(diceRoll[0] + diceRoll[1]);
                        offerToBuy(currPlayer);
                        //Check space
                        nextTurn(noOfPlayers);
                    }
                } else {
                    nextTurn(noOfPlayers);
                }
            } else {
                nextTurn(noOfPlayers);
            }
            //updateRoundNumber();
            //isGameFinished();
        }
    }

    private void diceRollDialog(int[] diceRoll) {
        Object[] okoptions = {"OK"};
        int n = JOptionPane.showOptionDialog(null,
                ("You rolled " + diceRoll[0] + " & " + diceRoll[1]),"Dice Roll",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                okoptions,
                okoptions[0]);
    }

    private void rollDiceDialog(String message) {
        Object[] rdoptions = {"ROLL DICE"};
        int n = JOptionPane.showOptionDialog(null,
                message,"Roll Dice",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                rdoptions,
                rdoptions[0]);
    }

    private void jailDialog(String message) {
        Object[] jailoptions = {"DAMMIT, FINE!"};
        int n = JOptionPane.showOptionDialog(null,
                message,"Your going to jail!",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                jailoptions,
                jailoptions[0]);
    }

    private void offerToBuy(Player player) {
        if (player.passedGo) {
            BoardLocation currLoc = board.board[player.getPosition()];
            if ((currLoc.canBuy) && (!currLoc.isOwned)) {
                Object[] buyoptions = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null,
                        ("You have landed on " + currLoc.getName() + ", would you like to buy it for: " + currLoc.getPrice() + "?").toString(),
                        "Property Purchase",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        buyoptions,
                        buyoptions[0]);

                if (n == 0) {
                    currLoc.setOwner(player);
                    player.pay(currLoc.getPrice());
                    System.out.println("Player " + player.getPlayerName() + " now owns " + currLoc.getName());
                    System.out.println("Player " + player.getPlayerName() + " now has " + player.getMoney() + " coins");
                } else if (n == 1) {
                    //trade(currLoc);
                }
            } else if(!currLoc.canBuy){
                //DO ACTION
            }
        }
    }

    private void isGameFinished() {
        if(players.size()<2){
            gameFinished = true;
        }
    }

    private void updateRoundNumber() {
        roundNumber += 1;

        if(roundNumber == 1000){
            gameFinished = true;
        }
    }

    public void nextTurn(int noOfPlayers){
        currentTurn +=1;

        if(currentTurn>noOfPlayers-1){
            currentTurn = currentTurn % noOfPlayers;
        }
    }


    }
