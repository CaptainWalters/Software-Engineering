package property_tycoon;

/**
 *
 * @author 132206
 *
 */


public class Player {
    int money;
    int playerNo;
    String playerName;
    String token;
    boolean banker;
    boolean cpu;
    
    int position;
    
    public void Player(int playerNo, String playerName, String token, boolean banker, boolean cpu){
        this.playerNo = playerNo;
        this.playerName = playerName;
        this.token = token;
        this.banker = banker;
        this.cpu = cpu;
        money = 1500;
        position = 0;
    }
    
    // This method calculates the players "position" and if they go round the board 
    // one whole time the position is set.
    public void movePosition(int noOfSpaces){
        position += noOfSpaces;
        if (position > 39) {
            //passed go
            position -= 40;
        }
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
    
    public String getPlayerToken(){
        return token;
    }
    
    public boolean isBanker(){
        return banker;
    }
    
    public boolean isCPU(){
        return cpu;
    }
}
