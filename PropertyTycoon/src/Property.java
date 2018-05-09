/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */
public class Property extends BoardLocation{

    // this class is used to populate each board location and can hold players on the space, what the space is
    // and how much it costs etc.
     
    //@146674 set all class properties to private and some final
    private final int position;
    private final String name;
    private final int value;
    private final int[] rent; // @146674 rent values
    private final String colour;
    private int development; // @146674 property type on this location (refer to rent values index for property type)
    private Player owner; // Should not be final

    public Property(int position, String name, int value, int[] rent, String colour){
        super(position, name);
        this.position = position;
        this.name = name;
        this.value = value;
        this.rent = rent; // @146674: param rent accepts an array of 6 values [0 = basic rent, 5 = hotel rent price]
        this.colour = colour;
        development = 0; // @146674: [0] No house; [1] 1 house; ... ; [4] 4 houses; [5] Hotel
        owner = null;
    }

    // Needs updating after 146674 changes
    public String getInformation(){
        return ("Position: " + Integer.toString(position) + ".\nName: " + name + ".\nValue: " + Integer.toString(value) + ".\nColour: " + colour +"\n");
    }
     
    //@146674
    public String getColour(){
         return this.colour;
     }

    public String getName(){
         return name;
     }

    public int getPrice(){
         return value;
     }
     
    // getRentPrices() returns rent price according to houses/hotel propertyType
    public int getRentPrice(){
        //@146674
        // propertyType: [0] No house rent; [1] 1 house rent; ... ; [4] 4 houses rent; [5] Hotel rent
        return this.rent[ this.propertyType ];
    }

    public void setOwner(Player player){
         owner = player;
     }
     
    // Get boardlocation's owner (returns Player type object)
    public Player getOwner(){
        //@146674
        return this.owner;
    }

    public Boolean isOwned(){
        //@146674 removed isOwned property and created this method instead
        return (this.owner != null);
    }
     
    //@146674
    public int numberOfPropertiesBuilt(){
         return this.propertyType;
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
                if( this.propertyType >= 4 ) price = 250; else price = 50;
                break;

            case "purple":
            case "orange":
                 // House = $100
                 // Hotel = 4xHouse + $100
                 if( this.propertyType >= 4 ) price = 500; else price = 100;
                 break;

            case "red":
            case "yellow":
                // House = $150
                // Hotel = 4xHouse + $150
                if( this.propertyType >= 4 ) price = 750; else price = 150;
                break;

            case "green":
            case "deep blue":
                // House = $200
                // Hotel = 4xHouse + $200
                if( this.propertyType >= 4 ) price = 1000; else price = 200;
                break;
                 
            default:
                break; // Do nothing for utility/station and other locations (returns price by default)
        }
        return price;
    }
     
    //@146674
    public Boolean buyHouse(Player player){

        // check if this location is maxed out with houses
        if( this.propertyType >= 5 )
            return false;
         
        // Only pay the bank
        player.payMoney( getHouseDevelopmentPrice() ); // this should be checked if true (player has enough money) before incrementing
        this.propertyType++; // Add a house
        System.out.println("Player " + player.getPlayerName() + " has built " + this.propertyType + " house(s) on " + this.getName());
        return true;
    }
}
