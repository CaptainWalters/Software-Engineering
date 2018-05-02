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

    private int noOfPlayers;


    Player[] players;
    Dice dice1;
    Dice dice2;
    Board board;
    int freeParking;

    Deck potLuck;
    Deck opportunityKnocks;

    Boolean gameFinished = false;

    int currentTurn = 0;
    int roundNumber = 0;

    public ClassicGame(Player[] players) throws IOException {
        this.players = players;
        this.noOfPlayers = players.length;
        freeParking = 0;
        init();
        takeTurn();
    }

    /**
     * This method is used to set up the board, players, dice and cards.
     * @throws IOException
     */
    public void init() throws IOException {

        board = new Board();


        dice1 = new Dice();
        dice2 = new Dice();

        //potLuck = new Deck();
        //opportunityKnocks = new Deck();

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
            Player currPlayer = players[currentTurn];
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

        if(n == -1){
            System.exit(0);
        }
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

        if(n == -1){
            System.exit(0);
        }
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

        if(n == -1){
            System.exit(0);
        }
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

                if(n == -1){
                    System.exit(0);
                }

                if (n == 0) {
                    currLoc.setOwner(player);
                    player.payMoney(currLoc.getPrice());
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

    public void cardAction(Player currPlayer, Card card) throws Exception {
        String action = card.getAction();
        String value = card.getValue();
        int house=0;
        int hotel=0;

        switch(action) {
            case "get":
                currPlayer.addMoney(Integer.parseInt(value));
                break;
            case "pay":
                currPlayer.payMoney(Integer.parseInt(value));
                break;
            case "jump":
                currPlayer.moveToPosition(Integer.parseInt(value));
                break;
            case "select":
                //addMoney(Integer.parseInt(value));
                break;
            case "free":
                addFreeParking(Integer.parseInt(value));
                break;
            case "collect":
                int num = currPlayer.getPlayerNumber();
                int collection = 0;
                int val = Integer.parseInt(value);
                Player thisPlayer = null;
                for(int i=0;i<players.length;i++) {
                    thisPlayer = players[i];
                    if (thisPlayer.playerNo != num) {
                        thisPlayer.payMoney(val);
                        collection += val;
                    }
                }
                currPlayer.addMoney(collection);
                break;
            case "move":
                currPlayer.movePosition(Integer.getInteger(value));
                break;
            case "repair":
                String[] repair = value.split(" ");
                house = Integer.parseInt(repair[1]);
                hotel = Integer.parseInt(repair[2]);
                //TODO values * total number of buildings owned
                break;
            case "jail":
                currPlayer.moveToPosition(99);
                break;
            default:
                throw new Exception("NO ACTION!");
        }
    }

    private void isGameFinished() {
        if(players.length<2){
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

        if(currentTurn>noOfPlayers-1) {
            currentTurn = currentTurn % noOfPlayers;
        }
    }

    public int getFreeParking(){
        return freeParking;
    }

    public void addFreeParking(int i){
        freeParking += i;
    }


}
