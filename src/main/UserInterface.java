package main;

import objects.Heart;
import objects.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.RenderingHints;

import static main.GamePanel.*;

// Author Jerickson Mayor
public class UserInterface {
    private final GamePanel         gamePanel;
    private Font                    font;
    private BufferedImage           panel;
    private BufferedImage           grapeImage;
    private BufferedImage           youWin;
    private BufferedImage           gameOver;
    private BufferedImage[]         clockAnim;
    private boolean                 isGameFinished = false;
    private boolean                 isGameOver = false;
    private final int               x = SCREENMAXWIDTH / 2 - 192 / 2;
    private final int               y = SCREENMAXHEIGHT / 2 - 133 / 2;
    private int                     elapsedTime = 0;
    private int                     seconds = 0;
    private int                     minutes = 0;
    private int                     hours = 0;
    private String                  secondsString = String.format("%02d", seconds);
    private String                  minutesString = String.format("%02d", minutes);
    private int                     aniClockTick;
    private int                     aniClockIndex = 0;
    private int                     aniClockSpeed = 40;
    private long                    timer;
    private long                    blinkTimer;
    private boolean                 isBlinkingOn = false;
    private SuperObject             heart;
    private String                  os; 

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        ImportFont();
        ImportImages();
        heart = new Heart();
	os = System.getProperty("os.name").toLowerCase();
    }

    public void ImportFont() {
        InputStream is = getClass().getResourceAsStream("/font/tinypixel.otf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,is);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void ImportImages() {
        InputStream is1 = getClass().getResourceAsStream("/sprites/clock_sheet.png");
        InputStream is2 = getClass().getResourceAsStream("/sprites/panel.png");
        InputStream is3 = getClass().getResourceAsStream("/sprites/grapes_hud.png");
        InputStream is4 = getClass().getResourceAsStream("/sprites/you_win.png");
        InputStream is5 = getClass().getResourceAsStream("/sprites/game_over.png");

        try {
            BufferedImage img = ImageIO.read(is1);
            clockAnim = new BufferedImage[15];
            for (int i = 0; i < clockAnim.length; i++) {
                clockAnim[i] = img.getSubimage(i * 34, 0 * 34, 34, 34);
            }
             panel = ImageIO.read(is2);
             grapeImage =  ImageIO.read(is3);
             youWin = ImageIO.read(is4);
             gameOver = ImageIO.read(is5);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is1.close();
                is2.close();
                is3.close();
                is4.close();
                is5.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void panel(Graphics g) {
        g.drawImage(panel, (TILESIZE * 3) - 29,(TILESIZE * 16) - 6,null);
    }
    public void TimerUpdate() {
        if (System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
//            hoursString = String.format("%02d", hours);
            }
        }
    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
    public void setGameFinished(boolean isGameFinished) {
        this.isGameFinished = isGameFinished;
    }
    public void updateBlink() {
        if (System.currentTimeMillis() - blinkTimer  > 250) {
            blinkTimer += 250;
            isBlinkingOn = !isBlinkingOn;
        }
    }
    public void update() {
        if(!gamePanel.getPlayer().getTimerSignal()
                || gamePanel.getPlayer().getNumOfGrapes() == 0
                || gamePanel.getPlayer().getPlayerLife() == 0) {
            timer = System.currentTimeMillis();
        }
        TimerUpdate();
        updateClockAnimTick();

         if(!isGameFinished && !isGameOver) {
            blinkTimer = System.currentTimeMillis();
        }
        updateBlink();
    }
    public void updateClockAnimTick() {
        if(gamePanel.getPlayer().getTimerSignal()
                && gamePanel.getPlayer().getNumOfGrapes() != 0
                && gamePanel.getPlayer().getPlayerLife() != 0) {
            aniClockTick++;
            if (aniClockTick >= aniClockSpeed) {
                aniClockTick = 0;
                aniClockIndex++;
                if (aniClockIndex >= 15)
                    aniClockIndex = 0;
            }
        }
    }
    public void render(Graphics g) {
	// Check the operating system
	boolean isLinux = os.contains("linux");
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font.deriveFont(isLinux ? 20f: 15f));
        // Draw our panel
        panel(g);
        // Draw grapes indicator
        g2.drawImage(grapeImage, (TILESIZE * 8) + 25, (TILESIZE * 16) + 17, 32, 36, null);
        // Enhancing the edges of the text
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setColor(Color.black);
        g2.drawString("x " + String.valueOf(gamePanel.getPlayer().getNumOfGrapes()), (TILESIZE * 8) + 68, (TILESIZE * 16) + 42);
        // Draw our clock
        g2.drawImage(clockAnim[aniClockIndex], 450, (TILESIZE * 16) + 18, null);
        // Draw Player Max Life
        int i = 0;
        int xpos = TILESIZE * 4 + 9; // 144
        int ypos = TILESIZE * 16 + 18; // 594
        while (i < gamePanel.getPlayer().getMaxlife() / 2) {
            g2.drawImage(heart.getHeartImage(2), xpos, ypos,null );
            i++;
            xpos += TILESIZE;
        }
        // Reset
        xpos = TILESIZE * 4 + 9;
        i = 0;

        // Draw Player Current Life
        while (i < gamePanel.getPlayer().getPlayerLife()) {
            g2.drawImage(heart.getHeartImage(1), xpos ,ypos ,null);
            i++;
            if(i < gamePanel.getPlayer().getPlayerLife()) {
                g2.drawImage(heart.getHeartImage(0), xpos, ypos, null);
            }
            i++;
            xpos += TILESIZE;
        }

        // Draw our timer
        if(!isBlinkingOn) {
            g2.setColor(Color.black);
        }
        else {
            g2.setColor(new Color(248, 242, 226));

        }
        g2.drawString(minutesString+":"+secondsString,500,(TILESIZE * 16) + 42);

          if(isGameFinished) {
              g.drawImage(youWin, x, y, null);

          }
          else if(isGameOver) {
              g.drawImage(gameOver, x,y,null);
          }

    }

}
