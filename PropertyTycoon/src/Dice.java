import java.util.Random;

/**
 * This class represents the dice.
 * @author 132206
 */

public class Dice {
    /**
     * This method simulates rolling a die by returning a random number between 1 and 6.
     * @return int A number between 1 and 6
     */
    public int rollDice(){
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }
}
