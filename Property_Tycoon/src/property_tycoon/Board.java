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

    BoardLocation[] loc;
    
    public Board(){
        loc = new BoardLocation[40];
    }
  
    public String getInfo (int position){
        return loc[position].getInformation();
    }
    
    public void populateBoard() throws IOException{   
        loc[0] = new BoardLocation(0,"Go",0, "", "Collect $200", false);
        loc[1] = new BoardLocation(1,"Crapper Street",60,"Brown","", true);
        loc[2] = new BoardLocation(2,"Pot Luck",0,"","Pick pot luck card", false);
    }
}