import java.io.IOException;

/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */

public class Board {
     BoardLocation[] board;

     public Board() throws IOException {
         board = new BoardLocation[41];
         populateBoard();
     }

     public String getInfo (int position){
         return board[position].getInformation();
     }
     
     //@146674
     public int getNumberOfHousesDevelopedByPlayer(Player player){
        int out = 0;
        for (BoardLocation location : board) // Loop through board
            if( location.isOwned() )
               if( location.getOwner().equals(player) )
                   out += (location.numberOfPropertiesBuilt() < 5) ? location.numberOfPropertiesBuilt():0; // Adds number of Houses and ignores Hotels
        return out;
     }
     
     //@146674
     public int getNumberOfHotelsDevelopedByPlayer(Player player){
        int out = 0;
        for (BoardLocation location : board) // Loop through board
            if( location.isOwned() )
               if( location.getOwner().equals(player) )
                   out += (location.numberOfPropertiesBuilt() == 5) ? 1:0; // Adds one if hotel found, zero otherwise
        return out;
     }
     
     //@146674
     public int getTotalNumberOfPropertiesDevelopedByPlayer(Player player){
        return ( getNumberOfHousesDevelopedByPlayer(player) + getNumberOfHotelsDevelopedByPlayer(player) );
        /* OLD, BUT RELIABLE CODE
        int out = 0;
        for (BoardLocation location : board)
            if( location.isOwned() )
               if( location.getOwner().equals(player) )
                   out += location.numberOfPropertiesBuilt();
        return out;
        */
     }
     
     //@146674
     public int getNumberOfLocationsOwnedByPlayer(Player player){
        int out = 0;
        for (BoardLocation location : board)
            if( location.isOwned() )
                if( location.getOwner().equals(player) )
                   out++;
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

     public void populateBoard() throws IOException{
         board[0] = new Instruction(0,"Go","landGO");
         board[1] = new Property(1,"Crapper Street",60, new int[]{2,10,30,90,160,250}, "brown");
         board[2] = new Instruction(2,"Pot Luck","doPLCard");
         board[3] = new Property(3,"Gangsters Paradise", 60, new int[]{4,20,60,180,320,450}, "brown");
         board[4] = new Instruction(4,"Income Tax","taxIncome");
         board[5] = new Property(5,"Brighton Station", 200, new int[]{25,50,100,200,200,200},"station");
         board[6] = new Property(6,"Weeping Angel", 100, new int[]{6, 30, 90, 270, 400, 550},"blue");
         board[7] = new Instruction(7,"Opportunity Knocks","doOKCard");
         board[8] = new Property(8,"Potts Avenue", 100, new int[]{6, 30, 90, 270, 400, 550},"blue");
         board[9] = new Property(9,"Nardole Drive", 120, new int[]{8, 40, 100, 300, 450, 600},"blue");
         board[10] = new Instruction(10,"Just Visiting","");
         board[11] = new Property(11,"Skywalker Drive", 140, new int[]{10, 50, 150, 450, 625, 750},"purple");
         board[12] = new Property(12,"Tesla PowerCo", 150, new int[]{0,0,0,0,0,0},"utilities");
         board[13] = new Property(13,"Wookie Hole ", 140, new int[]{10, 50, 150, 450, 625, 750},"purple");
         board[14] = new Property(14,"Rey Lane", 160, new int[]{12, 60, 180, 500, 700, 900},"purple");
         board[15] = new Property(15,"Hove Station", 200, new int[]{25,50,100,200,200,200},"station");
         board[16] = new Property(16,"Cooper Drive", 180, new int[]{14, 70, 200, 550, 750, 950},"orange");
         board[17] = new Instruction(17,"Pot Luck","doPLCard");
         board[18] = new Property(18,"Wolowitz Street", 180, new int[]{14, 70, 200, 550, 750, 950},"orange");
         board[19] = new Property(19,"Penny Lane", 200, new int[]{16, 80, 220, 600, 800, 1000},"orange");
         board[20] = new Instruction(20,"Free Parking","collectfines");
         board[21] = new Property(21,"Yue Fei Square", 220, new int[]{18, 90, 250, 700, 875, 1050},"red");
         board[22] = new Instruction(22,"Opportunity Knocks","doOKCard");
         board[23] = new Property(23,"Mulan Rouge", 220, new int[]{18, 90, 250, 700, 875, 1050},"red");
         board[24] = new Property(24,"Han Xin Gardens", 240, new int[]{20, 100, 300, 750, 925, 1100},"red");
         board[25] = new Property(25,"Falmer Station", 200, new int[]{25,50,100,200,200,200},"station");
         board[26] = new Property(26,"Kirk Close", 260, new int[]{22, 110, 330, 800, 975, 1150},"yellow");
         board[27] = new Property(27,"Picard", 260, new int[]{22, 110, 330, 800, 975, 1150},"yellow");
         board[28] = new Property(28,"Edison Water", 150, new int[]{0,0,0,0,0,0},"utilities");
         board[29] = new Property(29,"Crusher Creek", 280, new int[]{22, 120, 360, 850, 1025, 1200},"yellow");
         board[30] = new Instruction(30,"Go to Jail","Go to Jail");
         board[31] = new Property(31,"Sirat Mews", 300, new int[]{26, 130, 390, 900, 1100, 1275},"green");
         board[32] = new Property(32,"Ghengis Crescent", 300, new int[]{26, 130, 390, 900, 1100, 1275},"green");
         board[33] = new Instruction(33,"Pot Luck","doPLCard");
         board[34] = new Property(34,"Ibis Close", 320, new int[]{28, 150, 450, 1000, 1200, 1400},"green");
         board[35] = new Property(35,"Lewes Station", 200, new int[]{25,50,100,200,200,200},"station");
         board[36] = new Instruction(36,"Opportunity Knocks","doOKCard");
         board[37] = new Property(37,"Hawking Way", 350, new int[]{35, 175, 500, 1100, 1300, 1500},"deep blue");
         board[38] = new Instruction(38,"Super Tax","taxSuper");
         board[39] = new Property(39,"Turing Heights", 400, new int[]{50, 200, 600, 1400, 1700, 2000},"deep blue");
         board[40] = new Instruction(99,"Jail","jail_actions");
     }
     
}
