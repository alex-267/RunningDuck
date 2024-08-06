package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class ObstacleManager {
    private GamePanel gp;
    private ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
    private long lastExecutionTime;
    private long timeBeforeNextExecution;
    private int randomTimer;
    private Random random = new Random();
    private int[][] allObstacle = new int[5][2];
    private int nextObstacle;
    private int obstacleSpeed;
    private int secondMultiplier;

    public ObstacleManager(GamePanel gp) {
        this.gp = gp;
        obstacleCreation();
        originalStats();
    }

    public void originalStats(){
        lastExecutionTime = 0;
        timeBeforeNextExecution = 5000;
        randomTimer = 5;
        obstacleSpeed = 5;
        secondMultiplier = 1000;
        obstacle.clear();
    }

    public void obstacleCreation() {
        allObstacle[0][0] = 40;
        allObstacle[0][1] = 40;

        allObstacle[1][0] = 40;
        allObstacle[1][1] = 70;

        allObstacle[2][0] = 90; 
        allObstacle[2][1] = 40;

        allObstacle[3][0] = 90;
        allObstacle[3][1] = 64;

        allObstacle[4][0] = 150;
        allObstacle[4][1] = 40;
    }

    public void update() {
        for (Obstacle o : obstacle) {
            o.update();
        }
        if (System.currentTimeMillis() - lastExecutionTime >= timeBeforeNextExecution) {
            nextObstacle = random.nextInt(5);
            obstacle.add(new Obstacle(gp, allObstacle[nextObstacle][0], allObstacle[nextObstacle][1], obstacleSpeed));
            lastExecutionTime = System.currentTimeMillis();
            timeBeforeNextExecution = (random.nextInt(randomTimer)+1)*secondMultiplier;
        }
    }

    public void draw(Graphics2D g2) {
        for (Obstacle o : obstacle) {
            o.draw(g2);
        }
    }

    public int getSecondMultiplier() {return secondMultiplier;}
    public void setSecondMultiplier(int secondMultiplier) {this.secondMultiplier = secondMultiplier;}
    public int getObstacleSpeed() {return obstacleSpeed;}
    public void setObstacleSpeed(int obstacleSpeed) {this.obstacleSpeed = obstacleSpeed;}
    public int getRandomTimer() {return randomTimer;}
    public void setRandomTimer(int randomTimer) {this.randomTimer = randomTimer;}
}
