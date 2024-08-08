package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    private int mouseX, mouseY;
    public int getMouseX() {return mouseX;}
    public int getMouseY() {return mouseY;}

    private int mouseClickedX, mouseClickedY;
    public int getMouseClickedX() {return mouseClickedX;}
    public int getMouseClickedY() {return mouseClickedY;}
    public void setMouseClickedX(int mouseClickedX) {this.mouseClickedX = mouseClickedX;}
    public void setMouseClickedY(int mouseClickedY) {this.mouseClickedY = mouseClickedY;}
    
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClickedX = e.getX();
        mouseClickedY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
   
}
