package main;

import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_SpeedBoots;

public class AssetHandler {
    GamePanel gamePanel;

    public AssetHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //instantiate object and place onto map
    public void setObject() {
        //Key to Town 1 Door
        gamePanel.objects[0] = new OBJ_Key(); //create new key object
        gamePanel.objects[0].worldX = gamePanel.tileSize * 22;
        gamePanel.objects[0].worldY = gamePanel.tileSize * 24;

        //Town 1 Door
        gamePanel.objects[1] = new OBJ_Door();
        gamePanel.objects[1].worldX = gamePanel.tileSize * 2;
        gamePanel.objects[1].worldY = gamePanel.tileSize * 14;

        //Speed Boots
        gamePanel.objects[2] = new OBJ_SpeedBoots();
        gamePanel.objects[2].worldX = gamePanel.tileSize * 27;
        gamePanel.objects[2].worldY = gamePanel.tileSize * 15;
    }
}
