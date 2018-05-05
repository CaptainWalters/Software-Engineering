/**
 *
 * @author Kieran(132206), 146674
 *
 */

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *The ClassicGame class runs an instance of the classic game of property tycoon. It is responsible for initialising
 * the board, players, dice and cards.
 *
 */
public class ClassicGame{

    Path luck = Paths.get("../res/PotLuck.csv");
    Path knocks = Paths.get("../res/OpportunityKnocks.csv");
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

        potLuck = new Deck(luck);
        opportunityKnocks = new Deck(knocks);


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
            //BoardLocation currLoc = board.board[currPlayer.getPosition()]; // Not Used

            //1st roll
            rollDiceDialog(playerName + ", it is your turn. Please roll the dice.");
            int diceRoll[] = rollDice();
            diceRollDialog(diceRoll);
            currPlayer.movePosition(diceRoll[0] + diceRoll[1]);

            //@146674: Execute board location action on player
            doAction(currPlayer, diceRoll);

            //Check to see if player has rolled doubles.
            if (diceRoll[0] == diceRoll[1]) {
                rollDiceDialog("You rolled doubles. Please roll again");
                diceRoll = rollDice();
                diceRollDialog(diceRoll);
                currPlayer.movePosition(diceRoll[0] + diceRoll[1]);

                //@146674: Execute board location action on player
                doAction(currPlayer, diceRoll);
            
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
                        
                        //@146674: Execute board location action on player
                        doAction(currPlayer, diceRoll);
                        
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

    private int cardDialog(Card card) {
        int n = JOptionPane.showOptionDialog(null,
                (card.getDescription()),
                "Pick",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes", "No"},
                "Yes");
        if(n == -1){
            System.exit(0);
        }
        return n;
    }
    
    //@146674
    // Develop Location popup dialog box
    private int developLocationDialog(String locationName, int developmentPrice) {
        return JOptionPane.showOptionDialog(null,
            ("Would you like to develop " + locationName + " for " + developmentPrice + "?"),
            "Develop Location",
            JOptionPane.PLAIN_MESSAGE,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{"Yes", "No"},
            "Yes");
    }
    
    //@146674
    private void developLocation(Player player){
        // Method to offer player if they would like to purchase houses/hotel when all locations on colour are owned
        if (player.passedGo) {
            BoardLocation currLoc = board.board[player.getPosition()];
            
            if(!currLoc.canBuy()) return; // Shouldn't be able to develop a property you cannot own!
            if(!currLoc.getAction().equals("")) return; // Ignore developing locations with actions (like Utils/Stations/FreeParking/etc.)
            
            int set = 3;// Set number of properties in colour
            if( currLoc.getColour().equals("deep blue") || currLoc.getColour().equals("brown") ) set = 2; // These colours only have two locations on board
            
            if( board.getNumberOfLocationsOwnedByPlayerUsingColour(player, currLoc.getColour()) == set){ // Check player owns all properties in that colour
                for( int numOfProps = currLoc.numberOfPropertiesBuilt(); numOfProps<5; numOfProps++ ){ // Loop through remaining undeveloped properties
                    // Ask if player would like to purchase a house (and loop) or exit
                    if( developLocationDialog(currLoc.getName(), currLoc.getHouseDevelopmentPrice()) == 0 ) // Dialog 'Yes' button pressed
                        currLoc.buyHouse(player);
                    else return; // Dialog 'No' button pressed (exit loop)
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
                        (player.getPlayerName() + ", you have landed on " + currLoc.getName() + ", would you like to buy it for: " + currLoc.getPrice() + "?"),
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
            } else if( currLoc.isOwned() && !currLoc.getOwner().equals( player ) && currLoc.getAction().equals("") ){
                // @146674: if square is owned and cannot be baught, force player to pay rent if not owned by themselves
                System.out.println("Player " + player.getPlayerName() + " paid " + currLoc.getOwner().getPlayerName() + " the amount of " + currLoc.getRentPrice() + " for landing on " + currLoc.getName());
                player.payMoney( currLoc.getRentPrice() ); // Take rent money from current player
                currLoc.getOwner().addMoney( currLoc.getRentPrice() ); // Add rent money to landlord
            }
        }
    }
    
    //@146674
    // Executes an action on player that landed on a location during their turn
    public boolean doAction(Player player, int[] dice){
        if (player.passedGo) {
            BoardLocation currLoc = board.board[player.getPosition()];
            int value = 0; // Arbitrary final cost holder for calculations performed below
            
            switch( currLoc.getAction() ){
                /* Locations that cannot be purchased by the Player */
                // Pass GO
                case "landGO":
                    System.out.println("Player " + player.getPlayerName() + " landed on GO!");
                    //player.addMoney( 200 ); // [DISABLED] Set to give player £200 bonus if they land on GO
                    return false; // false because no action was actually performed (above is disabled)
                
                // Free parking
                case "collectfines":
                    /* Uncomment this when cards have been implemented!!! Reason it's commented out is to demonstrate that Free Parking works
                    if( getFreeParkingAmount() == 0 ) return false; // Ignore if value/amount set at Free Parking is zero*/
                    
                    System.out.println("Player " + player.getPlayerName() + " collected " + getFreeParkingAmount() + " from Free Parking");
                    player.addMoney( collectFreeParking() );
                    return true;
                    
                // Income Tax
                case "taxIncome":
                    System.out.println("Player " + player.getPlayerName() + " landed on Income Tax! Pay £200.");
                    player.payMoney( 200 ); // Player pay penality of £200 to Free Parking
                    addFreeParking( 200 );
                    return true;
                
                // Super Tax
                case "taxSuper":
                    System.out.println("Player " + player.getPlayerName() + " landed on Super Tax! Pay £100.");
                    player.addMoney( 100 ); // Player pay penality of £100 to Free Parking
                    addFreeParking( 100 );
                    return true;
                    
                // Pot Luck card action
                case "doPLCard":
                    System.out.printf(player.getPlayerName() + ", Pot Luck! ");
                    return cardAction(player, potLuck.drawCard(), dice);
                
                // Opportunity Knocks card action
                case "doOKCard":
                    System.out.printf(player.getPlayerName() + ", Opportunity Knocks! ");
                    return cardAction(player, opportunityKnocks.drawCard(), dice);
                    
                // Go to Jail [FIX THIS!!!]
                case "doGoToJail":
                    //System.out.println("doGoToJail hook trigger");
                    return false; // Does nothing atm [FIX THIS!!!]
                
                /* Player purchasable board locations */
                // Utility company (DO NOT MOVE, MUST BE NEAR default CASE)
                case "utilities":
                    if(!currLoc.isOwned()) break; // Break if utility location has not been purchased to pass to default case
                    if(currLoc.getOwner().equals(player)) return false; // Ignore if landlord lands on own utility
                    
                    value = 0; // Reset value
                    // check if player owns 1 or 2 utils
                    int utils = board.getNumberOfLocationsOwnedByPlayerUsingColour(currLoc.getOwner(), "utilities");
                    // if 1, rent = 4x sum(dice)
                    if( utils == 1) value = ( (dice[0]+dice[1]) * 4 );
                    // if 2, rent = 10x sum(dice)
                    if( utils == 2) value = ( (dice[0]+dice[1]) * 10 );
                    //System.out.println("[DEBUG] PLAYER: " + player.getPlayerName() + ", UTILITIES OWNED: " + utils + ",  DICE 0:" + dice[0] + ", DICE 1: " + dice[1] + ", VALUE: " + value);
                    
                    System.out.println("Player " + player.getPlayerName() + " paid " + currLoc.getOwner().getPlayerName() + " the amount of " + value + " for landing on " + currLoc.getName());
                    player.payMoney(value);
                    currLoc.getOwner().addMoney(value);
                    return true;
                
                // Stations (DO NOT MOVE, MUST BE NEAR default CASE)
                case "station":
                    if(!currLoc.isOwned()) break; // Break if utility location has not been purchased to pass to default case
                    if(currLoc.getOwner().equals(player)) return false; // Ignore if landlord lands on own station
                    
                    value = 0; // Reset value
                    // check if player how many stations landlord owns
                    int stations = board.getNumberOfLocationsOwnedByPlayerUsingColour(currLoc.getOwner(), "station");

                    if( stations == 2) value = 200;
                    if( stations == 3) value = 300;
                    if( stations == 3) value = 1000;
                    
                    System.out.println("Player " + player.getPlayerName() + " paid " + currLoc.getOwner().getPlayerName() + " the amount of " + value + " for landing on " + currLoc.getName());
                    player.payMoney(value);
                    currLoc.getOwner().addMoney(value);
                    return true;
                    
                default:
                    // Non-actionable locations are executed here
                    offerToBuy(player);
                    developLocation(player);
                    return false;
            }
        }
        return false;
    }

    //public void cardAction(Player currPlayer, Card card) throws Exception {
    public Boolean cardAction(Player player, Card card, int[] dice) {
        String action = card.getAction();
        int value = card.getValue();
        System.out.println( card.getDescription() );

        switch(action) {
            case "get":
                player.addMoney(value);
                return true;
            
            case "pay":
                player.payMoney(value);
                return true;
            
            case "parking":
                player.payMoney(value);
                addFreeParking(value);
                return true;
            
            case "jump":
                player.moveToPosition(value);
                doAction( player, dice ); // Executes wherever player lands
                return true;
                
            case "move":
                player.movePosition(-3); // Can we move back like this???
                doAction( player, dice ); // Executes wherever player lands
                return true;
            
            case "select":
                //addMoney(Integer.parseInt(locationValue));
                return false;

            case "collect":
                int num = player.getPlayerNumber();
                int collection = 0;
                Player thisPlayer = null;
                for(int i=0;i<players.length;i++) {
                    thisPlayer = players[i];
                    if (thisPlayer.playerNo != num) {
                        thisPlayer.payMoney(value);
                        collection += value;
                    }
                }
                player.addMoney(collection);
                return true;
            
            case "repair":
                int houses = board.getNumberOfHousesDevelopedByPlayer(player);
                int hotels = board.getNumberOfHotelsDevelopedByPlayer(player);
                int repairCost = 0;
                //TODO values * total number of buildings owned (houses + hotels)
                if( value == 25) repairCost = (25 * houses) + (100 * hotels);
                if( value == 40) repairCost = (40 * houses) + (115 * hotels);
                System.out.println("Player " + player.getPlayerName() + "has paid a total of " + repairCost + " in repair costs.");
                player.payMoney(repairCost);
                return true;
            
            case "multipass": // Get out of jail free card (The 5th Element reference)
                return false;
                
            case "jail": // Currently just sending people to GoToJail location (using jump 30) [FIX!!!]
                player.moveToPosition(99);
                return true;
            
            default:
                System.out.println("NO ACTION!");
                //throw new Exception("NO ACTION!");
                break; // default will break/exit and return false
        }
        return false;
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
