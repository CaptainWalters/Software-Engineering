/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.Timer;

/**
 *The Game class runs an instance of the classic game of property tycoon. It is responsible for initialising
 * the board, players, dice and cards.
 *
 */
public class Game {

    File luck = new File(getClass().getResource("PotLuck.csv").getPath());
    File knocks = new File(getClass().getResource("OpportunityKnocks.csv").getPath());
    File boardCSV = new File(getClass().getResource("Board.csv").getPath());
    private int noOfPlayers;
    ArrayList<Player> players;
    Dice dice1;
    Dice dice2;
    Board board;
    int freeParking;
    Deck potLuck;
    Deck opportunityKnocks;
    Boolean gameFinished = false;
    Boolean tradingAllowed;
    Boolean allPlayersPassedGo = false;
    public HashMap<Player,Integer> jailTurnCounter = new HashMap();
    int timerInt;
    public Timer timer;
    int currentTurn = 0;
    int roundNumber = 0;

    public Game(ArrayList<Player> players, boolean trading, boolean abridged, int timer) throws IOException {
        timerInt = timer;
        this.players = players;
        this.noOfPlayers = players.size();
        tradingAllowed = trading;
        init();
        takeTurn();
    }

    /**
     * This method is used to set up the board, players, dice and cards.
     * @throws IOException
     */
    public void init() throws IOException {
        board = new Board(boardCSV);
        dice1 = new Dice();
        dice2 = new Dice();
        potLuck = new Deck(luck);
        potLuck.shuffle();
        opportunityKnocks = new Deck(knocks);
        opportunityKnocks.shuffle();
        freeParking = 0;
        if(timerInt>0) {
            this.timer = new Timer();
            timer.schedule(endGameEvaluation(), timerInt * 60 * 1000);
        }
    }

    //132206
    private TimerTask endGameEvaluation() {
        return new TimerTask() {
            @Override
            public void run() {
                endGame();
            }
        };

    }

    //132206
    private void endGame() {
        int[] playerValue = new int[players.size()];
        int maxValue = 0;
        int maxPlayerIndex = -1;
        boolean tiedWinner = false;

        for(int i = 0; i<players.size();i++) {
            int playerAssets = 0;
            for (int j = 0; j < 40; j++) {
                if (board.board[j].getOwner() == players.get(i)) {
                    playerAssets += board.board[j].getPrice();
                }
            }
            playerValue[i] = playerAssets+players.get(i).getMoney();
        }

        for(int e = 0; e < playerValue.length; e++){
            if(playerValue[e]>maxValue){
                maxValue = playerValue[e];
                maxPlayerIndex = e;
            } if (playerValue[e] == maxValue){
                tiedWinner = true;
            }
        }

        if(!tiedWinner){
            System.out.println("Timer finished, " + players.get(maxPlayerIndex).getPlayerName() + " won! Had an asset value of: " + maxValue);
        } else {
            System.out.println("There is a tie for winning place.");
        }

        System.exit(0);
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
        // need to check if spaceis "Special"

        while(!gameFinished) {
            Player currPlayer = players.get(currentTurn);
            String playerName = currPlayer.getPlayerName();
            //1st roll
            if(!currPlayer.inJail) {
                if(!currPlayer.isCPU){
                    rollDiceDialog(playerName + ", it is your turn. Please roll the dice.");
                }
                int diceRoll[] = rollDice();
                if(!currPlayer.isCPU) {
                    diceRollDialog(diceRoll);
                }
                currPlayer.movePosition(diceRoll[0] + diceRoll[1]);
                System.out.println(currPlayer.getPlayerName() + " you landed on " + board.board[currPlayer.getPosition()].getName());
                //@146674: Execute board location action on player
                doAction(currPlayer, diceRoll);
                //Check to see if player has rolled doubles.
                if (diceRoll[0] == diceRoll[1]) {
                    if(!currPlayer.isCPU) {
                        rollDiceDialog("You rolled doubles. Please roll again");
                    }
                    diceRoll = rollDice();
                    if(!currPlayer.isCPU) {
                        diceRollDialog(diceRoll);
                    }
                    currPlayer.movePosition(diceRoll[0] + diceRoll[1]);
                    System.out.println(currPlayer.getPlayerName() + " you landed on " + board.board[currPlayer.getPosition()].getName());
                    //@146674: Execute board location action on player
                    doAction(currPlayer, diceRoll);
                    //Check to see if player rolled doubles again
                    if (diceRoll[0] == diceRoll[1]) {
                        if(!currPlayer.isCPU) {
                            rollDiceDialog("You rolled doubles again. Roll again. If you get doubles you will go to jail.");
                        }
                        diceRoll = rollDice();
                        if(!currPlayer.isCPU) {
                            diceRollDialog(diceRoll);
                        }
                        if (diceRoll[0] == diceRoll[1]) {
                            if(!currPlayer.isCPU) {
                                jailDialog("You rolled three doubles in a row, your going to jail!");
                            }
                            if (!currPlayer.heldCards.empty()) {
                                Card card = currPlayer.heldCards.pop();
                                if (card.getValue() == 1) {
                                    potLuck.addCard(card);
                                } else if (card.getValue() == 2) {
                                    opportunityKnocks.addCard(card);
                                }
                                System.out.println(currPlayer.getPlayerName() + " used their get out of jail free card.");
                                currPlayer.inJail = false;
                                currPlayer.moveToPosition(10);
                                System.out.println(currPlayer.getPlayerName() + " you landed on " + board.board[currPlayer.getPosition()].getName());
                                nextTurn(noOfPlayers);
                            } else {
                                int n;
                                if(!currPlayer.isCPU) {
                                    n = payJailDialog(currPlayer.getPlayerName());
                                } else {
                                    Random rand = new Random();
                                    n = rand.nextInt((1 - 0) + 1) + 0;
                                }
                                if (n == 0) {
                                    payJail(currPlayer, 50);
                                } else if (n == 1) {
                                    jailTurnCounter.put(currPlayer, 0);
                                    currPlayer.moveToPosition(40);
                                    System.out.println(currPlayer.getPlayerName() + " you landed on " + board.board[currPlayer.getPosition()].getName());
                                    currPlayer.setInJail();
                                }
                            }
                        } else {
                            currPlayer.movePosition(diceRoll[0] + diceRoll[1]);
                            System.out.println(currPlayer.getPlayerName() + " you landed on " + board.board[currPlayer.getPosition()].getName());
                            //@146674: Execute board location action on player
                            doAction(currPlayer, diceRoll);
                            //Check space
                            if (tradingAllowed) {
                                if(!currPlayer.isCPU()) {
                                    tradeDialog(currPlayer);
                                }
                            }
                            nextTurn(noOfPlayers);
                        }
                    } else {
                        if (tradingAllowed) {
                            if(!currPlayer.isCPU()) {
                                tradeDialog(currPlayer);
                            }
                        }
                        nextTurn(noOfPlayers);
                    }
                } else {
                    if (tradingAllowed) {
                        if(!currPlayer.isCPU()) {
                            tradeDialog(currPlayer);
                        }
                    }
                    nextTurn(noOfPlayers);
                }
                    //CPU ACTIONS

            } else {
                jailCheck(currPlayer);
                nextTurn(noOfPlayers);
            }
            updateRoundNumber();
        }
    }

    private void tradeDialog(Player currPlayer) {
        if(allPlayersPassedGo){
            Object[] okoptions = {"END TURN","TRADE"};
            int n = JOptionPane.showOptionDialog(null,
                    (currPlayer.getPlayerName() + " would you like to trade or end your turn."),"Dice Roll",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    okoptions,
                    okoptions[0]);

            if(n == -1){
                System.exit(0);
            } else if (n==1){
                doTrade(currPlayer);
            }
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

    private int sellMortgageDialog(String message) {
        Object[] selloptions = {"Sell", "Mortgage"};
        int n = JOptionPane.showOptionDialog(null,
                message,"You need some money!",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                selloptions,
                selloptions[0]);

        if(n == -1){
            System.exit(0);
        }

        return n;
    }

    private int payJailDialog(String currPlayer) {
        Object[] jailoptions = {"PAY", "DONT PAY"};
        int n = JOptionPane.showOptionDialog(null,
                currPlayer + " you are in jail. Pay 50 now or stay in jail for 2 more turns","Pay jail fee?",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                jailoptions,
                jailoptions[0]);

        if(n == -1){
            System.exit(0);
        }

        return n;
    }

    private void payJail(Player currPlayer, int value) {
        currPlayer.payMoney(value);
        freeParking+=value;
        jailTurnCounter.remove(currPlayer);
        currPlayer.setOutJail();
        currPlayer.moveToPosition(10);
        nextTurn(noOfPlayers);
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

    //@132206
    private boolean jailCheck(Player currPlayer){
        boolean inJail = false;
        int jailCount = 0;
        if(currPlayer.inJail) {
            inJail = true;
            if (jailTurnCounter.containsKey(currPlayer)) {
                jailCount = jailTurnCounter.get(currPlayer);
                if (jailCount == 3) {
                    payJail(currPlayer,0);
                } else if (jailCount == 2) {
                    jailTurnCounter.replace(currPlayer, 3);
                } else if (jailCount == 1) {
                    jailTurnCounter.replace(currPlayer, 2);
                } else if (jailCount == 0) {
                    jailTurnCounter.replace(currPlayer, 1);
                }
            }
        }
        return inJail;
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
                    if(!player.isCPU){
                        if( developLocationDialog(currLoc.getName(), currLoc.getHouseDevelopmentPrice()) == 0 ) { // Dialog 'Yes' button pressed
                            currLoc.buyHouse(player);
                        } else {
                            return;// Dialog 'No' button pressed (exit loop)
                        }
                    } else {
                        // CPU DEVELOP if money is above 600
                        int n;
                        Random rand = new Random();
                        n = rand.nextInt((1 - 0) + 1) + 0;
                        if(n==1&&player.getMoney()>600) {
                            currLoc.buyHouse(player);
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    //@146674 Edited/modified below
    private void offerToBuy(Player player) {
        if (player.passedGo) {
            BoardLocation currLoc = board.board[player.getPosition()];
            if (currLoc.canBuy() && !currLoc.isOwned() & (player.getMoney()>=currLoc.getPrice())) {
                int n;
                if(!player.isCPU) {
                    Object[] buyoptions = {"Yes", "No"};
                    n = JOptionPane.showOptionDialog(null,
                            (player.getPlayerName() + ", you have landed on " + currLoc.getName() + ", would you like to buy it for: " + currLoc.getPrice() + "?"),
                            "Property Purchase",
                            JOptionPane.PLAIN_MESSAGE,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            buyoptions,
                            buyoptions[0]);
                } else {
                    Random rand = new Random();
                    n = rand.nextInt((1 - 0) + 1) + 0;
                }
                if(n == -1){
                    System.exit(0);
                }

                if (n == 0) {
                    currLoc.setOwner(player);
                    player.payMoney(currLoc.getPrice());
                    System.out.println("Player " + player.getPlayerName() + " now owns " + currLoc.getName());
                    System.out.println("Player " + player.getPlayerName() + " now has " + player.getMoney() + " coins");
                } else if (n == 1) {
                    doAuction(currLoc);
                }
            } else if( currLoc.isOwned() && !currLoc.getOwner().equals( player ) && currLoc.getAction().equals("") && !currLoc.getOwner().inJail && !currLoc.getMortgaged() ){
                if(player.getMoney()>= currLoc.getRentPrice()){
                    // @146674: if square is owned and cannot be baught, force player to pay rent if not owned by themselves
                    System.out.println("Player " + player.getPlayerName() + " paid " + currLoc.getOwner().getPlayerName() + " the amount of " + currLoc.getRentPrice() + " for landing on " + currLoc.getName());
                    player.payMoney( currLoc.getRentPrice() ); // Take rent money from current player
                    currLoc.getOwner().addMoney( currLoc.getRentPrice() ); // Add rent money to landlord
                } else if(player.getMoney()<currLoc.getRentPrice()){
                    //sellMortgageProperties(player, currLoc.getRentPrice());
                }
            } else if(currLoc.canBuy() && !currLoc.isOwned() && (player.getMoney()<currLoc.getPrice())){
                System.out.println("You cannot afford to buy the location");
            }
        }
    }

    private void sellMortgageProperties(Player player, int rentPrice) {

        //TODO Sell houses/hotels first.


        int sellMortgagePrice;
        int n;
        boolean mortgageableProperties = true;
        boolean sellableProperties = true;
        int noOfMortgaged = 0;
        int mortgagePrice = 0;
        int sellPrice = 0;

        ArrayList<BoardLocation> playerProperties = board.getLocationsOwnedByPlayer(player);

        for(int i = 0; i< playerProperties.size(); i++){
            if(playerProperties.get(i).getMortgaged()){
                noOfMortgaged +=1;
                mortgagePrice += ((playerProperties.get(i).getPrice())/2);
                sellPrice += ((playerProperties.get(i).getPrice()));
            }
        }


        if(rentPrice<mortgagePrice) {
            if(!player.isCPU) {
                n = sellMortgageDialog("You owe somebody rent of: " + rentPrice + " but you only have " + player.getMoney() + ". Would you like to sell or mortgage your properties?");
            } else {
                n = 0;
            }
        } else if(rentPrice<sellPrice){
            n=1;
        } else {
            for(int i = 0; i<playerProperties.size();i++){
                playerProperties.get(i).setOwner(null);
            }
            int e = JOptionPane.showConfirmDialog(null, (player.getPlayerName()) + " is bankrupt! They are no longer playing.", "Bankrupt Player.", JOptionPane.DEFAULT_OPTION);
            players.remove(player);
            noOfPlayers -= 1;
            isGameFinished();
            n=-2;
        }


        //RANDOM CHOICE FOR CPU????
        if(n == 0){
            //do sell
        } else if ( n == 1){
            //do mortgage
        }
    }

    //@132206
    // Creates an auction if they player declines to buy property.
    //TODO: does not check to see if two bids are the same.
    public void doAuction(BoardLocation currLoc){
        if(players.size()>2) {
            if (allPlayersPassedGo) {
                int[] bidArr = new int[players.size()];
                Player winningPlayer = null;
                int winningBid = 0;
                int dupBid = 0;
                JTextField pbid = new JTextField();

                for (int i = 0; i < players.size(); i++) {
                    if (!players.get(i).inJail && !players.get(i).isCPU()) {
                        Object[] message = {currLoc.getName() + " is up for auction. " + players.get(i).getPlayerName() + ", please enter your bid. You only get one bid!", pbid};
                        int playerBid = JOptionPane.showConfirmDialog(null, message, "Property Auction.", JOptionPane.OK_CANCEL_OPTION);
                        if (playerBid == -1) {
                            System.exit(0);
                        } else if (playerBid == 1) {
                            doAuction(currLoc);
                        }
                        bidArr[i] = Integer.parseInt(pbid.getText());
                        pbid.setText("");
                    } else if(players.get(i).isCPU()) {
                        Random r = new Random();
                        double randomValue = 0 + (2 - 0) * r.nextDouble();
                        bidArr[i] = (int) (currLoc.getPrice()*randomValue);
                    } else {
                        bidArr[i] = 0;
                    }
                }

                for (int i = 0; i < bidArr.length; i++) {
                    if (bidArr[i] > winningBid) {
                        winningBid = bidArr[i];
                        winningPlayer = players.get(i);
                    } else if (bidArr[i] == winningBid) {
                        dupBid = winningBid;
                    }
                }

                if (winningBid == dupBid) {
                    int playerBid = JOptionPane.showConfirmDialog(null, "Two or more people entered the same highest bid. Please re-bid.", "Property Auction.", JOptionPane.OK_CANCEL_OPTION);
                    doAuction(currLoc);

                    if (playerBid == -1) {
                        System.exit(0);
                    }
                }


                winningPlayer.payMoney(winningBid); // Take bid from current player
                currLoc.setOwner(winningPlayer);
                int bidwinMsg = JOptionPane.showConfirmDialog(null, winningPlayer.getPlayerName() + " won the bid! They now own " + currLoc.getName(), "Property Auction.", JOptionPane.OK_CANCEL_OPTION);

                if (bidwinMsg == -1) {
                    System.exit(0);
                }
            }
        }
    }

    //@132206
    // Creates a trade dialog for 2 players to trade a property.
    public void doTrade(Player currPlayer){

        Player otherPlayer;

        ArrayList<Player> otherPlayers = new ArrayList<>(players);
        otherPlayers.remove(currPlayer);


        //remove inJail players from array.
        for(int i = 0; i<otherPlayers.size();i++){
            if(otherPlayers.get(i).inJail){
                otherPlayers.remove(i);
            }
        }

        if(otherPlayers.size()>0) {
            String[] opa = new String[otherPlayers.size()];

            for (int i = 0; i < otherPlayers.size(); i++) {
                opa[i] = otherPlayers.get(i).getPlayerName();
            }

            JComboBox playerBox = new JComboBox(opa);

            Object[] playerSelectionMessage = {"Please select the player you wish to trade with: ", playerBox};
            int playerOption = JOptionPane.showConfirmDialog(null, playerSelectionMessage, "Trade System.", JOptionPane.OK_CANCEL_OPTION);

            if (playerOption == -1) {
                System.exit(0);
            }

            otherPlayer = otherPlayers.get(playerBox.getSelectedIndex());


            ArrayList<BoardLocation> playerProperties = new ArrayList<>();

            for (int i = 0; i < 39; i++) {
                if (board.board[i].getOwner() == currPlayer) {
                    playerProperties.add(board.board[i]);
                }
            }

            if(playerProperties.size()>0) {

                String[] playerPropertiesString = new String[playerProperties.size()];

                for (int i = 0; i < playerProperties.size(); i++) {
                    playerPropertiesString[i] = playerProperties.get(i).getName();
                }


                ArrayList<BoardLocation> otherPlayerProperties = new ArrayList<>();

                for (int i = 0; i < 40; i++) {
                    if (board.board[i].getOwner() == otherPlayer) {
                        otherPlayerProperties.add(board.board[i]);
                    }
                }

                if(otherPlayerProperties.size()>0) {

                    String[] otherPlayerPropertiesString = new String[otherPlayerProperties.size()];

                    for (int i = 0; i < otherPlayerProperties.size(); i++) {
                        otherPlayerPropertiesString[i] = otherPlayerProperties.get(i).getName();
                    }

                    JList pList = new JList(playerPropertiesString);
                    JList oList = new JList(otherPlayerPropertiesString);


                    Object[] message = {"Please select your properties that you wish to trade :", pList, "Please select " + otherPlayer.getPlayerName() + "'s properties that you want:", oList};
                    int option = JOptionPane.showConfirmDialog(null, message, "Trade System.", JOptionPane.OK_CANCEL_OPTION);

                    if (option == -1) {
                        System.exit(0);
                    }

                    if (option == 1) {
                        JOptionPane.showMessageDialog(null,
                                (currPlayer.getPlayerName() + " has cancelled the trade"),
                                "Trade Cancelled",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int[] pSelected = pList.getSelectedIndices();
                    int[] oSelected = oList.getSelectedIndices();

                    BoardLocation[] selectedPlayersProperties = new BoardLocation[pSelected.length];
                    BoardLocation[] selectedOtherPlayersProperties = new BoardLocation[oSelected.length];

                    //swapping properties for current player
                    int pPValue = 0; // Current (sender) player properties total value
                    int pPRent = 0;
                    int otherPValue = 0; // Other Player (target) properties total value
                    int otherPRent = 0;

                    if (pSelected.length > 0) {
                        for (int i = 0; i < pSelected.length; i++) {
                            selectedPlayersProperties[i] = playerProperties.get(pSelected[i]);
                            pPValue += selectedPlayersProperties[i].getPrice();
                            pPRent += selectedPlayersProperties[i].getRentPrice();
                        }
                    }

                    if (oSelected.length > 0) {
                        for (int i = 0; i < oSelected.length; i++) {
                            selectedOtherPlayersProperties[i] = otherPlayerProperties.get(oSelected[i]);
                            otherPValue += selectedOtherPlayersProperties[i].getPrice();
                            otherPRent += selectedOtherPlayersProperties[i].getRentPrice();
                        }
                    }

                    //   CONFIRM DIALOG

                    // Get names of properties to be traded
                    String[] selectedPPropertiesStringList = new String[ selectedPlayersProperties.length ];
                    String[] selectedOPropertiesStringList = new String[ selectedOtherPlayersProperties.length ];

                    for(int i = 0; i<selectedPlayersProperties.length; i++){
                        selectedPPropertiesStringList[i] = selectedPlayersProperties[i].getName();
                    }

                    for(int i = 0; i<selectedOtherPlayersProperties.length; i++){
                        selectedOPropertiesStringList[i] = selectedOtherPlayersProperties[i].getName();
                    }
                    //if CPU player, evaluate if price worth it and automatically accept/decline
                    // (( getAllPPropertiesRent *.20 ) + AllPPropertiesValue) <= ((getAllOPropertiesRent * .20) + AllOPropertiesValue)
                    if (otherPlayer.isCPU()) {
                        // CPU autocalculate if trade is worth while
                        if( ((pPRent * .20) + pPValue) <= ((otherPRent * .20) + otherPValue) ) {
                            JOptionPane.showMessageDialog(null,
                                    (otherPlayer.getPlayerName() + " has cancelled the trade"),
                                    "Trade Cancelled",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    //dialog to ask player whether to accept trade (show what is being traded)
                    JList confPPSL = new JList(selectedPPropertiesStringList);
                    JList confOPSL = new JList(selectedOPropertiesStringList);
                    Object[] messagePlayerConfirmTrade = {"Please confirm that you wish to trade your :", confPPSL, "For " + otherPlayer.getPlayerName() + "'s properties:", confOPSL };
                    int optionPlayerConfirm = JOptionPane.showConfirmDialog(null, messagePlayerConfirmTrade, "Trade System.", JOptionPane.OK_CANCEL_OPTION);

                    if (optionPlayerConfirm == -1) {
                        System.exit(0);
                    }

                    //Player confirmation
                    if (optionPlayerConfirm == 0){
                        //Swapping properties for other player
                        if (pSelected.length > 0) {
                            for (int i = 0; i < selectedPlayersProperties.length; i++) {
                                selectedPlayersProperties[i].setOwner(otherPlayer);
                            }
                        }

                        if (oSelected.length > 0) {
                            for (int i = 0; i < selectedOtherPlayersProperties.length; i++) {
                                selectedOtherPlayersProperties[i].setOwner(currPlayer);
                            }
                        }

                        // Dialog trade complete
                        JOptionPane.showMessageDialog(null,
                                ("Trade successful!"),
                                "Trade Complete",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        //Player declines
                        JOptionPane.showMessageDialog(null,
                                (currPlayer.getPlayerName() + " has cancelled the trade"),
                                "Trade Cancelled",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                } else {
                    System.out.println(otherPlayer.getPlayerName() + " doesn't have any properties to trade");
                }
            } else {
                System.out.println("You have no properties to trade.");
            }
        } else {
            System.out.println("There is no-one to trade with.");
        }
    }

    //@146674
    // Executes an action on player that landed on a location during their turn
    public boolean doAction(Player player, int[] dice){
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
                    //addFreeParking( 200 );
                    return true;

                // Super Tax
                case "taxSuper":
                    System.out.println("Player " + player.getPlayerName() + " landed on Super Tax! Pay £100.");
                    player.payMoney( 100 ); // Player pay penality of £100 to Free Parking
                    //addFreeParking( 100 );
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

                    value = 25; // Reset value
                    // check if player how many stations landlord owns
                    int stations = board.getNumberOfLocationsOwnedByPlayerUsingColour(currLoc.getOwner(), "station");

                    if( stations == 2) value = 50;
                    if( stations == 3) value = 100;
                    if( stations == 4) value = 200;

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

        return false;
    }

    //public void cardAction(Player currPlayer, Card card) throws Exception {
    public Boolean cardAction(Player player, Card card, int[] dice) {
        String action = card.getAction();
        int value = card.getValue();
        System.out.println( card.getDescription() );
        if (card.getValue() == 1) {
            potLuck.addCard(card);
        } else if (card.getValue() == 2) {
            opportunityKnocks.addCard(card);
        }

        switch(action) {
            case "get":
                player.addMoney(value);
                return true;

            case "pay":
                player.payMoney(value);
                return true;

            case "free":
                player.payMoney(value);
                addFreeParking(value);
                return true;

            case "jump":
                player.moveToPosition(value);
                //doAction( player, dice ); // Executes wherever player lands
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
                for(int i=0;i<players.size();i++) {
                    thisPlayer = players.get(i);
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
                player.moveToPosition(40);
                player.setInJail();
                return true;

            default:
                System.out.println("NO ACTION!" + action);
                //throw new Exception("NO ACTION!");
                break; // default will break/exit and return false
        }
        return false;
    }

    private void isGameFinished() {
        if(players.size()<2){
            System.out.println(players.get(0).getPlayerName() + " is the winner!");
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

        if(!allPlayersPassedGo){
            allPlayersPassedGo = true;
            for(int i = 0; i<players.size();i++){
                if(!players.get(i).passedGo && allPlayersPassedGo){
                    allPlayersPassedGo = false;
                }
            }
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
