import javax.swing.*;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


/**
 * Model = GameData&Logic, View = GUI, Controller = main()
 * MVC model where the CONTROLLER will pull data from MODEL and send the data to VIEW to render visually
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */
public class GameMain {
    // THIS WHOLE FILE IS THE CONTROLLER



    public static void main(String[] args) throws IOException {
        ImageIcon img = new ImageIcon("//res/logo.png");
        int totalPlayers = 0;
        int humanPlayers = 0;
        int CPUPlayers = 0;
        Player[] players;


        //choose game type
        String[] options = new String[] {"Classic", "Abridged"};
        int gameType = JOptionPane.showOptionDialog(null, "Please choose a game type", "Game type selection.",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                img, options, options[0]);

        if(gameType == -1){
            System.exit(0);
        }

        //choose how many players
        totalPlayers = Integer.parseInt((JOptionPane.showInputDialog(null, "Enter the total number of players. Max players = 6","Number of players.",JOptionPane.QUESTION_MESSAGE)));

        if(totalPlayers == -1){
            System.exit(0);
        }

        players = new Player[totalPlayers];
        String[] cpuoptions = new String[]{"No","Yes"};
        ArrayList<String> tokenoptions = new ArrayList();
        tokenoptions.add("Boot");
        tokenoptions.add("Cat");
        tokenoptions.add("Goblet");
        tokenoptions.add("Hatstand");
        tokenoptions.add("Smartphone");
        tokenoptions.add("Spoon");

        JTextField pname = new JTextField();
        String[] toptions = new String[tokenoptions.size()];
        tokenoptions.toArray(toptions);

        JComboBox token = new JComboBox(toptions);
        JComboBox cpu = new JComboBox(cpuoptions);
        //Object[] message = {"Enter player name:", pname,"Select a token:", token,"CPU Player?", cpu };

        for(int i = 1; i<=totalPlayers;i++){
            Object[] message = {"Enter player name:", pname,"Select a token:", token,"CPU Player?", cpu };
            Enum etoken = Token.NotInUse;
            Boolean cpuChoice;

            int option = JOptionPane.showConfirmDialog(null, message, "Player details.", JOptionPane.OK_CANCEL_OPTION);

            if(option == -1){
                System.exit(0);
            }

            if (option == JOptionPane.OK_OPTION) {
                if(token.getSelectedItem().equals("Boot"))
                    etoken = Token.Boot;
                } else if (token.getSelectedItem().equals("Hatstand")){
                    etoken = Token.Hatstand;
                } else if(token.getSelectedItem().equals("Spoon")) {
                    etoken = Token.Spoon;
                } else if (token.getSelectedItem().equals("Goblet")) {
                    etoken = Token.Goblet;
                } else if (token.getSelectedItem().equals("Cat")) {
                    etoken = Token.Cat;
                } else if (token.getSelectedItem().equals("Smartphone")){
                    etoken = Token.Smartphone;
                }

            if(cpu.getSelectedItem().equals("Yes")){
                cpuChoice = true;
            } else {
                cpuChoice = false;
            }

            players[i-1] = new Player(i,pname.getText(),etoken,cpuChoice);
            pname.setText("");
            token.removeItem(token.getSelectedItem());
        }

        if(gameType == 0){
            ClassicGame game = new ClassicGame(players); // MODEL (Data&Logic)
        } else if(gameType == 1){
            AbridgedGame game = new AbridgedGame(players); // MODEL (Data&Logic)
        }

        JFrame f = new JFrame("Player creation");
        JPanel standardPanel = new JPanel();

        f.getContentPane().add(standardPanel);
        JTextField p1 = new JTextField();
    }
}
