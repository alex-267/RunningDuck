package main;

import javax.swing.JPanel;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    
    //Screen settings
    final private int tileSize = 64;
    final private int maxScreenCol = 16;
    final private int maxScreenRow = 8;
    final private int screenWidth = tileSize * maxScreenCol; //1024 px
    final private int screenHeight = tileSize * maxScreenRow; //512 px

    Thread gameThread;

    KeyHandler kH = new KeyHandler();

    //Player 
    private int playerX = 300;
    private int playerY = 300;
    private int playerSpeed = 7;
    private boolean canJumpAgain = true;

    //FPS
    private int FPS = 60;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta>=1){
                //Update
                update();
                //Draw
                repaint();
                delta--;    
            }
        }
    }

    public void update(){
        if(kH.isSpacePressed()){
            if(playerY>150 && canJumpAgain){
                playerY -= playerSpeed;
            }
            else{
                kH.setSpacePressed(false);
                canJumpAgain = false;
            }
        }
        if(kH.isLeftPressed()){
            if(playerX-playerSpeed >= 0){
                playerX -= playerSpeed;
                
            }
        }
        if(kH.isRightPressed()){
            if (playerX+playerSpeed+tileSize < screenWidth){
                playerX += playerSpeed;
            }  
        }
        if(!kH.isSpacePressed()){
            canJumpAgain = false;
            if (playerY < 300) {
                playerY += playerSpeed;
            }
            else{
                canJumpAgain = true;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}