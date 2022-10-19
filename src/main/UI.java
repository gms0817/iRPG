package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Font uiFont;
    BufferedImage keyImage;
    public boolean messageOn;
    public String message;
    int messageCounter = 0;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        uiFont = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(uiFont); //set the UI font
        graphics2D.setColor(Color.white);

        //Draw key tracker
        graphics2D.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawString(("x " + gamePanel.player.keyCount), 75, 65); //display number of keys

        if(messageOn) {
            graphics2D.drawString(message, 25 , gamePanel.screenHeight - 25);
            messageCounter++;
            if(messageCounter > 120) { //2 seconds at 60fps
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
