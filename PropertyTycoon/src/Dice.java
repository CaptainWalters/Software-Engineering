import java.util.Random;

/**
 *
 * @author Kieran(132206)
 *
 */

/**
 * This class represents the dice.
 */
public class Dice {
    /**
     * This method imatates rolling a dice by returning a random number between 1 and 6.
     *
     * @return A number between 1 and 6
     */
    public int rollDice(){
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }
}
