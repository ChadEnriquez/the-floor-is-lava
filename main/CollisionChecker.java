package main;

import entity.Entity;
import entity.Player;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isOnLavaTile(Player player) {
        // Bounds of the player
        int playerLeftX = player.x + player.solidArea.x;
        int playerRightX = player.x + player.solidArea.x + player.solidArea.width;
        // int playerTopY = player.y + player.solidArea.y;
        int playerBottomY = player.y + player.solidArea.y + player.solidArea.height;

        int playerLeftCol = playerLeftX / gp.tileSize;
        int playerRightCol = playerRightX / gp.tileSize;
        // int playerTopRow = playerTopY / gp.tileSize;
        int playerBottomRow = playerBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        // Check the tile under the player
        tileNum1 = gp.tm.mapTileNum[playerLeftCol][playerBottomRow];
        tileNum2 = gp.tm.mapTileNum[playerRightCol][playerBottomRow];

        // If the player is on a lava tile, decrease the number of lives
        if (tileNum1 == 2 || tileNum2 == 2) {
            player.loseLife();
            if (player.getLives() == 0) {
                System.out.println("DEAD PLAYER");
            }
        }
        return false;
    }
    public void checkTile(Entity entity) {
        // Bounds of the entity
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                
                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
                
                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
                
                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
                
                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
