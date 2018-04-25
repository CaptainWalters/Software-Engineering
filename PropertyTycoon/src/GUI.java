import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends Frame{
    private JTextField CrapperStreetName;
    private JTextField GOTextField;
    private JPanel Window;
    private JPanel BoardMargin;
    private JPanel Board;
    private JPanel Centre;
    private JPanel GoMargin;
    private JPanel Go;
    private JPanel SideOne;
    private JPanel SideTwo;
    private JPanel SideThree;
    private JPanel SideFour;
    private JPanel CentreMargin;
    private JPanel GoToJailMargin;
    private JPanel FreeParkingMargin;
    private JPanel JailMargin;
    private JPanel GoToJail;
    private JPanel TuringHeights;
    private JPanel SuperTax;
    private JPanel HawkingWay;
    private JPanel OkThree;
    private JPanel LewesStation;
    private JPanel IbisClose;
    private JPanel PLThree;
    private JPanel GhengisCrescent;
    private JPanel SiratMews;
    private JPanel SideFourProperties;
    private JPanel SideThreeProperties;
    private JPanel SideTwoProperties;
    private JPanel PennyLane;
    private JPanel CrapperStreet;
    private JPanel PL1;
    private JPanel GangstersParadise;
    private JPanel IncomeTax;
    private JPanel BrightonStation;
    private JPanel WeepingAngel;
    private JPanel OKOne;
    private JPanel PottsAvenue;
    private JPanel NardoleDrive;
    private JPanel PLDeck;
    private JPanel OkDeck;
    private JPanel FreeParking;
    private JPanel SideOneProperties;
    private JPanel NardoleDriveOwner;
    private JPanel PottsAvenueOwner;
    private JPanel WeepingAngelOwner;
    private JPanel WeepignAngelColour;
    private JPanel WeepingAngelName;
    private JPanel CrapperStreetColour;
    private JPanel CrapperStreetOwner;
    private JPanel CrapperStreetContainer;
    private JPanel CrapperStreetInfo;
    private JPanel CrapperStreetPieces;
    private JPanel CrapperStreetHouseHotel;
    private JPanel CrapperStreetCat;
    private JPanel CrapperStreetBoot;
    private JPanel CrapperStreetPhone;
    private JPanel CrapperStreetHat;
    private JPanel CrapperStreetSpoon;
    private JPanel CrapperStreetGoblet;
    private JPanel EdisonWater;

//    private ArrayList<SmartPhone> SmartPhone;
//    private ArrayList<Goblet> Goblet;
//    private ArrayList<Cat> Cat;
//    private ArrayList<HatStand> HatStand;
//    private ArrayList<Spoon> Spoon;
//    private ArrayList<Boot> Boot;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Property Tycoon");
        frame.setContentPane(new GUI().Window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }


}
