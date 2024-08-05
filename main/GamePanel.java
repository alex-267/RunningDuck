package main;

import javax.swing.JPanel;

import entity.*;

import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    
    //Screen settings
    final private int tileSize = 64;
    public int getTileSize() {return tileSize;}

    final private int maxScreenCol = 16;
    public int getMaxScreenCol() {return maxScreenCol;}

    final private int maxScreenRow = 8;
    public int getMaxScreenRow() {return maxScreenRow;}

    final private int screenWidth = tileSize * maxScreenCol; //1024 px
    public int getScreenWidth() {return screenWidth;}

    final private int screenHeight = tileSize * maxScreenRow; //512 px
    public int getScreenHeight() {return screenHeight;}

    Thread gameThread;
    KeyHandler kH = new KeyHandler();
    Player player = new Player(this, kH);
    Background bg = new Background(this);
    ObstacleManager obstacleManager = new ObstacleManager(this);

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
        bg.update();
        player.update();
        obstacleManager.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        bg.draw(g2);
        player.draw(g2);
        obstacleManager.draw(g2);
        
        g2.dispose();
    }
}