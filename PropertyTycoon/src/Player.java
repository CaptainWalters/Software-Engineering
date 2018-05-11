import java.util.Stack;

/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

/**
 * This class represents an individual game player.
 */
public class Player {
    int money;
    int playerNo;
    String playerName;
    Enum token;
    boolean isCPU;
    boolean passedGo;
    int position;
    Stack<Card> heldCards;
    boolean inJail;

    /**
     * This constructor creates the player with the given param values.
     *
     * @param playerNo The player number (1-6).
     * @param playerName The name of the player.
     * @param token The token the player picked.
     * @param cpu A boolean stating whether the player is a cpu or not.
     */
    public Player(int playerNo, String playerName, Enum token, boolean cpu){
        this.playerNo = playerNo;
        this.playerName = playerName;
        this.token = token;
        isCPU = cpu;
        money = 1500;
        position = 0;
        passedGo = false; // SHOULD BE FALSE, ONLY TRUE FOR TESTING.
        inJail = false;
        heldCards = new Stack();
    }

    /**
     * This method calculates the players "position". If their position is over 39 (past the bounds of the board array)
     * then the number is %40 to start the player at the beginning of the board again. If the player passes go,
     * passedGo() is called to perform the correct actions for passing go.
     *
     * @param noOfSpaces the number of spaces to move the player forward.
     */
    public void movePosition(int noOfSpaces){
        position += noOfSpaces;
        if (position > 39) {
            passedGo();
            position = position % 40;
        }
    }

    /**
     * This method sets players money to the given param.
     *
     * @param i The total value of "money" to set to the player.
     */
    public void setMoney(int i){
        money = i;
    }


    /**
     * This method gives the player +200 money for passing go and sets the passed go boolean to true
     */
    private void passedGo() {
        System.out.println("Player " + playerName + " has passed GO!"); // DEBUG
        if(!passedGo) {
            passedGo = true;
        }
        money+=200;
    }

    /**
     * This method gets the current position of the player.
     *
     * @return The position of the player.
     */
    public int getPosition(){
        return position;
    }

    /**
     * This method gets the total money the players owns.
     *
     * @return The amount of money the player currently has.
     */
    public int getMoney(){
        return money;
    }

    /**
     * This method gets the players name.
     *
     * @return The players name
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * This method gets the player number.
     *
     * @return The player number.
     */
    public int getPlayerNumber(){
        return playerNo;
    }

    /**
     * This method gets the players token
     *
     * @return The players token.
     */
    public Enum getPlayerToken(){
        return token;
    }

    /**
     * This method returns a boolean value referring to whether the player is a human or a computer.
     *
     * @return boolean value. true if computer, false if human.
     */
    public boolean isCPU(){
        return isCPU;
    }

    /**
     * This method adds money to the players current amount of money.
     *
     * @param i The amount of money to add.
     */
    void addMoney(int i) {
        money += i;
    }

    /**
     * This method subtracts money from the players current amount of money.
     *
     * @param i The total value of "money" to remove from the player.
     */
    void payMoney(int i) {
        money -= i;
    }

    /**
     * This method draws a card from pot luck or opportunity knocks for the player. If the card is get out of jail
     * free the card is added to a card array inside player, otherwise the card is returned.
     *
     * @param deck The deck of cards to draw a card from.
     * @return null if card is not get out of jail free. The card that was picked if not.
     */
    public Card drawCard(Deck deck){
        Card card;
        String action;

        card = deck.drawCard();
        action = card.getAction();

        if(action.equals("multipass")) {
            keepCard(card);
            return null;
        } else {
            return card;
        }
    }

    /**
     * This method moves the player to a set location. It is used when the player needs to move to a specific location
     * without passing go, such as going to jail.
     *
     * @param position The position the player is being moved to.
     */
    public void moveToPosition(int position) {
        this.position = position;
    }

    /**
     * This method is used to set the jail boolean to true when a player is sent to jail.
     */
    public void setInJail(){
        inJail = true;
    }

    /**
     * This method is used to set the jail boolean to false when a player comes out of jail.
     */
    public void setOutJail(){
        inJail = false;
    }

    /**
     * This method is used to "keep" the get out of jail free card.
     *
     * @param card The get out of jail free card to be assigned to the player.
     */
    public void keepCard(Card card){
        heldCards.push(card);
    }

    /**
     * This method is used to "use" the get out of jail free card.
     *
     * @return card The get out of jail free card to be replaced at the bottom of the deck.
     */
    public Card returnCard(){
        return heldCards.pop();
    }
}
