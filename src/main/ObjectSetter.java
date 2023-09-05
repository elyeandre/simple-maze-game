package main;

import objects.Grapes;

import static main.GamePanel.*;

// Author Jerickson Mayor
public class ObjectSetter {
    GamePanel gamePanel;
    public ObjectSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setObject() {

        gamePanel.getObject()[0] = new Grapes();
        gamePanel.getObject()[0].setXpos(5 * TILESIZE);
        gamePanel.getObject()[0].setYpos(2 * TILESIZE);

        gamePanel.getObject()[1] = new Grapes();
        gamePanel.getObject()[1].setXpos(6 * TILESIZE);
        gamePanel.getObject()[1].setYpos(6 * TILESIZE);

        gamePanel.getObject()[2] = new Grapes();
        gamePanel.getObject()[2].setXpos(11 * TILESIZE);
        gamePanel.getObject()[2].setYpos(2 * TILESIZE);

        gamePanel.getObject()[3] = new Grapes();
        gamePanel.getObject()[3].setXpos(8 * TILESIZE);
        gamePanel.getObject()[3].setYpos(7 * TILESIZE);

        gamePanel.getObject()[4] = new Grapes();
        gamePanel.getObject()[4].setXpos(9 * TILESIZE);
        gamePanel.getObject()[4].setYpos(2 * TILESIZE);

        gamePanel.getObject()[4] = new Grapes();
        gamePanel.getObject()[4].setXpos(9 * TILESIZE);
        gamePanel.getObject()[4].setYpos(2 * TILESIZE);

        gamePanel.getObject()[5] = new Grapes();
        gamePanel.getObject()[5].setXpos(3 * TILESIZE);
        gamePanel.getObject()[5].setYpos(9 * TILESIZE);

        gamePanel.getObject()[6] = new Grapes();
        gamePanel.getObject()[6].setXpos(2 * TILESIZE);
        gamePanel.getObject()[6].setYpos(14 * TILESIZE);

        gamePanel.getObject()[7] = new Grapes();
        gamePanel.getObject()[7].setXpos(5 * TILESIZE);
        gamePanel.getObject()[7].setYpos( 13 * TILESIZE);

        gamePanel.getObject()[8] = new Grapes();
        gamePanel.getObject()[8].setXpos(5 * TILESIZE);
        gamePanel.getObject()[8].setYpos(9 * TILESIZE);

        gamePanel.getObject()[9] = new Grapes();
        gamePanel.getObject()[9].setXpos(9 * TILESIZE);
        gamePanel.getObject()[9].setYpos(10 * TILESIZE);

        gamePanel.getObject()[10] = new Grapes();
        gamePanel.getObject()[10].setXpos(8 * TILESIZE);
        gamePanel.getObject()[10].setYpos(13 * TILESIZE);

        gamePanel.getObject()[11] = new Grapes();
        gamePanel.getObject()[11].setXpos(12 * TILESIZE);
        gamePanel.getObject()[11].setYpos(11 * TILESIZE);

        gamePanel.getObject()[12] = new Grapes();
        gamePanel.getObject()[12].setXpos(11 * TILESIZE);
        gamePanel.getObject()[12].setYpos(6 * TILESIZE);

        gamePanel.getObject()[13] = new Grapes();
        gamePanel.getObject()[13].setXpos(10 * TILESIZE);
        gamePanel.getObject()[13].setYpos(14 * TILESIZE);

        gamePanel.getObject()[14] = new Grapes();
        gamePanel.getObject()[14].setXpos(13 * TILESIZE);
        gamePanel.getObject()[14].setYpos(14 * TILESIZE);

        gamePanel.getObject()[15] = new Grapes();
        gamePanel.getObject()[15].setXpos(14 * TILESIZE);
        gamePanel.getObject()[15].setYpos(10 * TILESIZE);

        gamePanel.getObject()[16] = new Grapes();
        gamePanel.getObject()[16].setXpos(16 * TILESIZE);
        gamePanel.getObject()[16].setYpos(13 * TILESIZE);


        gamePanel.getObject()[17] = new Grapes();
        gamePanel.getObject()[17].setXpos(14 * TILESIZE);
        gamePanel.getObject()[17].setYpos(7 * TILESIZE);

        gamePanel.getObject()[18] = new Grapes();
        gamePanel.getObject()[18].setXpos(17 * TILESIZE);
        gamePanel.getObject()[18].setYpos(6 * TILESIZE);

        gamePanel.getObject()[19] = new Grapes();
        gamePanel.getObject()[19].setXpos(16 * TILESIZE);
        gamePanel.getObject()[19].setYpos(2 * TILESIZE);









    }


}
