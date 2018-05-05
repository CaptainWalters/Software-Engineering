/**
 *
 * @author Oliver
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
        return this.description;
    }

    public String getAction() {
        return this.action;
    }

    public int getValue() {
        return this.value;
    }
}
