import java.util.Stack;

/**
 *
 * @author Kieran(132206)
 *
 */


public class Player {
    int money;
    int playerNo;
    String playerName;
    Enum token;
    boolean cpu;
    boolean passedGo;
    int position;
    Stack<Card> heldCards;


    public Player(int playerNo, String playerName, Enum token, boolean cpu){
        this.playerNo = playerNo;
        this.playerName = playerName;
        this.token = token;
        this.cpu = cpu;
        money = 1500;
        position = 0;
        passedGo = true;
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
        return cpu;
    }

    //Getters & Setters End

    private void addMoney(int i) {
        money += i;
    }
    private void payMoney(int i) {
        money -= i;
    }

    public Card drawCard(Deck deck){
        Card card;
        String action;

        card = deck.getNextCard();
        action = card.getAction();

        if(action.equals("keep")) {
            heldCards.push(card);
            return null;
        } else {
            return card;
        }
    }

    public void doAction(String action, String value) throws Exception {
        switch(action) {
            case "get":
                addMoney(Integer.parseInt(value));
                break;
            case "pay":
                payMoney(Integer.parseInt(value));
                break;
            case "jump":
                moveToPosition(Integer.parseInt(value));
                break;
            case "select":
                //addMoney(Integer.parseInt(value));
                break;
            case "free":
                addMoney(Integer.parseInt(value));
                break;
            case "collect":
                addMoney(Integer.parseInt(value));
                break;
            case "keep":
                addMoney(Integer.parseInt(value));
                break;
            case "move":
                addMoney(Integer.parseInt(value));
                break;
            case "repair":
                addMoney(Integer.parseInt(value));
                break;
            case "jail":
                addMoney(Integer.parseInt(value));
                break;
            default:
                throw new Exception("NO ACTION!");
        }
    }

    public void moveToPosition(int position) {
        this.position = position;
    }

    public void pay(int value){
        money-= value;
    }

}
