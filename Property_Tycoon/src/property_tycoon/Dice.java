package property_tycoon;

import java.util.Random;

/**
 *
 * @author 132206
 */

public class Dice {
 
    
    public int rollDice(){
        Random rand = new Random();
        
        return rand.nextInt(6)+1; 
    }
    
}