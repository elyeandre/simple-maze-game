package objects;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import static main.GamePanel.ORIGINALTILEHEIGHT;
import static main.GamePanel.TILESIZE;
public class SuperObject {

    private BufferedImage[]         animations;
    private boolean                 collisionOn = false;
    private int                     xPos;
    private int                     yPos;
    public Rectangle                solidArea = new Rectangle(0,0,TILESIZE,ORIGINALTILEHEIGHT);
    private int                     solidAreaDefaultX = 0;
    private int                     solidAreaDefaultY = 0;
    private boolean                 collionBoxEnabled = false;
    private int                     aniTick;
    private int                     aniSpeed = 20;
    private int                     aniIndex = 0;
    private GamePanel               gamePanel;
    public void render(Graphics g, GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        g.drawImage(animations[aniIndex], xPos ,yPos, TILESIZE, ORIGINALTILEHEIGHT, null);
        g.setColor(Color.red);
        if(collionBoxEnabled) {
        g.drawRect(xPos + solidArea.x, yPos + solidArea.y, solidArea.width, solidArea.height);
        }
    }
    public void update() {
        updateAnimationTick();
    }
    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 5)
                aniIndex = 0;
        }
    }
    public void setAnimations(BufferedImage[] animations) {
        this.animations = animations;
    }
    public void setCollisionOn(boolean b) {
        this.collisionOn = b;
    }
    public boolean getCollision() {
        return collisionOn;
    }
    public int getXpos() {
        return  xPos;
    }
    public int getYPos() {
        return yPos;
    }
    public void setXpos(int xpos) {
        this.xPos = xpos;
    }
    public void setYpos(int ypos) {
        this.yPos = ypos;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public BufferedImage getHeartImage(int index) {
        return animations[index];
    }

}
