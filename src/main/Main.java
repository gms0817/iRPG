package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Setup the Window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when 'x' is clicked
        window.setResizable(false);
        window.setTitle("iRPG");
        window.setLocationRelativeTo(null); //display at center of screen

        //Setup Game Panel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); //sizes window to preferred size of game panel

        //Display main window
        window.setVisible(true); //display window

        //Setup game and place objects
        gamePanel.setupGame();

        //Start Game Loop
        gamePanel.startGameThread();
    }
}