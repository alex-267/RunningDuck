package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class ObstacleManager {
    private GamePanel gp;
    private ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
    private long lastExecutionTime = 0;
    private long timeBeforeNextExecution = 5000;
    private Random random = new Random();
    private int[][] allObstacle = new int[5][2];
    private int nextObstacle;

    public ObstacleManager(GamePanel gp) {
        this.gp = gp;
        obstacleCreation();
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
            obstacle.add(new Obstacle(gp, allObstacle[nextObstacle][0], allObstacle[nextObstacle][1]));
            lastExecutionTime = System.currentTimeMillis();
        }
    }

    public void draw(Graphics2D g2) {
        for (Obstacle o : obstacle) {
            o.draw(g2);
        }
    }
}
