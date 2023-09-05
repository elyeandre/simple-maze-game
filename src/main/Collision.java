package main;

import entity.Entity;
import static main.GamePanel.TILESIZE;

// Author Jerickson Mayor
public class Collision  {
GamePanel gamePanel;
    public Collision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }
    public void checkTile(Entity entity) {

        int entityLeftPlayerX = entity.getX() + entity.getSolidArea().x;
        int entityRightPlayerX = entity.getX() + entity.getSolidArea().x +  entity.getSolidArea().width;
        int entityTopPlayerY = entity.getY() + entity.getSolidArea().y;
        int entityBottomPlayerY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftPlayerX/TILESIZE;
        int entityRightCol = entityRightPlayerX/TILESIZE;
        int entityTopRow =  entityTopPlayerY/TILESIZE;
        int entityBottomRow = entityBottomPlayerY/TILESIZE;

        int tileNum1;
        int tileNum2;

        switch(entity.getDirection()) {
            case "up":
                entityTopRow = (int) (entityTopPlayerY - entity.getSpeed()) / TILESIZE;
                tileNum1 = gamePanel.getTileManager().getMapTile(entityLeftCol,entityTopRow);
                tileNum2 = gamePanel.getTileManager().getMapTile(entityRightCol,entityTopRow);
                if (gamePanel.getTileManager().getTile(tileNum1).collisionOn || gamePanel.getTileManager().getTile(tileNum2).collisionOn) {
                    entity.setCollision(true);
                }
                break;
            case "down":
                entityBottomRow = (int) (entityBottomPlayerY + entity.getSpeed()) / TILESIZE;
                tileNum1 = gamePanel.getTileManager().getMapTile(entityLeftCol,entityBottomRow);
                tileNum2 = gamePanel.getTileManager().getMapTile(entityRightCol,entityBottomRow);
                if (gamePanel.getTileManager().getTile(tileNum1).collisionOn || gamePanel.getTileManager().getTile(tileNum2).collisionOn) {
                    entity.setCollision(true);
                }
                break;
            case "left":
                entityLeftCol = (int) (entityLeftPlayerX - entity.getSpeed()) / TILESIZE;
                tileNum1 = gamePanel.getTileManager().getMapTile(entityLeftCol,entityTopRow);
                tileNum2 = gamePanel.getTileManager().getMapTile(entityLeftCol,entityBottomRow);
                if (gamePanel.getTileManager().getTile(tileNum1).collisionOn || gamePanel.getTileManager().getTile(tileNum2).collisionOn) {
                    entity.setCollision(true);
                }
                break;
            case "right":
                entityRightCol = (int) (entityRightPlayerX + entity.getSpeed()) / TILESIZE;
                tileNum1 = gamePanel.getTileManager().getMapTile(entityRightCol,entityTopRow);
                tileNum2 = gamePanel.getTileManager().getMapTile(entityRightCol,entityBottomRow);
                if (gamePanel.getTileManager().getTile(tileNum1).collisionOn || gamePanel.getTileManager().getTile(tileNum2).collisionOn) {
                    entity.setCollision(true);
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player) {
       int index = 999;

        for (int i = 0; i < gamePanel.getObject().length; i++) {
            if(gamePanel.getObject()[i] != null) {
                //Getting entity solid area;
                entity.getSolidArea().x = entity.getX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getY() + entity.getSolidArea().y;
                //Getting the object solid area;
                gamePanel.getObject()[i].solidArea.x = gamePanel.getObject()[i].getXpos() + gamePanel.getObject()[i].solidArea.x;
                gamePanel.getObject()[i].solidArea.y = gamePanel.getObject()[i].getYPos() + gamePanel.getObject()[i].solidArea.y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gamePanel.getObject()[i].solidArea)) {
                            if(gamePanel.getObject()[i].getCollision()) {
                                entity.setCollision(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gamePanel.getObject()[i].solidArea)) {
                            if(gamePanel.getObject()[i].getCollision()) {
                                entity.setCollision(true);

                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gamePanel.getObject()[i].solidArea)) {
                            if(gamePanel.getObject()[i].getCollision()) {
                                entity.setCollision(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gamePanel.getObject()[i].solidArea)) {
                            if(gamePanel.getObject()[i].getCollision()) {
                                entity.setCollision(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gamePanel.getObject()[i].solidArea.x = gamePanel.getObject()[i].getSolidAreaDefaultX();
                gamePanel.getObject()[i].solidArea.y = gamePanel.getObject()[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

}


