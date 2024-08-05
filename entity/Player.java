package entity;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {
    private GamePanel gp;
    private KeyHandler kH;
    private boolean canJumpAgain = true;
    private BufferedImage img1, img2, img3, img4, img5, img6, imgJump;
    private int duckState = 1;
    private boolean isJumping = false;

    private double drawInterval = 1000000000 / 20;
    private double delta = 0;
    private long lastTime = System.nanoTime();
    private long currentTime;

    public Player(GamePanel gp, KeyHandler kH) {
        super(300, 300, 7);
        this.gp = gp;
        this.kH = kH;
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            img1 = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck1.png"));
            img2 = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck2.png"));
            img3 = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck3.png"));
            img4 = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck4.png"));
            img5 = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck5.png"));
            img6 = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck6.png"));
            imgJump = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duckjump.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() { 
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / drawInterval;
        lastTime = currentTime;

        if(delta>=1){
            duckState++;
            if(duckState == 7){
                duckState = 1;
            }
            delta--;
        }

        if(kH.isSpacePressed()){
            if(this.getY()>150 && canJumpAgain){
                this.setY(this.getY()-this.getSpeed());
                isJumping = true;
            }
            else{
                kH.setSpacePressed(false);
                canJumpAgain = false;
            }
        }
        if(kH.isLeftPressed()){
            if(this.getX()-this.getSpeed() >= 0){
                this.setX(this.getX()-this.getSpeed());
            }
        }
        if(kH.isRightPressed()){
            if (this.getX()+this.getSpeed()+gp.getTileSize() < gp.getWidth()){
                this.setX(this.getX()+this.getSpeed());
            }  
        }
        if(!kH.isSpacePressed()){
            canJumpAgain = false;
            if (this.getY() < 300) {
                this.setY(getY()+this.getSpeed());
            }
            else{
                canJumpAgain = true;
                isJumping = false;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if(isJumping){
            g2.drawImage(imgJump, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
        }
        else{
            switch (duckState) {
                case 1:
                    g2.drawImage(img1, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
                    break;
                case 2:
                    g2.drawImage(img2, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
                    break;
                case 3:
                    g2.drawImage(img3, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
                    break;
                case 4:
                    g2.drawImage(img4, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
                    break;
                case 5:
                    g2.drawImage(img5, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
                    break;
                case 6:
                    g2.drawImage(img6, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
                    break;
            }
        }
        
    }
    
}
