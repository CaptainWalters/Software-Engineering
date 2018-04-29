/**
 *
 * @author Kieran(132206)
 *
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
     boolean isOwned;
     Player owner;

     public BoardLocation(int position, String name, int value, String colour, String action, boolean canBuy){
         this.position = position;
         this.name = name;
         this.value = value;
         this.colour = colour;
         this.action = action;
         this.canBuy = canBuy;
         this.isOwned = false;
         this.owner = null;
     }

     public String getInformation(){
         String info = ("Position: " + Integer.toString(position) + ".\nName: " + name + ".\nValue: " + Integer.toString(value) + ".\nColour: " + colour +"\n");
         return info;
     }

     public String getName(){
         return name;
     }

     public int getPrice(){
         return value;
     }

     public void setOwner(Player player){
         owner = player;
         isOwned = true;
     }

}
