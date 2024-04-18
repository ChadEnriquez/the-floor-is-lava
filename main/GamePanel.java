package main;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private static final int originalTileSize = 16;
    private static final int scale = 4;
    private static final int tileSize = originalTileSize * scale;
    private static final int maxScreenCol = 80; // Adjusted for 1920x1080
    private static final int maxScreenRow = 45; // Adjusted for 1920x1080
    private static final int screenWidth = maxScreenCol * tileSize;
    private static final int screenHeight = maxScreenRow * tileSize;

    // Frames per second
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Set player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 24;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // draw screen at 60 fps
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // Update information
            update();
            // Draw/Render
            repaint();
            // Sleep method to control frame rate
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // Update game logic here
        if (keyH.upPressed && playerY > 0) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed && playerY < (screenHeight - tileSize)) {
            // Adjusted the condition to prevent moving beyond the bottom edge
            playerY = Math.min(playerY + playerSpeed, screenHeight - tileSize);
        } else if (keyH.leftPressed && playerX > 0) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed && playerX < (screenWidth - tileSize)) {
            // Adjusted the condition to prevent moving beyond the right edge
            playerX = Math.min(playerX + playerSpeed, screenWidth - tileSize);
        }
    }
    
    
    public void paintComponent(Graphics g) {
        // Draw game objects here
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}