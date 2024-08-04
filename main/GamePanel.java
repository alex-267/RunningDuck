package main;

import javax.swing.JPanel;
import java.awt.*;
public class GamePanel extends JPanel{
    
    //Screen settings
    final int tileSize = 64;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //1024 px
    final int screenHeight = tileSize * maxScreenRow; //768 px
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}