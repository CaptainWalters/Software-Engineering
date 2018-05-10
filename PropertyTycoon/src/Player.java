import java.util.Stack;

/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
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

    // This method calculates the players "position" and if they go round the board
    // one whole time the position is set.
    public void movePosition(int noOfSpaces){
        position += noOfSpaces;
        if (position > 39) {
            passedGo = true;
            passedGo();
            position = position % 40;
        }
    }

    //Getters & Setters

    private void passedGo() {
        System.out.println("Player " + playerName + " has passed GO!"); // DEBUG
        money+=200;
    }

    public int getPosition(){
        return position;
    }

    public int getMoney(){
        return money;
    }

    public String getPlayerName(){
        return playerName;
    }

    public int getPlayerNumber(){
        return playerNo;
    }

    public Enum getPlayerToken(){
        return token;
    }

    public boolean isCPU(){
        return isCPU;
    }

    //Getters & Setters End

    void addMoney(int i) {
        money += i;
    }
    void payMoney(int i) {
        money -= i;
    }

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

    public void moveToPosition(int position) {
        this.position = position;
    }

    public void setInJail(){
        inJail = true;
    }

    public void setOutJail(){
        inJail = false;
    }

    public void keepCard(Card card){
        heldCards.push(card);
    }
    public Card returnCard(){
        return heldCards.pop();
    }
}
