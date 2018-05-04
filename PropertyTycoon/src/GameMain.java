import javax.swing.*;
import java.io.IOException;

/**
 * Model = GameData&Logic, View = GUI, Controller = main()
 * MVC model where the CONTROLLER will pull data from MODEL and send the data to VIEW to render visually
 * @author Kieran(132206)
 *
 */
public class GameMain {
    // THIS WHOLE FILE IS THE CONTROLLER

    public static void main(String[] args) throws IOException {
        int totalPlayers = 0;
        int humanPlayers = 0;
        int CPUPlayers = 0;


        //choose game type
        String[] options = new String[] {"Classic", "Abridged"};
        int gameType = JOptionPane.showOptionDialog(null, "Please choose a game type", "Game selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        //choose how many players
        while((totalPlayers == 0)||(totalPlayers>6)){
            totalPlayers = 0;
            int noOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of human players. Total players = 6 (human + cpu)"));
            humanPlayers = noOfPlayers;
            totalPlayers += noOfPlayers;
            int noOfCPU = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of CPU players. Total players = 6 (human + cpu)"));
            CPUPlayers = noOfCPU;
            totalPlayers += noOfCPU;
        }







        if(gameType == 0){
            ClassicGame game = new ClassicGame(totalPlayers); // MODEL (Data&Logic)

        } else if(gameType == 1){
            AbridgedGame game = new AbridgedGame(humanPlayers, CPUPlayers); // MODEL (Data&Logic)
        }

        JFrame f = new JFrame("Player creation");
        JPanel standardPanel = new JPanel();

        f.getContentPane().add(standardPanel);
        JTextField p1 = new JTextField();
    }
}
