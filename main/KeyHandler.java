package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) {
            // Move up
            upPressed = true;
        } else if(code == KeyEvent.VK_DOWN) {
            // Move down
            downPressed = true;
        } else if(code == KeyEvent.VK_LEFT) {
            // Move left
            leftPressed = true;
        } else if(code == KeyEvent.VK_RIGHT) {
            // Move right
            rightPressed = true;
        } else if(code == KeyEvent.VK_W) {
            // Perform action
            upPressed = true;
        } else if(code == KeyEvent.VK_S) {
            // Perform action
            downPressed = true;
        } else if(code == KeyEvent.VK_A) {
            // Perform action
            leftPressed = true;
        } else if(code == KeyEvent.VK_D) {
            // Perform action
            rightPressed = true;
        } else if(code == KeyEvent.VK_SPACE) {
            // Perform action
        } else if(code == KeyEvent.VK_ESCAPE) {
            // Pause game
        } else if(code == KeyEvent.VK_ENTER) {
            // Confirm action
        } else {
            // Handle other key events
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) {
            // Move up
            upPressed = false;
        } else if(code == KeyEvent.VK_DOWN) {
            // Move down
            downPressed = false;
        } else if(code == KeyEvent.VK_LEFT) {
            // Move left
            leftPressed = false;
        } else if(code == KeyEvent.VK_RIGHT) {
            // Move right
            rightPressed = false;
        } else if(code == KeyEvent.VK_W) {
            // Perform action
            upPressed = false;
        } else if(code == KeyEvent.VK_S) {
            // Perform action
            downPressed = false;
        } else if(code == KeyEvent.VK_A) {
            // Perform action
            leftPressed = false;
        } else if(code == KeyEvent.VK_D) {
            // Perform action
            rightPressed = false;
        } else if(code == KeyEvent.VK_SPACE) {
            // Perform action
        } else if(code == KeyEvent.VK_ESCAPE) {
            // Pause game
        } else if(code == KeyEvent.VK_ENTER) {
            // Confirm action
        } else {
            // Handle other key events
        }
    }

}
