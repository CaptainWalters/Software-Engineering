/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */
public class Instruction extends BoardLocation{

    // this class is used to populate each board location and can hold players on the space, what the space is
    // and how much it costs etc.
     
    //@146674 set all class properties to private and some final
    private final int position;
    private final String name;
    private final String action;

    public Instruction(int position, String name, String action){
        super(position, name);
        this.position = position;
        this.name = name;
        this.action = action;
    }

    // Needs updating after 146674 changes
    @Override
    public String getInformation(){
         String info = ("Position: " + Integer.toString(position) + ".\nName: " + name + ".\nValue: " + Integer.toString(locationValue) + ".\nColour: " + colour +"\n");
         return info;
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
