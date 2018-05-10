import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */
public class GameMain{


    public static void main(String[] args) throws IOException {
        ImageIcon img = new ImageIcon("//res/logo.png");
        int totalPlayers = 0;
        ArrayList<Player> players = new ArrayList<>();
        int timer = 0;


        //choose game type
        JCheckBox checkBox1 = new JCheckBox("Abridged?");
        JCheckBox checkBox2 = new JCheckBox("Allow Trading?");
        Object[] sMessage = {"Please choose a game type:", checkBox1, checkBox2};
        int gameType = JOptionPane.showConfirmDialog(null, sMessage, "Game type selection.", JOptionPane.DEFAULT_OPTION);
        if (checkBox1.isSelected()){
            JTextField timerField = new JTextField();
            Object[] message = {"Please enter the length of the game (mins)",timerField};
            int n = JOptionPane.showConfirmDialog(null, message, "Game Length selection.", JOptionPane.DEFAULT_OPTION);
            timer = Integer.parseInt(timerField.getText());
        }

        boolean abridged = checkBox1.isSelected();
        boolean trading = checkBox2.isSelected();

        if(gameType == JOptionPane.CLOSED_OPTION) {

            System.exit(0);
        }

        //choose how many players
        String[] nOptions = {"2","3","4","5","6"};
        JComboBox noOfP = new JComboBox(nOptions);
        Object[] npMessage = {"Please choose number of players:", noOfP,};
        int pOptions = JOptionPane.showConfirmDialog(null, npMessage,"Number of players.",JOptionPane.DEFAULT_OPTION);

        totalPlayers = Integer.parseInt(noOfP.getSelectedItem().toString());

        if(pOptions == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }


        String[] cpuOptions = new String[]{"No","Yes"};
        ArrayList<String> tokenOptions = new ArrayList();
        tokenOptions.add("Boot");
        tokenOptions.add("Cat");
        tokenOptions.add("Goblet");
        tokenOptions.add("Hatstand");
        tokenOptions.add("Smartphone");
        tokenOptions.add("Spoon");

        JTextField pname = new JTextField();
        String[] toptions = new String[tokenOptions.size()];
        tokenOptions.toArray(toptions);

        JComboBox token = new JComboBox(toptions);
        JComboBox cpu = new JComboBox(cpuOptions);


        for (int i = 1; i <= totalPlayers; i++) {
            Object[] message = {"Enter player name:", pname, "Select a token:", token, "CPU Player?", cpu};
            Enum etoken = Token.NotInUse;
            Boolean cpuChoice;

            int option = JOptionPane.showConfirmDialog(null, message, "Player details.", JOptionPane.OK_CANCEL_OPTION);

            if((option == JOptionPane.CANCEL_OPTION)||(option == JOptionPane.CLOSED_OPTION)) {
                System.exit(0);
            }

            if (cpu.getSelectedItem().equals("Yes")) {
                cpuChoice = true;
            } else {
                cpuChoice = false;
            }

            if (option == 0) {
                if (token.getSelectedItem().equals("Boot")) {
                    etoken = Token.Boot;
                    players.add(new Player(i, pname.getText(), etoken, cpuChoice));
                } else if (token.getSelectedItem().equals("Hatstand")) {
                    etoken = Token.Hatstand;
                    players.add(new Player(i, pname.getText(), etoken, cpuChoice));
                } else if (token.getSelectedItem().equals("Spoon")) {
                    etoken = Token.Spoon;
                    players.add(new Player(i, pname.getText(), etoken, cpuChoice));
                } else if (token.getSelectedItem().equals("Goblet")) {
                    etoken = Token.Goblet;
                    players.add(new Player(i, pname.getText(), etoken, cpuChoice));
                } else if (token.getSelectedItem().equals("Cat")) {
                    etoken = Token.Cat;
                    players.add(new Player(i, pname.getText(), etoken, cpuChoice));
                } else if (token.getSelectedItem().equals("Smartphone")) {
                    etoken = Token.Smartphone;
                    players.add(new Player(i, pname.getText(), etoken, cpuChoice));
                }

                pname.setText("");
                token.removeItem(token.getSelectedItem());
            }

        }

        Game game = new Game(players, trading, abridged, timer); // MODEL (Data&Logic)

    }
}
