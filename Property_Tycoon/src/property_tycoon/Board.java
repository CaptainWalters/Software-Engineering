package property_tycoon;

/**
 *
 * @author Kieran
 */
public class Board {

    BoardLocation[] loc = new BoardLocation[40];
    
    public String getInfo (int position){
        return loc[position].getInformation();
    }
    
}
