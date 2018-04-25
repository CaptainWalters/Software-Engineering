import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import static java.lang.System.in;
import java.util.Scanner;

/**
 *
 * @author Kieran
 */
public class GameMain {



    public static void main(String[] args){
        int totalPlayers = 0;
        int humanPlayers = 0;
        int CPUPlayers = 0;

        String[] options = new String[] {"Classic", "Abridged"};
        int gameType = JOptionPane.showOptionDialog(null, "Please choose a game type", "Game selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

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
            ClassicGame game = new ClassicGame(humanPlayers, CPUPlayers);
            game.initPlayers(humanPlayers);
        } else if(gameType == 1){
            AbridgedGame game = new AbridgedGame(humanPlayers, CPUPlayers);
        }

        JFrame f = new JFrame("Player creation");
        JPanel standardPanel = new JPanel();

        f.getContentPane().add(standardPanel);
        JTextField p1 = new JTextField();
    }

    }
