package property_tycoon;

/**
 *
 * @author Kieran
 */
public class BoardLocation {
    
    // this class is used to populate each board location and can hold players on the space, what the space is 
    // and how much it costs etc.

    int position;
    String name;
    int value;
    String colour;
    String action;
    boolean canBuy;
    
    public void BoardLocation(int position, String name, int value, String colour, String action, boolean canBuy){
        this.position = position;
        this.name = name;
        this.value = value;
        this.colour = colour;
        this.action = action;
        this.canBuy = canBuy;
    }
    
    public String getInformation(){
        String info = ("Position: " + Integer.toString(position) + "./nName: " + name + "./nValue: " + Integer.toString(value) + "./nColour: " + colour);
        return info;
    }
}
