import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

/**
 *
 * This class represents the playing board in the game in the form of an array of BoardLocations. It is used to hold
 * all the location information in the form of BoardLocation.
 *
 */
public class Board {
     BoardLocation[] board;

    /**
     *This is the constructor for Board, which takes in a csv file. This is so changes can be altered easily.
     *
     * @param file A csv file containing the information to create every location on the board.
     */
     public Board(File file) {
         board = new BoardLocation[41];
         try {
             populateBoard(file);
         } catch (IOException e) {
            e.printStackTrace();
         }
     }

    /**
     *This method calls .getInformation from BoardLocation and is used to get the information of a specific location.
     *
     * @param position int value refering to location at index of board.
     * @return String A string containing all the information of the specific location.
     */
     public String getInfo (int position){
         return board[position].getInformation();
     }

    /**
     *This method returns all the properties owned by the specified player.
     *
     * @param player The player whos properties you want in the ArrayList.
     * @return ArrayList of all properties of the specified player.
     */
     public ArrayList<BoardLocation> getLocationsOwnedByPlayer(Player player){
         ArrayList<BoardLocation> playerProperties = new ArrayList<>();
         for (int i = 0; i < 40; i++) {
             if (board[i].getOwner() == player) {
                 playerProperties.add(board[i]);
             }
         }
         return playerProperties;
     }

    /**
     *This method returns the total number of houses the specified player currently has on the board.
     *
     * @param player The player of which you want to find out how many houses they own.
     * @return The number of houses the specified player owns.
     */
     public int getNumberOfHousesDevelopedByPlayer(Player player){
        int out = 0;
        for (BoardLocation location : board) // Loop through board
            if( location.isOwned() )
               if( location.getOwner().equals(player) )
                   out += (location.getDevelopments() < 5) ? location.getDevelopments():0; // Adds number of Houses and ignores Hotels
        return out;
     }

    /**
     *This method returns the number of hotels the specified player currently has on the board.
     *
     * @param player The player of which you want to find out how many hotels they own.
     * @return The number of hotels the specified player owns.
     */
     public int getNumberOfHotelsDevelopedByPlayer(Player player){
        int out = 0;
        for (BoardLocation location : board) // Loop through board
            if( location.isOwned() )
               if( location.getOwner().equals(player) )
                   out += (location.getDevelopments() == 5) ? 1:0; // Adds one if hotel found, zero otherwise
        return out;
     }

    /**
     *The number of properties a player owns of the given colour.
     *
     * @param player The player of which you want to find out how many properties of a given colour you want to find.
     * @param colourCheck The colour of the property you want to know how many the player owns of.
     * @return the number of properties of colourCheck owned by the player
     */
     public int getNumberOfLocationsOwnedByPlayerUsingColour(Player player, String colourCheck){
         int out = 0;
         for (BoardLocation location : board)
             if( location.getColour().equals(colourCheck) && location.isOwned() )
                if( location.getOwner().equals(player) )
                    out++;
         return out;
     }

    /**
     *This method populated the board given the csv file of location details. If the file is not found or not as
     * expected an error is thrown.
     *
     * @param file The csv file containing the location details.
     */
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
