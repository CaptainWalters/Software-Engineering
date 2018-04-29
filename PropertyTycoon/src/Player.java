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

    public void moveToPosition(int position) {
        this.position = position;
    }

    public void pay(int value){
        money-= value;
    }
}
