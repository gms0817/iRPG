package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //Check for collision with entity
    public void checkTile(Entity entity) {
        //get collision box coordinates
        int entityLeftWorldX = entity.worldX + entity.collisionBox.x;
        int entityRightWorldX = entity.worldX + entity.collisionBox.x + entity.collisionBox.width;
        int entityTopWorldY = entity.worldY + entity.collisionBox.y;
        int entityBottomWorldY = entity.worldY + entity.collisionBox.y + entity.collisionBox.height;

        //get entity rows and cols
        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2; //used to check for collision with tiles based on direction

        //check collision based on direction
        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow]; //check right  side of entity

                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow]; //check right  side of entity

                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; //check right  side of entity

                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow]; //check right  side of entity

                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
