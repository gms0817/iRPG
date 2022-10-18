package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    //Screen-Coordinates
    public final int screenX;
    public final int screenY;

    //Keep track of objects
    int keyCount = 0;

    //Create Player
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        //Center player on screen
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        //Instantiate collisionBox
        collisionBox = new Rectangle();        //x, y, width, height
        collisionBox.x = 10;
        collisionBox.y = 22;
        collisionBoxDefaultX = collisionBox.x;
        collisionBoxDefaultY = collisionBox.y;
        collisionBox.width = 28;
        collisionBox.height = 28;

        setDefaultValues();
        getPlayerImage();
    }

    //Set defaults
    public void setDefaultValues() {
        //set player initial spawn
        worldX = gamePanel.tileSize * 18; //player position on world map not screen
        worldY = gamePanel.tileSize * 24; //player position on world map not screen
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if(keyHandler.upPressed) {
                direction = "up";
            }

            else if(keyHandler.downPressed) {
                direction = "down";
            }

            else if(keyHandler.leftPressed) {
                direction = "left";
            }

            else if(keyHandler.rightPressed) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false; //reset default collision value;
            gamePanel.collisionChecker.checkTile(this); //check for collision with the player

            //Check object collision
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objIndex);

            //if false, player can move
            if(!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }


        }
        //Used to swap sprites every twelve frames so simulate walking
        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNumber == 1)
                spriteNumber = 2;
            else if(spriteNumber == 2)
                spriteNumber = 1;
            spriteCounter = 0; //reset counter
        }

    }

    public void pickupObject(int index) {
        if(index != 999) {
            String objectName = gamePanel.objects[index].name;
            switch(objectName) { //object specific actions
                case "key":
                    gamePanel.playSoundEffect(3);
                    keyCount++;
                    gamePanel.objects[index] = null;
                    break;
                case "door":
                    if(keyCount > 0) {
                        gamePanel.playSoundEffect(2);
                        gamePanel.objects[index] = null; //remove door
                        keyCount--;
                    }
                    break;
                case "speedBoots":
                    gamePanel.playSoundEffect(1);
                    speed += 2;
                    gamePanel.objects[index] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D graphic2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1)
                    image = up1;
                if (spriteNumber == 2)
                    image = up2;
            }
            case "down" -> {
                if (spriteNumber == 1)
                    image = down1;
                if (spriteNumber == 2)
                    image = down2;
            }
            case "left" -> {
                if (spriteNumber == 1)
                    image = left1;
                if (spriteNumber == 2)
                    image = left2;
            }
            case "right" -> {
                if (spriteNumber == 1)
                    image = right1;
                if (spriteNumber == 2)
                    image = right2;
            }
        }

        graphic2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
