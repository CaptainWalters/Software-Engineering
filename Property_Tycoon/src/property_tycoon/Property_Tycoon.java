package property_tycoon;

import java.io.IOException;

/**
 *
 * @author Kieran
 */
public class Property_Tycoon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Board test = new Board();
        test.populateBoard();
        System.out.println(test.getInfo(0));
        System.out.println(test.getInfo(1));
        System.out.println(test.getInfo(2));
    }
    
}
