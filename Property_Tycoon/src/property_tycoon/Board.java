package property_tycoon;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.opencsv.CSVReader;
import java.util.List;

/**
 *
 * @author Kieran
 */
public class Board {

    BoardLocation[] board;
    
    public Board(){
        board = new BoardLocation[40];
    }
  
    public String getInfo (int position){
        return board[position].getInformation();
    }
    
    public void populateBoard() throws IOException{   
        board[0] = new BoardLocation(0,"Go",0, "", "Collect $200", false);
        board[1] = new BoardLocation(1,"Crapper Street",60,"Brown","", true);
        board[2] = new BoardLocation(2,"Pot Luck",0,"","Pick pot luck card", false);
    }
}