/**
 *
 * @author Oliver
 */

public class Card {

    private String description;
    private String action;
    private String value;

    public void Card(String description, String action, String value) {
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

    public String getValue() {
        return value;
    }

}
