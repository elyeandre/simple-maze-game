package objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static main.GamePanel.ORIGINALTILEHEIGHT;
import static main.GamePanel.TILESIZE;
public class Heart extends SuperObject {
    public Heart() {
    loadImage();
    }
    private void loadImage() {
        InputStream is = getClass().getResourceAsStream("/sprites/player_life_sheet.png");
        try {
            BufferedImage img = ImageIO.read(is);
            BufferedImage[] animations = new BufferedImage[3];
            setAnimations(animations);
                for (int i = 0; i < animations.length; i++)
                    animations[i] = img.getSubimage(i * 32, 0 * 32, 32, 32);
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
