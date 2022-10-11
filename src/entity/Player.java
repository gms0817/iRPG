package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    //Create Player
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
    }

    //Set defaults
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if(keyHandler.upPressed == true) {
            y -= speed;
        }

        else if(keyHandler.downPressed == true) {
            y += speed;
        }

        else if(keyHandler.leftPressed == true) {
            x -= speed;
        }

        else if(keyHandler.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D graphic2) {
        //Set color for drawing graphics
        graphic2.setColor(Color.white);
        graphic2.fillRect(x,y, graphic2.tileSize, graphic2.titleSize); //rectangle size of character
    }
}
