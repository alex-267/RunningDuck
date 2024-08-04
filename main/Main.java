package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Running Duck");
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        frame.pack();
    }
}