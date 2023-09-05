package main;

import entity.Player;
import objects.SuperObject;
import javax.swing.JPanel;
import java.awt.*;

// Author: Jerickson Mayor

public class GamePanel extends JPanel implements Runnable {
    public final static int     TILESIZE = 36;
    public final static int     TILEHEIGHTDEPTH = 14;
    public final static int     ORIGINALTILEHEIGHT = TILESIZE + TILEHEIGHTDEPTH;
    public final static int     MAXSCREENCOL = 20;
    public final static int     MAXSCREENROW = 18;
    public final static int     SCREENMAXHEIGHT = (TILESIZE * MAXSCREENROW) + TILEHEIGHTDEPTH; // 662
    public final static int     SCREENMAXWIDTH = TILESIZE * MAXSCREENCOL; // 772
    private Thread              gameThread;
    private final TileManager   tileManager;
    private final Player        player;
    private final Collision     collisionSystem;
    private final SuperObject[] superObjects;
    private final SoundHandler  music;
    private final SoundHandler  soundfx;
    private final ObjectSetter  objectSetter;
    private final UserInterface userInterface;


    public GamePanel()  {
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        userInterface = new UserInterface(this);
        tileManager = new TileManager(this);
        player = new Player(this,88,90);
        music = new SoundHandler();
        soundfx = new SoundHandler();
        collisionSystem = new Collision(this);
        objectSetter = new ObjectSetter(this);
        superObjects = new SuperObject[20];
        setPanelSize();
        this.addKeyListener(new KeyHandler(this));
    }
    public void setUpGame() {
        playMusic(2);
        objectSetter.setObject();
    }
    private void setPanelSize() {
        Dimension dimension = new Dimension(SCREENMAXWIDTH,SCREENMAXHEIGHT);
        this.setPreferredSize(dimension);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void update() {

        int i = 0;
        while (i<  superObjects.length) {
            if(superObjects[i] != null) {
                superObjects[i].update();
            }
            i++;
        }
        tileManager.update();
        player.update();
        userInterface.update();


    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSfx(int i)  {
        soundfx.setFile(i);
        soundfx.play();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.white);
        // Fake Shadow
        tileManager.drawShadow(g);
        // Tiles
        tileManager.render(g);
        // Object
        for (SuperObject superObject : superObjects) {
            if (superObject != null) {
                superObject.render(g, this);
            }
        }
        // Player
        player.render(g);
        // HUD
        userInterface.render(g2);
        // This block of code help smoothing the animation in some systems.
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        int FPS = 120;
        double ns = 1000000000 / (double) FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (gameThread != null) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                repaint();
                updates++;
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    public void windowFocusLost() {
        if(!player.isCollided()) {
            player.resetDirBoolean();
            player.setMoving(false);
            player.resetAniTick();
        }
    }
    public TileManager getTileManager() {
        return tileManager;
    }
    public Player getPlayer() {
        return player;
    }
    public SuperObject[] getObject() {
        return superObjects;
    }
    public Collision getCollisionSystem() {
        return collisionSystem;
    }
    public UserInterface getUserInterface() {
        return userInterface;
    }

}
