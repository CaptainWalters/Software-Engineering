/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sezuka
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
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


    public GUI() {
        // Load assets HERE
        this.Window = new JPanel();
        this.GOTextField = new JTextField("GO");

        // Leave this (adds components to main panel automatically)
        //for (JComponent comp : allComponents) {
        Window.add(GOTextField);
        //}
    }

    public JComponent getMainComponent() {
        return Window;
    }

    private static void createAndShowGui() {
        GUI gameFrame = new GUI();

        // creating my JFrame only when I need it and where I need it
        JFrame frame = new JFrame("Property Tycoon");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add( gameFrame.getMainComponent() );
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }


    /*public static void main(String[] args) {
        // Don't touch (main run)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }*/


//    public void init(){
//
//        int totalPlayers = 0;
//
//        String[] options = new String[] {"Classic", "Abridged"};
//        int gameType = JOptionPane.showOptionDialog(null, "Please choose a game type", "Game selection",
//                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
//                null, options, options[0]);
//
//
//        while((totalPlayers == 0)||(totalPlayers<=6)){
//            int noOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of human players. Total players = 6 (human + cpu)"));
//            totalPlayers += noOfPlayers;
//            int noOfCPU = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of CPU players. Total players = 6 (human + cpu)"));
//            totalPlayers += noOfCPU;
//        }
//
//        if(gameType.equals("Classic")){
//            ClassicGame game = new ClassicGame(humanPlayers, CPUPlayers);
//            game.initPlayers(humanPlayers);
//        } else if(gameType.equals("Abridged")){
//            AbridgedGame game = new AbridgedGame(humanPlayers, CPUPlayers);
//        }
//    }



}