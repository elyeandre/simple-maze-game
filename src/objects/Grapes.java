package objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static main.GamePanel.ORIGINALTILEHEIGHT;
import static main.GamePanel.TILESIZE;
public class Grapes extends SuperObject {
    public Grapes() {
        loadAnimations();
        setCollisionOn(true);
    }
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/sprites/grape_sheet.png");
        try {
            BufferedImage img = ImageIO.read(is);
            BufferedImage[] animations = new BufferedImage[5];
            setAnimations(animations);
                for (int i = 0; i < animations.length; i++)
                    animations[i] = img.getSubimage(i * TILESIZE, 0 * ORIGINALTILEHEIGHT, TILESIZE, ORIGINALTILEHEIGHT);
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
