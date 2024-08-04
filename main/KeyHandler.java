package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    private boolean spacePressed = false;
    public boolean isSpacePressed() {return spacePressed;}
    public void setSpacePressed(boolean spacePressed) {this.spacePressed = spacePressed;}

    private boolean leftPressed = false;
    public boolean isLeftPressed() {return leftPressed;}
    public void setLeftPressed(boolean leftPressed) {this.leftPressed = leftPressed;}
    
    private boolean rightPressed = false;
    public boolean isRightPressed() {return rightPressed;}
    public void setRightPressed(boolean rightPressed) {this.rightPressed = rightPressed;}
    
    @Override
    public void keyPressed(KeyEvent arg0) {
        int code = arg0.getKeyCode();

        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        int code = arg0.getKeyCode();

        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

}
