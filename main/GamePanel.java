package main;

import javax.swing.JPanel;

import entity.*;
import entity.Button;

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
    private MouseHandler mH = new MouseHandler();
    private Player player = new Player(this, kH);
    private Background bg = new Background(this);
    private ObstacleManager obstacleManager = new ObstacleManager(this);

    public Player getPlayer() {return this.player;}
    public ObstacleManager getObstacleManager() {return this.obstacleManager;}
    public Background getBackgrounds() {return this.bg;}
    public MouseHandler getMouseHandler() {return this.mH;}

    private Button retry, home, play, red, green, blue;

    private int state = 2;
    public int getState() {return this.state;}
    public void setState(int state) {this.state = state;}

    private final int gameState = 0;
    public int getGameState() {return this.gameState;}
    
    private final int loseState = 1;
    public int getLoseState() {return this.loseState;}

    private final int homeState = 2;
    public int getHomeState() {return this.homeState;}

    //FPS
    private int FPS = 60;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
        this.initializeButtons();
        this.addMouseListener(mH);
        this.addMouseMotionListener(mH);
    }

    public void initializeButtons(){
        retry = new Button(this, 300, 300, 100, 100, "retry");
        home = new Button(this, 600, 300, 100, 100, "home");
        play = new Button(this, 450, 100, 100, 100, "play");
        red = new Button(this, this.screenWidth-100, 150, 64, 64, "red");
        green = new Button(this, this.screenWidth-100, 220, 64, 64, "green");
        blue = new Button(this, this.screenWidth-100, 290, 64, 64, "blue");
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
        if(state == loseState){
            retry.update();
            home.update();
        }
        if(state == homeState){
            bg.update();
            player.update();
            play.update();
            red.update();
            green.update();
            blue.update();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        bg.draw(g2);
        player.draw(g2);

        if(state == gameState || state == loseState){
            obstacleManager.draw(g2);
        }
        
        if(state == loseState){
            drawLoseScreen(g2);
        }

        if(state == homeState){
            play.draw(g2);
            red.draw(g2);
            green.draw(g2);
            blue.draw(g2);
        }

        g2.dispose();
    }

    public void drawLoseScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, screenWidth, screenHeight);

        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(0, 80, screenWidth, 120);

        retry.draw(g2);
        home.draw(g2);

        player.drawLoseScore(g2);

        g2.setColor(Color.black);
    }

    public void play(){
        player.originalStats();
        obstacleManager.originalStats();
        bg.setSpeed(5);
        state = gameState;
    }

    public void home(){
        player.originalStats();
        obstacleManager.originalStats();
        bg.setSpeed(5);
        state = homeState;
    }
}