/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */
public class BoardLocation {

    // this class is used to populate each board location and can hold players on the space, what the space is
    // and how much it costs etc.

    private final int position;
    private final String name;
    private final int locationValue;
    private final int[] rentValue; // @146674 rent values
    private int developments; // @146674 property type on this location (refer to rent values index for property type)
    private final String colour;
    private final String action;
    private boolean canBuy; // Should not be final
    private Player owner; // Should not be final
    private boolean mortgaged;

    /**
     *
     * @param position
     * @param name
     * @param value
     * @param rent
     * @param colour
     * @param action
     * @param canBuy
     */
    public BoardLocation(int position, String name, int value, int[] rent, String colour, String action, boolean canBuy){
        this.position = position;
        this.name = name;
        this.locationValue = value;
        this.rentValue = rent; // @146674: param rent accepts an array of 6 values [0 = basic rent, 5 = hotel rent price]
        this.developments = 0; // @146674: [0] No house; [1] 1 house; ... ; [4] 4 houses; [5] Hotel
        this.colour = colour;
        this.action = action;
        this.canBuy = canBuy;
        this.owner = null;
        this.mortgaged = false;
        System.out.println(toString()); // DEBUG
    }

    //@146674
    @Override
    public String toString() {
        return "Position: " + Integer.toString(position) + "\nName: " + name + "\nPrice to Buy: " + Integer.toString(locationValue)
                 + "\nBasic Rent: " + rentValue[0]+ "\nRent with 1 House: " + rentValue[1] + "\nRent with 2 Houses: " + rentValue[2]
                 + "\nRent with 3 Houses: " + rentValue[3] + "\nRent with 4 Houses: " + rentValue[4] + "\nRent with a Hotel: " + rentValue[5]
                 + "\nColour: " + colour + "\nAction: " + action + "\nCan Player buy this: " + canBuy + "\nCurrent owner: " + owner + "\nProperty Mortgaged: " + mortgaged + "\n";
    }

    public String getName(){
        return name;
    }

    //@146674
    public String getInformation(){
        return this.toString();
    }
     
    //@146674
    public String getColour(){
        return this.colour;
    }

    public int getPrice(){
        return locationValue;
    }
     
    // getRentPrices() returns rent price according to houses/hotel propertyType
    public int getRentPrice(){
        return this.rentValue[ this.developments ]; // propertyType: [0] No house rent; [1] 1 house rent; ... ; [4] 4 houses rent; [5] Hotel rent
    }

    // Get BoardLocation's owner (returns Player type object)
    public Player getOwner(){
        return this.owner;
    }

    public Boolean getCanBuy(){
        return this.canBuy;
    }

    public int getDevelopments(){
        return this.developments;
    }

    //@146674
    // Location Action is now fed to Game::doAction() using value from this method
    public String getAction(){
        return this.action;
    }

    //@146674
    public int getHouseDevelopmentPrice(){
        int price = 0;
        switch( this.colour ){
            case "brown":
            case "blue":
                // House = $50
                // Hotel = 4xHouse + $50
                if( this.developments >= 4 ) price = 250; else price = 50;
                break;

            case "purple":
            case "orange":
                // House = $100
                // Hotel = 4xHouse + $100
                if( this.developments >= 4 ) price = 500; else price = 100;
                break;

            case "red":
            case "yellow":
                // House = $150
                // Hotel = 4xHouse + $150
                if( this.developments >= 4 ) price = 750; else price = 150;
                break;

            case "green":
            case "deep blue":
                // House = $200
                // Hotel = 4xHouse + $200
                if( this.developments >= 4 ) price = 1000; else price = 200;
                break;

            default:
                break; // Do nothing for utility/station and other locations (returns price by default)
        }
        return price;
    }

    public boolean getMortgaged(){
        return mortgaged;
    }

    public void setMortgaged(boolean value){
        mortgaged = value;
    }

    public void setOwner(Player player){
        owner = player;
    }

    public Boolean isOwned(){
        return (this.owner != null);
    } //Returns boolean defining if property is owned
     
    //@146674
    public Boolean buyHouse(Player player){

        // check if this location is maxed out with houses
        if( this.developments >= 5 )
            return false;
         
        // Only pay the bank
        player.payMoney( getHouseDevelopmentPrice() ); // this should be checked if true (player has enough money) before incrementing
        this.developments++; // Add a house
        System.out.println("Player " + player.getPlayerName() + " has built " + this.developments + " house(s) on " + this.getName());
        return true;
    }

    public void mortgageLocation(){
           setMortgaged(true);
    }

    public void unmortgageLocation(){
        setMortgaged(false);
    }

}
