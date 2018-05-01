import java.io.IOException;

/**
 *
 * @author Kieran(132206)
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
     
     public int getPropertiesOwnedByPlayerUsingColour(Player player, String colourCheck){
         int out = 0;
         for (BoardLocation location : board)
             if( location.getColour().equals(colourCheck) && location.isOwned() )
                if( location.getOwner().equals(player) )
                    out++;
         return out;
     }

     public void populateBoard() throws IOException{
         board[0] = new BoardLocation(0,"Go",0, new int[]{0,0,0,0,0,0}, "", "Collect £200", false);
         board[1] = new BoardLocation(1,"Crapper Street",60, new int[]{2,10,30,90,160,250}, "brown", "", true);
         board[2] = new BoardLocation(2,"Pot Luck",0, new int[]{0,0,0,0,0,0}, "","Pick pot luck card", false);
         board[3] = new BoardLocation(3,"Gangsters Paradise", 60, new int[]{4,20,60,180,320,450}, "brown","", true);
         board[4] = new BoardLocation(4,"Income Tax", 0, new int[]{0,0,0,0,0,0},"","Pay £200", false);
         board[5] = new BoardLocation(5,"Brighton Station", 200, new int[]{25,50,100,200,200,200},"station","", true);
         board[6] = new BoardLocation(6,"Weeping Angel", 100, new int[]{6, 30, 90, 270, 400, 550},"blue","", true);
         board[7] = new BoardLocation(7,"Opportunity Knocks", 0, new int[]{0,0,0,0,0,0},"","Pick opportunity knocks card", false);
         board[8] = new BoardLocation(8,"Potts Avenue", 100, new int[]{6, 30, 90, 270, 400, 550},"blue","", true);
         board[9] = new BoardLocation(9,"Nardole Drive", 120, new int[]{8, 40, 100, 300, 450, 600},"blue","", true);
         board[10] = new BoardLocation(10,"Just Visiting", 0, new int[]{0,0,0,0,0,0},"","", false);
         board[11] = new BoardLocation(11,"Skywalker Drive", 140, new int[]{10, 50, 150, 450, 625, 750},"purple","", true);
         board[12] = new BoardLocation(12,"Tesla PowerCo", 150, new int[]{0,0,0,0,0,0},"utilities","", true);
         board[13] = new BoardLocation(13,"Wookie Hole ", 140, new int[]{10, 50, 150, 450, 625, 750},"purple","", true);
         board[14] = new BoardLocation(14,"Rey Lane", 160, new int[]{12, 60, 180, 500, 700, 900},"purple","", true);
         board[15] = new BoardLocation(15,"Hove Station", 200, new int[]{25,50,100,200,200,200},"station","", true);
         board[16] = new BoardLocation(16,"Cooper Drive", 180, new int[]{14, 70, 200, 550, 750, 950},"orange","", true);
         board[17] = new BoardLocation(17,"Pot Luck", 0, new int[]{0,0,0,0,0,0},"","Pick pot luck card", false);
         board[18] = new BoardLocation(18,"Wolowitz Street", 180, new int[]{14, 70, 200, 550, 750, 950},"orange","", true);
         board[19] = new BoardLocation(19,"Penny Lane", 200, new int[]{16, 80, 220, 600, 800, 1000},"orange","", true);
         board[20] = new BoardLocation(20,"Free Parking", 0, new int[]{0,0,0,0,0,0},"","Collect Fines", false);
         board[21] = new BoardLocation(21,"Yue Fei Square", 220, new int[]{18, 90, 250, 700, 875, 1050},"red","", true);
         board[22] = new BoardLocation(22,"Opportunity Knocks", 0, new int[]{0,0,0,0,0,0},"","Pick opportunity knocks card", false);
         board[23] = new BoardLocation(23,"Mulan Rouge", 220, new int[]{18, 90, 250, 700, 875, 1050},"red","", true);
         board[24] = new BoardLocation(24,"Han Xin Gardens", 240, new int[]{20, 100, 300, 750, 925, 1100},"red","", true);
         board[25] = new BoardLocation(25,"Falmer Station", 200, new int[]{25,50,100,200,200,200},"station","", true);
         board[26] = new BoardLocation(26,"Kirk Close", 260, new int[]{22, 110, 330, 800, 975, 1150},"yellow","", true);
         board[27] = new BoardLocation(27,"Picard", 260, new int[]{22, 110, 330, 800, 975, 1150},"yellow","", true);
         board[28] = new BoardLocation(28,"Edison Water", 150, new int[]{0,0,0,0,0,0},"utilities","", true);
         board[29] = new BoardLocation(29,"Crusher Creek", 280, new int[]{22, 120, 360, 850, 1025, 1200},"yellow","", true);
         board[30] = new BoardLocation(30," Go to Jail", 0, new int[]{0,0,0,0,0,0},"","Go to Jail", false);
         board[31] = new BoardLocation(31,"Sirat Mews", 300, new int[]{26, 130, 390, 900, 1100, 1275},"green","", true);
         board[32] = new BoardLocation(32,"Ghengis Crescent", 300, new int[]{26, 130, 390, 900, 1100, 1275},"green","", true);
         board[33] = new BoardLocation(33,"Pot Luck",0, new int[]{0,0,0,0,0,0},"","Pick pot luck card", false);
         board[34] = new BoardLocation(34,"Ibis Close", 320, new int[]{28, 150, 450, 1000, 1200, 1400},"green","", true);
         board[35] = new BoardLocation(35,"Lewes Station", 200, new int[]{25,50,100,200,200,200},"station","", true);
         board[36] = new BoardLocation(36,"Opportunity Knocks", 0, new int[]{0,0,0,0,0,0},"","Pick opportunity knocks card", false);
         board[37] = new BoardLocation(37,"Hawking Way", 350, new int[]{35, 175, 500, 1100, 1300, 1500},"deep blue","", true);
         board[38] = new BoardLocation(38,"Super Tax", 0, new int[]{0,0,0,0,0,0},"","Pay £100", false);
         board[39] = new BoardLocation(39,"Turing Heights", 400, new int[]{50, 200, 600, 1400, 1700, 2000},"deep blue","", true);
         board[40] = new BoardLocation(99,"Jail", 0, new int[]{0,0,0,0,0,0},"jail", "jail_actions", false);
     }
     
}
