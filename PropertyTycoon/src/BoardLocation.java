/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */
public class BoardLocation {

    // this class is used to populate each board location and can hold players on the space, what the space is
    // and how much it costs etc.
     
    //@146674 set all class properties to private and some final
     private final int position;
     private final String name;
     private final int locationValue;
     private final int[] rentValue; // @146674 rent values
     private int propertyType; // @146674 property type on this location (refer to rent values index for property type)
     private final String colour;
     private final String action;
     private boolean canBuy; // Should not be final
     private Player owner; // Should not be final
     private boolean mortgaged;

     public BoardLocation(int position, String name, int value, int[] rent, String colour, String action, boolean canBuy){
         this.position = position;
         this.name = name;
         this.locationValue = value;
         this.rentValue = rent; // @146674: param rent accepts an array of 6 values [0 = basic rent, 5 = hotel rent price]
         this.propertyType = 0; // @146674: [0] No house; [1] 1 house; ... ; [4] 4 houses; [5] Hotel
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

     //@146674
     public String getInformation(){
         return this.toString();
     }
     
     //@146674
     public String getColour(){
         return this.colour;
     }

     public String getName(){
         return name;
     }

     public int getPrice(){
         return locationValue;
     }
     
     // getRentPrices() returns rent price according to houses/hotel propertyType
     public int getRentPrice(){
         //@146674
         // propertyType: [0] No house rent; [1] 1 house rent; ... ; [4] 4 houses rent; [5] Hotel rent
         return this.rentValue[ this.propertyType ];
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
     public Boolean canBuy(){
         return this.canBuy;
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

     public void mortgageLocation(){
            setMortgaged(true);
     }

     public void unmortgageLocation(){
         setMortgaged(false);
     }

     public void setMortgaged(boolean value){
         mortgaged = value;
     }

     public boolean getMortgaged(){
         return mortgaged;
     }
}
