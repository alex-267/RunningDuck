package entity;

import java.awt.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.*;

public class Button extends JButton{
    
    private int x, y, width, height;
    private GamePanel gp;
    private BufferedImage img, img2, imgToDraw;
    private String name;
    private Rectangle hitbox;

    public Button(GamePanel gp, int x, int y, int width, int height, String name) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.getButtonImage();
        imgToDraw = img;
        this.hitbox = new Rectangle(x, y, width, height);
    }

    public void getButtonImage() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/image/button/"+this.name+".png"));
            img2 = ImageIO.read(getClass().getResourceAsStream("/image/button/"+this.name+"2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(this.hitbox.contains(this.gp.getMouseHandler().getMouseX(), this.gp.getMouseHandler().getMouseY())) {
            imgToDraw = img2;
        } else{
            imgToDraw = img;
        }
        if (this.hitbox.contains(this.gp.getMouseHandler().getMouseClickedX(), this.gp.getMouseHandler().getMouseClickedY())) {
            switch (name) {
                case "retry":
                    gp.play();
                    break;
            
                case "home":
                    gp.home();
                    break;

                case "play":
                    gp.play();
                    break;

                case "red":
                    gp.getPlayer().setColor("red");
                    break;

                case "green":
                    gp.getPlayer().setColor("green");
                    break;

                case "blue":
                    gp.getPlayer().setColor("blue");
                    break;
            }
            this.gp.getMouseHandler().setMouseClickedX(0);
            this.gp.getMouseHandler().setMouseClickedY(0);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(imgToDraw, x, y, width, height, null);
    }
}