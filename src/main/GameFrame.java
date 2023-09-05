package main;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import static main.GamePanel.SCREENMAXHEIGHT;
import static main.GamePanel.SCREENMAXWIDTH;
public class GameFrame extends JFrame {

private GamePanel gamePanel;

   GameFrame() {
        super.setTitle("Simple Maze Game");
        this.setResizable(false);
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setSize(SCREENMAXWIDTH,SCREENMAXHEIGHT);
        this.pack();
        this.setLocationRelativeTo(null);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {}
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.windowFocusLost();

            }
        });


    }
     public static void main(String[] args) {
    	    new GameFrame().setVisible(true);  
     }
}
