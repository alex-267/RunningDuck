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

    private Thread gameThread;
    private KeyHandler kH = new KeyHandler();
    private Player player = new Player(this, kH);
    private Background bg = new Background(this);
    private ObstacleManager obstacleManager = new ObstacleManager(this);

    public Player getPlayer() {return this.player;}
    public ObstacleManager getObstacleManager() {return this.obstacleManager;}
    public Background getBackgrounds() {return this.bg;}

    private int state = 0;
    public int getState() {return this.state;}
    public void setState(int state) {this.state = state;}

    private final int gameState = 0;
    public int getGameState() {return this.gameState;}
    
    private final int loseState = 1;
    public int getLoseState() {return this.loseState;}

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
        if(state == gameState){
            bg.update();
            player.update();
            obstacleManager.update();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        bg.draw(g2);
        player.draw(g2);
        obstacleManager.draw(g2);
        
        if(state == loseState){
            drawLoseScreen(g2);
        }

        g2.dispose();
    }

    public void drawLoseScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, screenWidth, screenHeight);

        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(0, 80, screenWidth, 120);

        player.drawLoseScore(g2);

        g2.setColor(Color.black);
    }
}