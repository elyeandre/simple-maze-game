package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Author Jerickson Mayor
public class KeyHandler implements KeyListener {
    private GamePanel gamePanel;
    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if (!gamePanel.getPlayer().isCollided() && gamePanel.getPlayer().getNumOfGrapes() != 0 && gamePanel.getPlayer().getPlayerLife() != 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    gamePanel.getPlayer().setUp(true);
                    gamePanel.getPlayer().setDirection("up");
                    break;
                case KeyEvent.VK_S:
                    gamePanel.getPlayer().setDown(true);
                    gamePanel.getPlayer().setDirection("down");
                    break;
                case KeyEvent.VK_A:
                    gamePanel.getPlayer().setLeft(true);
                    gamePanel.getPlayer().setDirection("left");
                    break;
                case KeyEvent.VK_D:
                    gamePanel.getPlayer().setRight(true);
                    gamePanel.getPlayer().setDirection("right");
                    break;
            }

        }

    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
