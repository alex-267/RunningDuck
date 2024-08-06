package entity;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import main.*;

public class Obstacle extends Entity {
    private GamePanel gp;
    private BufferedImage img;
    private int width, height;
    private Rectangle hitbox;
    
    public Obstacle(GamePanel gp, int width, int height, int speed) {
        super(gp.getWidth(), 364-height, speed);
        this.gp = gp;
        this.width = width;
        this.height = height;
        this.getObstacleImage();
        this.hitbox = new Rectangle(this.getX(), this.getY(), this.width, this.height);
    }

    public void getObstacleImage() {
        try {
            switch (this.width) {
                case 40:
                    if (height == 40) {
                        img = ImageIO.read(getClass().getResourceAsStream("/image/obstacle/obstacle4040.png"));
                    }
                    else{
                        img = ImageIO.read(getClass().getResourceAsStream("/image/obstacle/obstacle4070.png"));
                    }
                    break;
                case 90:
                    if (height == 40) {
                        img = ImageIO.read(getClass().getResourceAsStream("/image/obstacle/obstacle9040.png"));
                    }
                    else{
                        img = ImageIO.read(getClass().getResourceAsStream("/image/obstacle/obstacle9064.png"));
                    }
                    break;
                case 150:
                    img = ImageIO.read(getClass().getResourceAsStream("/image/obstacle/obstacle15040.png"));
                    break;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.setX(this.getX() - this.getSpeed());
        updateHitbox();
        if(hitbox.intersects(gp.getPlayer().getDuckHitbox())){
            gp.setState(gp.getLoseState());
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(img, getX(), getY(), width, height, null);
    }

    public void updateHitbox(){
        this.hitbox.x = this.getX();
        this.hitbox.y = this.getY();
    }

}
