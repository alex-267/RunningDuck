package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.*;

public class Background extends Entity {
    private GamePanel gp;
    private KeyHandler kH;
    private BufferedImage img;

    public Background(GamePanel gp, KeyHandler kH) {
        super(0, 0, 10);
        this.gp = gp;
        this.kH = kH;
        this.getBackgroundImage();
    }

    public void getBackgroundImage() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/image/background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.setX(getX() - this.getSpeed());
        if(this.getX() < -gp.getWidth()) {
            this.setX(0);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(img, getX(), getY(), gp.getWidth(), gp.getHeight(), null);
        g2.drawImage(img, getX()+gp.getWidth(), getY(), gp.getWidth(), gp.getHeight(), null);
    }
  
}
