/**
 *
 * @author Oliver(134730)
 *
 */

/**
 * This class holds the individual cards of the game.
 */
public class Card {

    private final String description;
    private final String action;
    private final int value;


    public Card(String description, String action, int value) {
        this.description = description;
        this.action = action;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public String getAction() {
        return action;
    }

    public int getValue() {
        return value;
    }
}
