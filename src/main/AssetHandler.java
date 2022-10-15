package main;

import main.object.OBJ_Chest;
import main.object.OBJ_Key;

public class AssetHandler {
    GamePanel gamePanel;

    public AssetHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //instantiate object and place onto map
    public void setObject() {
        //Starter Chest Key
        gamePanel.objects[0] = new OBJ_Key(); //create new key object
        gamePanel.objects[0].worldX = gamePanel.tileSize * 22;
        gamePanel.objects[0].worldY = gamePanel.tileSize * 24;

        //Starter Chest
        gamePanel.objects[1] = new OBJ_Chest();
        gamePanel.objects[1].worldX = gamePanel.tileSize * 6;
        gamePanel.objects[1].worldY = gamePanel.tileSize * 20;
    }
}
