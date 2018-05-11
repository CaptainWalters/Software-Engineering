/**
 * This Class creates an object that represents Cards used in the game.
 * @author 134730
 *
 */
public class Card {

    private final String description;
    private final String action;
    private final int value;

    /**
     * The constructor for the Card class.
     *  @param description The description or message on the card
     *  @param action The action that the card will execute internally on the Player
     *  @param value Arbitrary value to hold regarding the card (e.g. location ID on board or fee price)
     */
    public Card(String description, String action, int value) {
        this.description = description;
        this.action = action;
        this.value = value;
    }

    /**
     * Get the description/message of the Card object
     * @return String description/message of the Card
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the action of the Card object
     * @return String action to execute on Player
     */
    public String getAction() {
        return action;
    }

    /**
     * Get the arbitrary value for the Card object
     * @return String arbitrary value for Card
     */
    public int getValue() {
        return value;
    }
}
