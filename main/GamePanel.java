package main;

import javax.swing.*;
import java.awt.*;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 8;
    
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 80; 
    final int maxScreenRow = 45; 
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    // Frames per second
    int fps = 60;

    // Constructors
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

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
        player.update();
    }
    
    
    public void paintComponent(Graphics g) {
        // Draw game objects here
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}