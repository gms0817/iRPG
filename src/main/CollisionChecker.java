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
        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow]; //check right  side of entity
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow]; //check right  side of entity
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; //check right  side of entity
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize; //predict where player will be based on speed to predict collision
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow]; //check left side of entity
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow]; //check right  side of entity
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) { //if true, prevent entity movement
                    entity.collisionOn = true;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player) { //check if the entity is the player
        int index = 999;

        for(int i = 0; i < gamePanel.objects.length; i++) {
            if(gamePanel.objects[i] != null) {
                //get entity collision box position
                entity.collisionBox.x = entity.worldX + entity.collisionBox.x;
                entity.collisionBox.y = entity.worldY + entity.collisionBox.y;

                //get object's collision box position
                gamePanel.objects[i].collisionBox.x = gamePanel.objects[i].worldX + gamePanel.objects[i].collisionBox.x;
                gamePanel.objects[i].collisionBox.y = gamePanel.objects[i].worldY + gamePanel.objects[i].collisionBox.y;

                switch(entity.direction) {
                    case "up":
                        entity.collisionBox.y -= entity.speed;
                        if(entity.collisionBox.intersects(gamePanel.objects[i].collisionBox)) { //check if entity and obj collision box intersect
                            if (gamePanel.objects[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }

                            break;
                        }
                    case "down":
                        entity.collisionBox.y += entity.speed;
                        if(entity.collisionBox.intersects(gamePanel.objects[i].collisionBox)) { //check if entity and obj collision box intersect
                            if (gamePanel.objects[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                            break;
                        }
                    case "left":
                        entity.collisionBox.x -= entity.speed;
                        if(entity.collisionBox.intersects(gamePanel.objects[i].collisionBox)) { //check if entity and obj collision box intersect
                            if (gamePanel.objects[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                            break;
                        }
                    case "right":

                        entity.collisionBox.x += entity.speed;
                        if(entity.collisionBox.intersects(gamePanel.objects[i].collisionBox)) { //check if entity and obj collision box intersect
                            if(gamePanel.objects[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        break;
                        }
                }
                entity.collisionBox.x = entity.collisionBoxDefaultX; //reset x
                entity.collisionBox.y = entity.collisionBoxDefaultY; //reset y
                gamePanel.objects[i].collisionBox.x = gamePanel.objects[i].collisionBoxDefaultX;
                gamePanel.objects[i].collisionBox.y = gamePanel.objects[i].collisionBoxDefaultY;
            }


        }
        return index;
    }
}
