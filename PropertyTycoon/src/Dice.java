import java.util.Random;

/**
 *
 * @author Kieran(132206)
 *
 */

public class Dice {

    public int rollDice(){
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }
}
