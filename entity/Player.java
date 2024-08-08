package entity;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {
    private GamePanel gp;
    private KeyHandler kH;
    private boolean canJumpAgain;
    private BufferedImage img1, img2, img3, img4, img5, img6, imgJump;
    private int duckState;
    private boolean isJumping;

    private double drawInterval = 1000000000 / 20;
    private double delta = 0;
    private long lastTime = System.nanoTime();
    private long currentTime;

    private Rectangle duckHitbox;
    public Rectangle getDuckHitbox() {return duckHitbox;}

    private double score;
    private int bestScore = 0;
    private boolean newHighScore;
    private int difficulty;

    private String color;
    public void setColor(String color) {this.color = color;}
    private BufferedImage[] redDuck = new BufferedImage[7];
    private BufferedImage[] greenDuck = new BufferedImage[7];
    private BufferedImage[] blueDuck = new BufferedImage[7];

    public Player(GamePanel gp, KeyHandler kH) {
        super(300, 300, 7);
        this.gp = gp;
        this.kH = kH;
        this.getPlayerImage();
        this.duckHitbox = new Rectangle(this.getX()+2, this.getY()+7, 57, 57);
        this.color = "red";
        this.originalStats();
    }

    public void originalStats() {
        this.setX(300);
        this.setY(300);
        this.score = 0.0;
        this.duckState = 1;
        this.isJumping = false;
        this.difficulty = 0;
        this.newHighScore = false;
        this.canJumpAgain = true;
        this.delta = 0;
        this.lastTime = System.nanoTime();
    }

    public void getPlayerImage() {
        try {
            redDuck[0] = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck1.png"));
            redDuck[1] = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck2.png"));
            redDuck[2] = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck3.png"));
            redDuck[3] = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck4.png"));
            redDuck[4] = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck5.png"));
            redDuck[5] = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duck6.png"));
            redDuck[6] = ImageIO.read(getClass().getResourceAsStream("/image/duck/red/duckjump.png"));

            greenDuck[0] = ImageIO.read(getClass().getResourceAsStream("/image/duck/green/duck1.png"));
            greenDuck[1] = ImageIO.read(getClass().getResourceAsStream("/image/duck/green/duck2.png"));
            greenDuck[2] = ImageIO.read(getClass().getResourceAsStream("/image/duck/green/duck3.png"));
            greenDuck[3] = ImageIO.read(getClass().getResourceAsStream("/image/duck/green/duck4.png"));
            greenDuck[4] = ImageIO.read(getClass().getResourceAsStream("/image/duck/green/duck5.png"));
            greenDuck[5] = ImageIO.read(getClass().getResourceAsStream("/image/duck/green/duck6.png"));
            greenDuck[6] = ImageIO.read(getClass().getResourceAsStream("/image/duck/green/duckjump.png"));

            blueDuck[0] = ImageIO.read(getClass().getResourceAsStream("/image/duck/blue/duck1.png"));
            blueDuck[1] = ImageIO.read(getClass().getResourceAsStream("/image/duck/blue/duck2.png"));
            blueDuck[2] = ImageIO.read(getClass().getResourceAsStream("/image/duck/blue/duck3.png"));
            blueDuck[3] = ImageIO.read(getClass().getResourceAsStream("/image/duck/blue/duck4.png"));
            blueDuck[4] = ImageIO.read(getClass().getResourceAsStream("/image/duck/blue/duck5.png"));
            blueDuck[5] = ImageIO.read(getClass().getResourceAsStream("/image/duck/blue/duck6.png"));
            blueDuck[6] = ImageIO.read(getClass().getResourceAsStream("/image/duck/blue/duckjump.png"));

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

        if (this.gp.getState() == gp.getGameState()) {
            score+=0.1;

            if((int)score==600){
                difficulty++;
                gp.getObstacleManager().setObstacleSpeed(15);
                gp.getObstacleManager().setSecondMultiplier(500);
                gp.getBackgrounds().setSpeed(15);
                gp.getObstacleManager().setRandomTimer(3);
            }

            if(difficulty < 6){
                if((int)score%100 == 0){
                    if(difficulty!=(int)score/100){
                        difficulty++;
                        gp.getObstacleManager().setObstacleSpeed(gp.getObstacleManager().getObstacleSpeed()+1);
                        gp.getObstacleManager().setSecondMultiplier(gp.getObstacleManager().getSecondMultiplier()-100);
                        gp.getBackgrounds().setSpeed(gp.getObstacleManager().getObstacleSpeed());
                    }
                }
            }

            if(kH.isSpacePressed()){
                if(this.getY()>180 && canJumpAgain){
                    this.setY(this.getY()-this.getSpeed());
                    isJumping = true;
                    changeHitboxToJump();
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
                    changeHitboxToJump();
                }
                else{
                    canJumpAgain = true;
                    isJumping = false;
                    changeHitboxToRun();
                }
            }
        }
        else{
            switch (color) {
                case "red":
                    img1 = redDuck[0];
                    img2 = redDuck[1];
                    img3 = redDuck[2];
                    img4 = redDuck[3];
                    img5 = redDuck[4];
                    img6 = redDuck[5];
                    imgJump = redDuck[6];
                    break;
            
                case "green":
                    img1 = greenDuck[0];
                    img2 = greenDuck[1];
                    img3 = greenDuck[2];
                    img4 = greenDuck[3];
                    img5 = greenDuck[4];
                    img6 = greenDuck[5];
                    imgJump = greenDuck[6];
                    break;

                case "blue":
                    img1 = blueDuck[0];
                    img2 = blueDuck[1];
                    img3 = blueDuck[2];
                    img4 = blueDuck[3];
                    img5 = blueDuck[4];
                    img6 = blueDuck[5];
                    imgJump = blueDuck[6];
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if(gp.getState() == gp.getGameState()){
            g2.setFont(new Font("Roboto", Font.PLAIN, 24));
            g2.drawString("High Score : " + String.format("%010d",bestScore), gp.getWidth()-450, 20);
            g2.drawString(String.format("%010d",(int)score), gp.getWidth()-150, 20);
        }
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

    public void changeHitboxToJump(){
        this.duckHitbox.x = this.getX()+2;
        this.duckHitbox.y = this.getY()+29;
        this.duckHitbox.width = 54;
        this.duckHitbox.height = 24;
    }

    public void changeHitboxToRun(){
        this.duckHitbox.x = this.getX()+2;
        this.duckHitbox.y = this.getY()+7;
        this.duckHitbox.width = 57;
        this.duckHitbox.height = 57;
    }

    public void updateHighScore(){
        if(bestScore < (int)score){bestScore = (int)score; newHighScore = true;}
    }
    
    public void drawLoseScore(Graphics2D g2){
        g2.setColor(new Color(255, 255, 255, 200));
        g2.setFont(new Font("Roboto", Font.PLAIN, 50));
        g2.drawString("Score : " + String.format("%010d",(int)score), 260, 160);
        updateHighScore();
        if(newHighScore){
            g2.setColor(new Color(255, 255, 0, 200));
            g2.setFont(new Font("Roboto", Font.PLAIN, 30));
            g2.drawString("New high score !", 380, 195);
        }

    }

}
