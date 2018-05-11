/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

/**
 * This class represents the individual location of the board. Such as Go, Properties, Pot luck etc.
 */
public class BoardLocation {

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
     *This is the constructor for BoardLocation which loads the values for each individual property.
     *
     * @param position The position on the board of the location.
     * @param name The name of the location.
     * @param value The cost to buy the location.
     * @param rent An int array which holds the values of the rent, including with all improvements.
     * @param colour The colour of the location space
     * @param action The action performed when a player lands on the space.
     * @param canBuy A boolean value stating whether or not a player can buy the location.
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
        //System.out.println(toString()); // DEBUG
    }

    /**
     * This method overrides the default .toString() method and is used for printing about a description of the location.
     */
    @Override
    public String toString() {
        return "Position: " + Integer.toString(position) + "\nName: " + name + "\nPrice to Buy: " + Integer.toString(locationValue)
                 + "\nBasic Rent: " + rentValue[0]+ "\nRent with 1 House: " + rentValue[1] + "\nRent with 2 Houses: " + rentValue[2]
                 + "\nRent with 3 Houses: " + rentValue[3] + "\nRent with 4 Houses: " + rentValue[4] + "\nRent with a Hotel: " + rentValue[5]
                 + "\nColour: " + colour + "\nAction: " + action + "\nCan Player buy this: " + canBuy + "\nCurrent owner: " + owner + "\nProperty Mortgaged: " + mortgaged + "\n";
    }

    /**
     * This method returns the name of the location
     *
     * @return The name of the location.
     */
    public String getName(){
        return name;
    }

    /**
     * This method returns all the information refering to the location.
     *
     * @return All the information of the location.
     */
    public String getInformation(){
        return this.toString();
    }

    /**
     * This method returns the colour of the location
     *
     * @return The colour of the location.
     */
    public String getColour(){
        return this.colour;
    }

    /**
     * This method returns the price of the location
     *
     * @return The price of the location.
     */
    public int getPrice(){
        return locationValue;
    }

    /**
     * This method returns the rent price of the location, and takes into account whether the location has any houses or hotels.
     *
     * @return The price of rent of the location.
     */
    public int getRentPrice(){
        return this.rentValue[ this.developments ]; // propertyType: [0] No house rent; [1] 1 house rent; ... ; [4] 4 houses rent; [5] Hotel rent
    }

    /**
     * This method returns the owner of the location
     *
     * @return The owner of the location.
     */
    public Player getOwner(){
        return this.owner;
    }

    /**
     * This method returns the boolean canBuy of the location.
     *
     * @return The boolean refering to whether someone can buy the location.
     */
    public Boolean getCanBuy(){
        return this.canBuy;
    }

    /**
     * This method returns the number of developments on the location
     *
     * @return The number of developments "built" on the location.
     */
    public int getDevelopments(){
        return this.developments;
    }

    /**
     * This method returns the action of the location
     *
     * @return The action of the location.
     */
    public String getAction(){
        return this.action;
    }

    //@146674
    /**
     * This method returns the price of building a house or hotel on the location.
     *
     * @return The the price of building a house or hotel on the location.
     */
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


    /**
     * This method returns a boolean referring to whether the location is currently mortgaged or not.
     *
     * @return The boolean referring to whether the location is currently mortgaged or not.
     */
    public boolean getMortgaged(){
        return mortgaged;
    }

    /**
     * This method sets the mortgaged value to the value in the param.
     *
     * @param value true or false value that is being set.
     */
    public void setMortgaged(boolean value){
        mortgaged = value;
    }

    /**
     * This method sets the owner of the location.
     *
     * @param player The player that owns the location.
     */
    public void setOwner(Player player){
        owner = player;
    }

    /**
     * This method returns the isOwned boolean value.
     *
     * @return true if the location is owned by a player, otherwise null.
     */
    public Boolean isOwned(){
        return (this.owner != null);
    } //Returns boolean defining if property is owned

    /**
     * This method places a house on the location.
     *
     * @param player The player that is buying a house.
     */
    public Boolean buyHouse(Player player) {
        // check if this location is maxed out with houses
        if (this.developments >= 5){
            return false;
        }
        // Only pay the bank
        player.payMoney( getHouseDevelopmentPrice() ); // this should be checked if true (player has enough money) before incrementing
        this.developments++; // Add a house
        System.out.println("Player " + player.getPlayerName() + " has built " + this.developments + " house(s) on " + this.getName());
        return true;
    }

     //@146674
     public Boolean canBuy(){
         return this.canBuy;
     }

    /**
     * This method sets the mortgaged value to true.
     */
    public void mortgageLocation(){
           setMortgaged(true);
    }

    /**
     * This method sets the mortgaged value to false.
     */
    public void unmortgageLocation(){
        setMortgaged(false);
    }

}
