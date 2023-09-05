package entity;

import main.*;

// Author Jerickson Mayor

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static main.Constants.PlayerConstants.*;
import static main.GamePanel.ORIGINALTILEHEIGHT;
import static main.GamePanel.TILESIZE;

public class Player extends Entity {
    private int playerAction = IDLEDOWN;
    private boolean enablePlayerCollisionBox = false;
    private int dieTick = 0;
    private int soundTick = 0;
    private boolean isDie = false;
    private int objectIndex = 0;
    private int splashCounter = 0;
    private final GamePanel gamePanel;
    private BufferedImage splashImage[];
    private int grapesPosX = 0;
    private int grapesPoxY = 0;
    private int numOfGrapes = 20;
    private int aniSplashTick;
    private int aniSplashIndex = 0;
    private int aniSplashSpeed = 20;
    private boolean doSplash = false;
    private boolean timerStart = false;


    public Player(GamePanel gamePanel, float x, float y) {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        initializeRectangle();
        setPlayerspeed();
        setlife();
        setDefaultDirection();
        loadAnimations();
        loadSplashAnimations();
    }

    public int getPlayerLife() {
        return currentlife;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public Rectangle getSolidArea() {
        return solidArea;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    @Override
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    @Override
    public void setlife() {
        maxLife = 6;
        currentlife = maxLife;
    }
    @Override
    public int getMaxlife() {
        return maxLife;
    }
    public void setDefaultDirection() {
        direction = "down";
    }

    public void setPlayerspeed() {
        speed = 1.0f;
    }

    public int getNumOfGrapes() {
        return numOfGrapes;
    }

    public void render(Graphics g) {
        if (doSplash) {
            g.drawImage(splashImage[aniSplashIndex], grapesPosX - TILESIZE , grapesPoxY - TILESIZE , 100, 98, null);
        }
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 50, 64, null);
        drawCollisionBox(g, enablePlayerCollisionBox);
    }

    public void drawCollisionBox(Graphics g, boolean isTrue) {
        if (isTrue) {
            g.setColor(Color.red);
            g.drawRect((int) x + solidArea.x, (int) y + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public void setCollision(boolean b) {
        isCollided = b;
    }

    public void update() {
        updatePos();
        splashAnimationTick();
        updateAnimationTick();
        setAnimation();
    }

    public void initializeRectangle() {
        solidArea = new Rectangle();
        solidArea.x = 7;
        solidAreaDefaultX = solidArea.x;
        solidArea.y = 30;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 29;
        solidArea.height = 30;
    }

    public void splashAnimationTick() {
        if (doSplash) {
            aniSplashTick++;
            if (aniSplashTick >= aniSplashSpeed) {
                aniSplashTick = 0;
                aniSplashIndex++;
                if (aniSplashIndex >= 4)
                    aniSplashIndex = 0;

            }
            splashCounter++;
            if (splashCounter == 50) {
                splashCounter = 0;
                doSplash = false;
            }
        }
    }

    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    public void setAnimation() {
        int startAni = playerAction;
        if (isMoving) {
            if (isDown) {
                playerAction = WALKINGDOWN;
            } else if (isUp) {
                playerAction = WALKINGUP;
            } else if (isLeft) {
                playerAction = WALKINGLEFT;
            } else if (isRight) {
                playerAction = WALKINGRIGHT;
            }
            if (startAni != playerAction) {
                resetAniTick();
            }
        } else {
            if (isDie == false && numOfGrapes != 0) {
                if (direction.equals("down")) {
                    playerAction = IDLEDOWN;
                } else if (direction.equals("up")) {
                    playerAction = IDLEUP;
                } else if (direction.equals("left")) {
                    playerAction = IDLELEFT;
                } else {
                    playerAction = IDLERiGHT;
                }

            } else {

                if (numOfGrapes != 0 || currentlife == 0) {
                    aniSpeed = 40;
                    playerAction = DIE;

                } else {
                    playerAction = WIN;
                }
            }
        }
    }

    public void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void pickObject(int index) {

        if (index != 999) {
            --numOfGrapes;
            try {
                doSplash = true;
                grapesPosX = gamePanel.getObject()[objectIndex].getXpos();
                grapesPoxY = gamePanel.getObject()[objectIndex].getYPos();
                gamePanel.playSfx(1);
                gamePanel.getObject()[index] = null;

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (numOfGrapes == 0) {
                gamePanel.playSfx(5);
                gamePanel.getUserInterface().setGameFinished(true);
            }

        }
    }

    public void resetDefaultAniSpeed() {
        aniSpeed = 15;
    }

    public void updatePos() {
        // CheckTileCollision
        isCollided = false;
        gamePanel.getCollisionSystem().checkTile(this);
        // CheckObjectCollision
        objectIndex = gamePanel.getCollisionSystem().checkObject(this, true);
        pickObject(objectIndex);

        if (isCollided != true) {
            if (isUp) {
                y -= speed;
                isMoving = true;
                timerStart = true;
            } else if (isDown) {
                y += speed;
                isMoving = true;
                timerStart = true;
            }
            if (isRight) {
                x += speed;
                isMoving = true;
                timerStart = true;
            } else if (isLeft) {
                x -= speed;
                isMoving = true;
                timerStart = true;
            }
        } else if (numOfGrapes != 0) {

            if (isCollided && objectIndex == 999) {
                setMoving(false);
                isDie = true;
                soundTick++;
                if (soundTick == 1) {
                    gamePanel.playSfx(0);
                }
                dieTick++;
                if (dieTick == 350) {
                    dieTick = 0;
                    currentlife--;
                    resetDefaultAniSpeed();
                    setDirection("down");
                    resetDirBoolean();
                    setPosition(88, 90);
                    isCollided = false;
                    isDie = false;
                    soundTick = 0;
                    setMoving(false);
                    if (currentlife == 0) {
                        gamePanel.stopMusic();
                        gamePanel.playSfx(3);
                        gamePanel.getUserInterface().setGameOver(true);
                        setMoving(false);
                    }
                }
            }
        } else {
            gamePanel.stopMusic();
            setMoving(false);
            isCollided = true;

        }
    }
    public boolean getTimerSignal() {
        return timerStart;
    }
    public boolean isCollided() {
        return isCollided;
    }

    public void setUp(boolean b) {
        this.isUp = b;
        this.isLeft = false;
        this.isRight = false;
        this.isDown = false;
    }

    public void setDown(boolean b) {
        this.isDown = b;
        this.isUp = false;
        this.isLeft = false;
        this.isRight = false;
    }

    public void setLeft(boolean b) {
        this.isLeft = b;
        this.isUp = false;
        this.isRight = false;
        this.isDown = false;
    }

    public void setRight(boolean b) {
        this.isRight = b;
        this.isUp = false;
        this.isLeft = false;
        this.isDown = false;
    }

    public void resetDirBoolean() {
        this.isLeft = false;
        this.isRight = false;
        this.isDown = false;
        this.isUp = false;
    }

    public void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction))
                aniIndex = 0;
        }

    }
    private void loadAnimations() {

        InputStream is = getClass().getResourceAsStream("/sprites/player_spritesheet.png");
        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[10][15];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 50, j * 64, 50, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void loadSplashAnimations() {
        InputStream is = getClass().getResourceAsStream("/sprites/splash_sheet.png");
        try {
            BufferedImage img = ImageIO.read(is);
            splashImage = new BufferedImage[4];
                for (int i = 0; i < splashImage.length; i++) {
                    splashImage[i] = img.getSubimage(i * 100, 0 * 98, 100, 98);
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
