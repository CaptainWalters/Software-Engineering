/**
 *
 * @author Kieran(132206)
 *
 */

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *The ClassicGame class runs an instance of the classic game of property tycoon. It is responsible for initialising
 * the board, players, dice and cards.
 *
 */
public class ClassicGame{

    private int noOfPlayers;
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
    int freeParking;

    PotLuck potLuck;
    OpportunityKnocks opportunityKnocks;

    Boolean gameFinished = false;

    int currentTurn = 0;
    int roundNumber = 0;

    public ClassicGame(int noOfPlayers) throws IOException {
        this.noOfPlayers = noOfPlayers;
        freeParking = 0;
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
        player3 = new Player(3, "Kieran", Token.Hatstand, false);
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
            
            // Check location if purchasable or pay rent otherwise
            offerToBuy(currPlayer);
            
            //@146674: See if location has any actions
            doAction(currPlayer, diceRoll);
            
            //@146674: Develop location with property (method automatically checks if player is able to do so)
            developLocation(currPlayer);

            //check space and do operations

            //Check to see if player has rolled doubles.
            if (diceRoll[0] == diceRoll[1]) {
                rollDiceDialog("You rolled doubles. Please roll again");
                diceRoll = rollDice();
                diceRollDialog(diceRoll);
                currPlayer.movePosition(diceRoll[0] + diceRoll[1]);


                //Check space and do operations
                offerToBuy(currPlayer);
                
                //@146674: See if location has any actions
                doAction(currPlayer, diceRoll);
            
                //@146674: Develop location with houses/hotel (method automatically checks if player is able to do so)
                developLocation(currPlayer);
                
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
                        
                        // See if location has any actions
                        doAction(currPlayer, diceRoll);

                        //@146674: See if location has any actions
                        developLocation(currPlayer);
                        
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
    
    //@146674
    private void developLocation(Player player){
        // Method to offer player if they would like to purchase houses/hotel when all locations on colour are owned
        if (player.passedGo) {
            BoardLocation currLoc = board.board[player.getPosition()];
            
            if(!currLoc.canBuy()) return; // Shouldn't be able to develop a property you cannot own!
            
            int set = 3;
            if( currLoc.getColour().equals("deep blue") || currLoc.getColour().equals("brown") ) set = 2;
            if( board.getNumberOfPropertiesOwnedByPlayerUsingColour(player, currLoc.getColour()) == set){ // All properties owned (CHECK NUMBER OF PROPS)
                System.out.println("Offering Player " + player.getPlayerName() + " chance to develop " + currLoc.getName());
                for( int numOfProps = currLoc.numberOfPropertiesBuilt(); numOfProps<6; numOfProps++ ){
                    // Ask if they would like to purchase a house or exit
                    if( true ) currLoc.buyHouse(player); else return;
                }
            }
        }
    }
    
    //@146674 Edited/modified below
    private void offerToBuy(Player player) {
        if (player.passedGo) {
            BoardLocation currLoc = board.board[player.getPosition()];
            if (currLoc.canBuy() && !currLoc.isOwned()) {
                Object[] buyoptions = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null,
                        ("You have landed on " + currLoc.getName() + ", would you like to buy it for: " + currLoc.getPrice() + "?"),
                        "Property Purchase",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        buyoptions,
                        buyoptions[0]);

                if (n == 0) {
                    currLoc.setOwner(player);
                    player.payMoney(currLoc.getPrice());
                    System.out.println("Player " + player.getPlayerName() + " now owns " + currLoc.getName());
                    System.out.println("Player " + player.getPlayerName() + " now has " + player.getMoney() + " coins");
                } else if (n == 1) {
                    //trade(currLoc);
                }
            } else if( currLoc.isOwned() && !currLoc.getOwner().equals( player ) ){
                // @146674: if square is owned and cannot be baught, force player to pay rent if not owned by themselves
                System.out.println("Player " + player.getPlayerName() + " paid " + currLoc.getOwner().getPlayerName() + " the amount of " + currLoc.getRentPrice() + " for landing on " + currLoc.getName());
                player.payMoney( currLoc.getRentPrice() ); // Take rent money from current player
                currLoc.getOwner().addMoney( currLoc.getRentPrice() ); // Add rent money to landlord
            }
        }
    }
    
    //@146674
    public boolean doAction(Player player, int[] dice){
        if (player.passedGo) {
            BoardLocation currLoc = board.board[player.getPosition()];
            switch( currLoc.getAction() ){
                // Free parking
                case "collectfines":
                    /* Uncomment this when cards have been implemented!!! Reason it's commented out is to demonstrate that Free Parking works
                    if( getFreeParkingAmount() == 0 ) return false; // Ignore if value @ free parking is zero*/
                    
                    System.out.println("Player " + player.getPlayerName() + " collected " + getFreeParkingAmount() + " from Free Parking");
                    player.addMoney( collectFreeParking() );
                    return true;
                
                // Utility companies
                case "utilities":
                    if(!currLoc.isOwned()) return false; // Ignore if utility location has not been purchased
                    
                    int value = 0;
                    // check if player owns 1 or 2 utils
                    int utils = board.getNumberOfPropertiesOwnedByPlayerUsingColour(player, "utilities");
                    // if 1, rent = 4x sum(dice)
                    if( utils == 1) value = ( (dice[0]+dice[1]) * 4 );
                    // if 2, rent = 10x sum(dice)
                    if( utils == 2) value = ( (dice[0]+dice[1]) * 10 );
                    System.out.println("[DEBUG] PLAYER: " + player.getPlayerName() + ", UTILITIES OWNED: " + utils + ",  DICE 0:" + dice[0] + ", DICE 1: " + dice[1] + ", VALUE: " + value);
                    System.out.println("[OVERRIDE] Player " + player.getPlayerName() + " has paid " + value + " to " + currLoc.getOwner().getPlayerName() +" for landing on " + currLoc.getName());
                    player.payMoney(value);
                    currLoc.getOwner().addMoney(value);
                    return true;
                    
                default: // Do nothing if no action
                    System.out.println("Action '" + currLoc.getAction() + "' is not implemented.");
                    return false;
            }
        }
        return false;
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
                addFreeParking(Integer.parseInt(value));
                break;
            case "jump":
                currPlayer.moveToPosition(Integer.parseInt(value));
                break;
            case "select":
                //addMoney(Integer.parseInt(locationValue));
                break;
            case "free":
                addFreeParking(Integer.parseInt(value));
                break;
            case "collect":
                int num = currPlayer.getPlayerNumber();
                int collection = 0;
                int val = Integer.parseInt(value);
                Player thisPlayer = null;
                for(int i=0;i<players.size();i++) {
                    thisPlayer = players.get(i);
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
        this.currentTurn +=1;

        if(this.currentTurn>noOfPlayers-1) {
            this.currentTurn = this.currentTurn % noOfPlayers;
        }
    }

    public int getFreeParkingAmount(){
        return this.freeParking;
    }
    
    //@146674
    public int collectFreeParking(){
        int fp = this.freeParking; // Put actual FreeParking value into temporary variable
        this.freeParking = 0; // Reset actual FreeParking value
        return fp; // Return FreeParking temporary variable value
    }

    public void addFreeParking(int i){
        freeParking += i;
    }
    
}
