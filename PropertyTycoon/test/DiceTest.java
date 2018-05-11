/**
 *
 * @author Oliver(134730)
 *
 */

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {

    private Dice d = new Dice();
    private Set<Integer> set = new HashSet<>();

    public DiceTest() {
        for (int i = 0; i < 1000; i++) {
            set.add(d.rollDice());
        }
    }

    @Test
    public void rollDiceWithinBoundary() {
        for (int s:set) {
            assertTrue(s >= 1 && s <= 6);
        }
    }

    @Test
    public void allNumbersAreRolled() {
        for(int i=1;i<=6;i++) {
            assertTrue(set.contains(i));
        }
    }
}