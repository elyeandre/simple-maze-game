package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static main.GamePanel.TILESIZE;
import static main.GamePanel.ORIGINALTILEHEIGHT;
import static main.GamePanel.MAXSCREENCOL;
import static main.GamePanel.MAXSCREENROW;

// Author Jerickson Mayor
public class TileManager {
    private final int[][] mapTile;
    private BufferedImage[][] tileImages;
    private BufferedImage[] crackAnim;
    private BufferedImage   shadow1;
    private  BufferedImage  shadow2;
    public Tile[]           tile;
    private boolean         showGrid = false;
    private boolean         showTileData = false;
    private int             aniTick;
    private int             aniSpeed = 20;
    private int             aniIndex = 0;
    private int             count = 0;
    private int             crackTick = 0;
    private boolean         doAnimation = true;
    private final           GamePanel gamePanel;
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mapTile = new int[MAXSCREENCOL][MAXSCREENROW];
        tile = new Tile[10];
        geTileMap();
        importTileImage();
        loadTileImage();
        importCrackAnim();
        importShadowImage();
    }
    private void geTileMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/tilemap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < MAXSCREENCOL && row < MAXSCREENROW) {
                String line = br.readLine();
                while(col < MAXSCREENCOL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTile[col][row] = num;
                    col++;
                }
                if (col == MAXSCREENCOL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void importTileImage() {
        InputStream is = getClass().getResourceAsStream("/tiles/tile_sheet.png");
        try {
            BufferedImage img = ImageIO.read(is);
            tileImages = new BufferedImage[5][4];
            for (int j = 0; j < tileImages.length; j++) {
                for (int i = 0; i < tileImages[j].length; i++) {
                    tileImages[j][i] = img.getSubimage(i * TILESIZE, j * 52, TILESIZE, 52);
                }
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
    private void importShadowImage() {
        InputStream is = getClass().getResourceAsStream("/tiles/shadow_1.png");
        InputStream is2 = getClass().getResourceAsStream("/tiles/shadow_2.png");
        try {
            shadow1 = ImageIO.read(is);
            shadow2  = ImageIO.read(is2);

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
    private void importCrackAnim() {
        InputStream is = getClass().getResourceAsStream("/tiles/wall_crack_sheet.png");
        try {
            BufferedImage img = ImageIO.read(is);
            crackAnim = new BufferedImage[10];
            for (int j = 0; j < crackAnim.length; j++) {
                    crackAnim[j] = img.getSubimage(j * 48, 0 * 64, 48, 64);
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
    private void renderTileData(Graphics g, String text, Font f, int x, int y, boolean enable) {
        if(enable) {
            g.setColor(Color.black);
            FontMetrics metrics = g.getFontMetrics(f);
            int xpos = x + (TILESIZE - metrics.stringWidth(text)) / 2;
            int ypos = y + ((ORIGINALTILEHEIGHT - metrics.getHeight())/ 2) + metrics.getAscent();
            g.setFont(f);
            g.drawString(text, xpos, ypos);
        }
    }
    private void renderGrid(Graphics g, int x, int y, boolean enable) {
        if (enable) {
            g.setColor(Color.black);
            g.drawRect(x, y, TILESIZE, ORIGINALTILEHEIGHT);
        }
    }
    public void drawShadow(Graphics g) {
        for (int row = 0; row < mapTile.length ; row++) {
                for (int col = 0; col < mapTile[row].length; col++) {
                if(row == 1 ) {
                    g.drawImage(shadow2,TILESIZE * col - 40,TILESIZE * row + 11,44,50,null);
                }
                if(gamePanel.getPlayer().getNumOfGrapes() != 1 && gamePanel.getPlayer().getNumOfGrapes() != 0) {
                    if (mapTile[row][col] != 0 && mapTile[row][col] == 8 || mapTile[row][col] == 9 && row != 1 && col != 1 && col != 18)
                        g.drawImage(shadow1, TILESIZE * row, TILESIZE * col + 11, 36, 50, null);
                }
                else {
                    if (gamePanel.getPlayer().getNumOfGrapes() == 1) {
                        if (mapTile[row][col] != 0 && mapTile[row][col] == 8 && row != 1 && col != 1 && col != 18)
                            g.drawImage(shadow1, TILESIZE * row, TILESIZE * col + 11, 36, 50, null);
                    } else {
                        if (gamePanel.getPlayer().getNumOfGrapes() == 0) {
                            if (mapTile[row][col] != 0 && mapTile[row][col] == 8 && mapTile[row][col] != 9 && row != 1 && col != 1 && col != 18)
                                g.drawImage(shadow1, TILESIZE * row, TILESIZE * col + 11, 36, 50, null);
                        }
                    }

                }

            }
        }
    }
    public void update() {
        updateAnimationTick();
    }
    public void updateAnimationTick() {

        aniTick++;
        if(gamePanel.getPlayer().getNumOfGrapes() == 1) {
            if(doAnimation)
            if(aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                crackTick++;
                if(aniIndex == 8)
                    aniIndex = 0;
                if(crackTick == 1)
                    gamePanel.playSfx(6);
            }
            count++;
            if(count == 120 ) {
                doAnimation = false;
            }

        }

    }
    private void loadTileImage() {

        tile[0] = new Tile();
        tile[1] = new Tile();
        tile[1].image = tileImages[1][0];
        tile[2] = new Tile();
        tile[2].image = tileImages[1][1];
        tile[3] = new Tile();
        tile[3].image = tileImages[1][2];
        tile[4] = new Tile();
        tile[4].image = tileImages[1][3];
        tile[5] = new Tile();
        tile[5].image = tileImages[2][0];
        tile[5].collisionOn = true;
        tile[6] = new Tile();
        tile[6].image = tileImages[3][0];
        tile[7] = new Tile();
        tile[7].image = tileImages[4][0];
        tile[8] = new Tile();
        tile[8].image = tileImages[0][0];
        tile[8].collisionOn = true;
        tile[9] = new Tile();
        tile[9].image = tileImages[0][0];
        tile[9].collisionOn = true;

    }
    public Tile getTile(int index) {
        return tile[index];
    }
    public int getMapTile(int col, int row) {
        return mapTile[col][row];
    }
        public void render (Graphics g) {

            Font font = new Font("Monospaced", Font.BOLD, 16);
            int col = 0;
            int row = 0;
            while (col < MAXSCREENCOL && row < MAXSCREENROW) {
                int tileNum = mapTile[col][row];
                if (gamePanel.getPlayer().getNumOfGrapes() == 1) {
                    if (tileNum != 9)
                        g.drawImage(tile[tileNum].image, TILESIZE * col, TILESIZE * row, TILESIZE, ORIGINALTILEHEIGHT, null);
                        g.drawImage(crackAnim[aniIndex], 570, 130, 48, 64, null);
                        g.drawImage(crackAnim[aniIndex], 534, 130, 48, 64, null);
                        tile[9].collisionOn = false;
                    } else {
                        if (gamePanel.getPlayer().getNumOfGrapes() == 0) {
                            if (tileNum != 9) {
                                g.drawImage(tile[tileNum].image, TILESIZE * col, TILESIZE * row, TILESIZE, ORIGINALTILEHEIGHT, null);
                            }
                        } else {
                            g.drawImage(tile[tileNum].image, TILESIZE * col, TILESIZE * row, TILESIZE, ORIGINALTILEHEIGHT, null);
                        }

                    }
                    renderGrid(g, TILESIZE * col, TILESIZE * row, showGrid);
                    renderTileData(g, Integer.toString(tileNum), font, TILESIZE * col, TILESIZE * row, showTileData);
                    col++;
                    if (col == MAXSCREENCOL) {
                        col = 0;
                        row++;

                    }
                }

        }

}
