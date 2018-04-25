/**
 *
 * @author Kieran
 */
public class BoardLocation {

    // this class is used to populate each board location and can hold players on the space, what the space is
    // and how much it costs etc.
// Kieran>>>>>>
     int position;
     String name;
     int value;
     String colour;
     String action;
     boolean canBuy;

     public BoardLocation(int position, String name, int value, String colour, String action, boolean canBuy){
         this.position = position;
         this.name = name;
         this.value = value;
         this.colour = colour;
         this.action = action;
         this.canBuy = canBuy;
     }

     public String getInformation(){
         String info = ("Position: " + Integer.toString(position) + ".\nName: " + name + ".\nValue: " + Integer.toString(value) + ".\nColour: " + colour +"\n");
         return info;
     }

    // Loz>>>
//    int tileCoord;
//
//    BoardLocation(int tileCoord) {
//        this.tileCoord = tileCoord;
//    }
//    public abstract boolean isLocationOccupied();
//    public abstract Piece getPiece();
//    public abstract String getInformation();
//
//    private static final class EmptyLocation extends BoardLocation{
//        EmptyLocation(int loc) {
//            super(loc)
//        }
//        @Override
//        public boolean isLocationOccupied(){
//            return false;
//        }
//
//        @Override
//        public Piece getPiece() {
//            return null;
//        }
//
//    }
//
//    private static final class OccupiedLocation extends Tile{
//        Piece pieceonLocation;
//
//        OccupiedLocation (int tileCoord, Piece pieceonLocation){
//            super(tileCoord);
//            this.pieceonTile = pieceonTile;
//        }
//
//        @Override
//        public boolean isTileOccupied(){
//            return true;
//        }
//
//        @Override
//        public Piece getpiece(){
//            return this.pieceonLoaction;
//        }
//    }
}
