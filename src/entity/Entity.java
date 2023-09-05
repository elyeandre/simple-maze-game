package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected float             x;
    protected float             y;
    protected boolean           isCollided = false;
    protected float             speed;
    protected String            direction;
    protected Rectangle         solidArea;
    protected boolean           isMoving;
    protected boolean           isLeft;
    protected boolean           isRight;
    protected boolean           isDown;
    protected boolean              isUp;
    protected BufferedImage[][] animations;
    protected int               aniTick;
    protected int               aniIndex = 0;
    protected int               aniSpeed = 20;
    public int                  solidAreaDefaultX;
    public int                  solidAreaDefaultY;

    protected int                  maxLife;
    protected int                  currentlife;
    public abstract int getY();
    public abstract int getX();
    public abstract String getDirection();
    public abstract void setCollision(boolean b);
    public abstract void setDirection(String direction);
    public abstract float getSpeed();
    public abstract Rectangle getSolidArea();
    public abstract void setPosition(int x, int y);
    public abstract int getSolidAreaDefaultX();
    public abstract int getSolidAreaDefaultY();
    public abstract void setlife();
    public abstract int getMaxlife();

}
