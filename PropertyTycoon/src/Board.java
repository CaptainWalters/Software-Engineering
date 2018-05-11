import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

public class Board {
     BoardLocation[] board;

     public Board(File file) {
         board = new BoardLocation[41];
         try {
             populateBoard(file);
         } catch (IOException e) {
            e.printStackTrace();
         }
     }

     public String getInfo (int position){
         return board[position].getInformation();
     }

     public ArrayList<BoardLocation> getLocationsOwnedByPlayer(Player player){
         ArrayList<BoardLocation> playerProperties = new ArrayList<>();
         for (int i = 0; i < 40; i++) {
             if (board[i].getOwner() == player) {
                 playerProperties.add(board[i]);
             }
         }
         return playerProperties;
     }
     
     //@146674
     public int getNumberOfHousesDevelopedByPlayer(Player player){
        int out = 0;
        for (BoardLocation location : board) // Loop through board
            if( location.isOwned() )
               if( location.getOwner().equals(player) )
                   out += (location.getDevelopments() < 5) ? location.getDevelopments():0; // Adds number of Houses and ignores Hotels
        return out;
     }
     
     //@146674
     public int getNumberOfHotelsDevelopedByPlayer(Player player){
        int out = 0;
        for (BoardLocation location : board) // Loop through board
            if( location.isOwned() )
               if( location.getOwner().equals(player) )
                   out += (location.getDevelopments() == 5) ? 1:0; // Adds one if hotel found, zero otherwise
        return out;
     }
     
     //@146674
     public int getNumberOfLocationsOwnedByPlayerUsingColour(Player player, String colourCheck){
         int out = 0;
         for (BoardLocation location : board)
             if( location.getColour().equals(colourCheck) && location.isOwned() )
                if( location.getOwner().equals(player) )
                    out++;
         return out;
     }

     public void populateBoard(File file) throws IOException {
         BufferedReader br;
         try {
             br = new BufferedReader(new FileReader(file));
             String line = br.readLine();
             int i = 0;
             while (line != null) {
                 int[] rentArray = new int[6];
                 String[] attributes = line.split(",");
                 int ID = Integer.parseInt( attributes[0] );
                 String name = attributes[1];
                 int value = Integer.parseInt( attributes[2] );
                 rentArray[0] = Integer.parseInt( attributes[3] );
                 rentArray[1] = Integer.parseInt( attributes[4] );
                 rentArray[2] = Integer.parseInt( attributes[5] );
                 rentArray[3] = Integer.parseInt( attributes[6] );
                 rentArray[4] = Integer.parseInt( attributes[7] );
                 rentArray[5] = Integer.parseInt( attributes[8] );
                 String colour = attributes[9];
                 String action = attributes[10];
                 Boolean canBuy = Boolean.valueOf( attributes[11] );
                 board[i] = new BoardLocation(ID, name, value, rentArray, colour, action, canBuy );
                 i++;
                 line = br.readLine();
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     
}
